#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <openssl/md5.h>
// gcc crack.c -o crack -lpthread -lcrypto

#define NTHREADS 16 
#define CRACK_COUNT 10
#define PASSWORD_MAXLENGTH 6  // 5 letters plus NUL terminator
#define MD5_LENGTH 16
#define DIGEST_LENGTH 33      // 32 chars plus null terminator
#define LOWER_ASCII 'a'
#define UPPER_ASCII 'z'

int statics[NTHREADS];        // will be initialized to 1,2,3...etc.
char passwords[CRACK_COUNT][PASSWORD_MAXLENGTH];
char digests[CRACK_COUNT][DIGEST_LENGTH];
char uncracked_digests[CRACK_COUNT][DIGEST_LENGTH];
int cracked_status[CRACK_COUNT]; // Set to 0 when cracked
int next_uncracked_digest_index = 0;

void increment(char * password) {

  int idx = PASSWORD_MAXLENGTH - 2; // skip null terminator
  int done = -1;
  char ltr;
  while (done != 0) {
    ltr = password[idx];
    // printf("ltr: %d\n", ltr);
    if (ltr >= UPPER_ASCII) {

      // reset this digit, and any to its left that are also UPPER_ASCII
      int reset_idx = idx;
      while ((reset_idx >= 0) && (password[reset_idx]>=UPPER_ASCII)) {
        password[reset_idx] = LOWER_ASCII;
        if (reset_idx == 0) {
          done = 0;
        }
        else {
          reset_idx--;
        }
      } // end while
      if (reset_idx == -1) {
        done = 0;
      }
      else {
        password[reset_idx] += 1;
        done = 0;
      }
    } // end if

    else {
      password[idx] += 1;
      done = 0;
    }

  } // end while
}

void * crack_hashes(void * arg) {
    // thread_num is a "common" thread index between 0 and (NTHREADS-1)
    int thread_num = * (int *) arg;
    int j;

    MD5_CTX context;
    unsigned char * this_hash = malloc(MD5_LENGTH);
    char digest[DIGEST_LENGTH];                   // the one we're cracking
    char * hash_string = malloc(DIGEST_LENGTH);   // for this test_password
    char test_password[PASSWORD_MAXLENGTH];

    while(next_uncracked_digest_index < CRACK_COUNT) {

      strncpy(digest,uncracked_digests[next_uncracked_digest_index++],DIGEST_LENGTH);
      printf("Thread %d\tCrack digest:\t%s\n",thread_num, digest);
      char test_password[PASSWORD_MAXLENGTH] = "aaaa";
      int cracked = -1;
      while (cracked != 0) {

        // Test this password: Compute MD5
        MD5_Init(&context);
        if (MD5_Update(&context,test_password,PASSWORD_MAXLENGTH-1) != 1) { printf("MD5 fail\n"); }
        if (MD5_Final(this_hash,&context) != 1) { printf("MD5 fail\n"); }
        for(j=0; j<MD5_LENGTH; j++) { sprintf(hash_string + j*2,"%02x",this_hash[j]); }
        hash_string[DIGEST_LENGTH-1] = 0x0;

        // If a match: print, note, and exit
        if (strncmp(digest,hash_string,DIGEST_LENGTH-1) == 0) {
          printf("Thread %d\tMatch: %s\t%s\n", thread_num, test_password, digest);
          cracked = 0;
          cracked_status[next_uncracked_digest_index] = 0;
        }
        else { increment(test_password); }

      } // end while cracked != 0

    } // end while more digests to cracked

}

int main()
{

    FILE * f;
    char * line = NULL;
    size_t len = 0;
    ssize_t read = 0;
    pthread_t cracker_threads[NTHREADS];
    int i, j, line_count;

    for (i=0; i<CRACK_COUNT; i++) { cracked_status[i] = -1; }

    // Read uncracked digests from text file
    f = fopen("240954.digest","r");

    line_count = 0;
    while ((read = getline(&line, &len, f) != -1) && (line_count < CRACK_COUNT)) {
      strncpy(uncracked_digests[line_count],line,DIGEST_LENGTH-1);
      // printf("%s\n",uncracked_digests[line_count]);
      line_count++;
    }

    // Initialize the static numeric array
    for(i=0; i < NTHREADS; i++) { statics[i] = i; }

    // TODO: Create the threads. Use NUMTHREADS as a total.
    // Use the statics[] array as an argument. so each thread knows its number.
    for(i = 0; i < NTHREADS; i++) {
      pthread_create( &cracker_threads[i], NULL, crack_hashes, &statics[i] );
    }

    // TODO: Join all threads when complete
    for(j = 0; j < NTHREADS; j++) {
      pthread_join( cracker_threads[j], NULL);
    }

    // TODO: For multi-threaded ops, comment out the next two lines:
    //i = 1;
    //crack_hashes(&i);
}
