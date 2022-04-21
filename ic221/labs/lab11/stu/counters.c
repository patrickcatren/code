#include <stdio.h>
#include <pthread.h>

#define NTHREADS 10
void *thread_function(void *);

int counter = 0;
int statics[NTHREADS];      // will be initialized to 0,1,2,3...etc.

int main()
{
   pthread_t thread_id[NTHREADS];
   int i, j;
   
   // Initialize the static numeric array
   for(i=0; i < NTHREADS; i++)
   {
      statics[i] = i;
   }   

    // Create the threads. The statics[] array is needed because &i may increment
    //   before it is referenced, resulting in the wrong thread_num being assigned.
    //   We use &statics[i] instead of &i to initalize the (common) thread number.
    for(i=0; i < NTHREADS; i++)
    {
        pthread_create( &thread_id[i], NULL, thread_function, &statics[i] );
    }

    // Join all threads when complete
    for(j=0; j < NTHREADS; j++)
    {
        pthread_join( thread_id[j], NULL); 
    }
  
    // Reveal final counter value
    printf("Final counter value: %d\n", counter); 
}

void *thread_function(void * t_num)
{
    int thread_num = * (int *) t_num;
    printf("Thread number %d, id: %ld\n", thread_num, pthread_self());
    counter++;
    
     if (thread_num == 7) {
        printf("Lucky number 7! Press enter to continue...\n");
        getchar();
    } 
}
