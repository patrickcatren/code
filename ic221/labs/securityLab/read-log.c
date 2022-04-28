#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>

int main(int argc, char * argv[]){

    int last = 1;                       // How many last lines to read (default 1)
    FILE * f_log; 

    // TODO: update to reflect your directory
    char * path = "/home/mids/mXXXXXX/ic221/log.txt";

    // Check arguments
    if (argc >= 2){

        if (sscanf(argv[1], "%d", &last) == 0){
            fprintf(stderr, "ERROR: invalid value '%s'\n", argv[1]);
            exit(1);
        }
        if (last < 0) {
            fprintf(stderr, "ERROR: invalid value '%s' \n", argv[1]);
            exit(1);
        }        
    }

    // Open file
    if( (f_log = fopen(path, "r")) == NULL){
        perror("fopen");
        fprintf(stderr, "ERROR: Opening Log File '%s'\n",path);
        exit(1);
    }

    //  Read the last n lines of the file
    //  Algorithm big idea: You're tracking backwards in the file
    //  searching for newline symbols. Once you've found n newlines, you
    //  print out the file from that point.
    
    fseek(f_log,0,SEEK_END);        // Go to end of file
    if(ftell(f_log) == 0) return 0; // File was empty: exit

    int i = -1, j;
    char c;

    // Move backwards n entries (number in 'last')
    for(j=0; j<last; j++){
        do{
            fseek(f_log,--i,SEEK_END);
            fread(&c,1,1,f_log);
        }while(c != '\n' && ftell(f_log) != 1); //not a new line or start of file

        if(ftell(f_log) <= 1) break;    
    }

    // Going forward, write everything, here to end of log file, to stdout
    fseek(f_log, i++, SEEK_END);
    while ( fread(&c, 1, 1, f_log) ){
        fwrite(&c, 1, 1, stdout);
    }

    fflush(stdout);
    return 0;
}

