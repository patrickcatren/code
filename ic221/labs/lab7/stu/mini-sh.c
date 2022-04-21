/* IC221 Fork Lab
 * mini-sh.c : program to read and execute simple command lines
 * 3/C Patrick Catren
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <readline/readline.h>
#include <readline/history.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <sys/types.h>

#define MAX_ARGS 128

/* Subtract the ‘struct timeval’ values X and Y,
 * storing the difference in 'result'.
 * Return 1 if the difference is negative, otherwise 0.
 * source: https://www.gnu.org/software/libc/manual/html_node/Elapsed-Time.html */
int timeval_subtract (struct timeval *result, struct timeval *x, struct timeval *y)
{
  /* Perform the carry for the later subtraction by updating y. */
  if (x->tv_usec < y->tv_usec) {
	int nsec = (y->tv_usec - x->tv_usec) / 1000000 + 1;
	y->tv_usec -= 1000000 * nsec;
	y->tv_sec += nsec;
  }
  if (x->tv_usec - y->tv_usec > 1000000) {
	int nsec = (x->tv_usec - y->tv_usec) / 1000000;
	y->tv_usec += 1000000 * nsec;
	y->tv_sec -= nsec;
  }

  /* Compute the time difference */
  result->tv_sec = x->tv_sec - y->tv_sec;
  result->tv_usec = x->tv_usec - y->tv_usec;

  /* Return 1 if result is negative. */
  return x->tv_sec < y->tv_sec;
}


int main(int argc, char * argv[]) {
	char *line;       //DO NOT EDIT, needed for readline
	char prompt[256]; //DO NOT EDIT, needed for readline
	pid_t c_pid, pid;   	//NOTE: to store child pid
	int status, i; 	//NOTE: For wait status and iteration
	char *cmd_argv[MAX_ARGS]; //NOTE: use this store your argv array
	char *tok;   		//NOTE: This is useful for tokenization
	struct timeval start, end, diff; //for timing

	start.tv_sec = 0;
	start.tv_usec = 0;
	diff = end = start; //initialize times to zero

	//for readline setup, don't edit!
	rl_bind_key('\t', rl_abort);

	while(1) {

	// Prompt user for input. Add the previous 'diff' time to the prompt.
	snprintf(prompt, 256, "mini-sh (%ld.%04ld) #> ", diff.tv_sec, diff.tv_usec/1000);

	// Note: readline allocates a new line every time it reads
	line = readline(prompt);

	// If you read nothing (EOF/NULL), break the loop
	if (line == NULL) {
		printf("\n");
		break;
	}

	// If you read an empty line, continue
	if (strcmp(line,"")==0) {
		free(line);
		continue;
	}

	// Generate the cmd_argv[] array from the line
	tok = strtok(line, " ");
	cmd_argv[0] = tok;
	i = 1;
		while( (tok = strtok(NULL, " ")) != NULL){
			cmd_argv[i] = tok;
			if ( i+1 > MAX_ARGS ){ //don't go beyond MAX_ARGS
				break;
			}
			i++;
	}
	cmd_argv[i] = NULL;  //NULL Final Space in argv array

	//TODO: Record start time.
	//      Fork-Exec-Wait the command, and compute the time of execution.
	//      Store the time of execution in 'diff' so that it will be
	//      available in the next prompt.

  c_pid = fork();
  gettimeofday(&start, NULL);
  if(c_pid == 0){ // Child
    pid = getpid();
    execvp( cmd_argv[0], cmd_argv);
    perror("exec");
    _exit(1);
  }
  else if(c_pid > 0){ // Parent
    if((pid = wait(&status))<0){
      perror("wait");
      _exit(1);
    }
    gettimeofday(&end, NULL);
  }
  else{
    perror("fork failed");
    _exit(1);
  }

  timeval_subtract(&diff, &end, &start);

	//HINT: Make sure you *always* exit() in the child, in case the exec()
	//      fails.

	free(line); // avoid a memory leak
	}

	return 0;
}