#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <readline/readline.h>
#include <readline/history.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

#define MAX_ARGS 128
#define MAX_CMDS 128


int main(int argc, char * argv[]){

	pid_t cpid;		// Used during the fork
	pid_t pgroup=0;	// variable to save the common PGID
	int i, status;    
	char* commands[MAX_CMDS];
	char* pipeline[MAX_CMDS][MAX_ARGS];
    char* token;
	char* command;

	// Read a line from supplied arguments in argv
	if (argc < 2) {
		printf("Usage: ./unroll <pipeline_string>");
		exit(1);
	}
	char * line = argv[1];

	// Parse the line into commands separated by |
	int command_count = 0;
    while(command_count < MAX_ARGS) {
		if (command_count == 0) token = strtok(line, "|");
		else token = strtok(NULL,"|");
		if (token == NULL) 
			break;
		commands[command_count] = token;
		command_count++;
	}
	commands[command_count] = NULL;

	// Parse the commands into args separated by " "
	for(i=0; i < command_count; i++) {
		command = commands[i];
		int arg_count = 0;
		while(arg_count < MAX_ARGS) {
			if (arg_count == 0) token = strtok(command," ");
			else token = strtok(NULL," ");
			if (token == NULL)
				break;
			pipeline[i][arg_count] = token;
			arg_count++;
		}
		pipeline[i][arg_count] = NULL;
	}
	pipeline[i][0] = NULL;
     
     
	// Execute each command
	for (i = 0; pipeline[i][0] != NULL; i++) {
    
		cpid = fork();
		if (cpid == 0) {
			
			// I am a child process!
			// TODO: Set my PGID to the value saved in 'pgroup'
			setpgid(cpid, pgroup);
			
			// TODO: execute the command with execvp()
			//   The command itself is pipeline[i][0]
			//   The full argv array pointer is pipeline[i]
			//   If execvp fails, raise error and exit
			execvp(pipeline[i][0], pipeline[i]);

			perror("execvp failed");

			return 2;

		} else if (cpid > 0) {
			
			// I am the parent process!
			if (i == 0) { // if first child:
				
				// TODO: set 1st child process PGID to match its own PID
				setpgid(cpid, cpid);
				// TODO: save 1st child's PGID in 'pgroup' 			
				pgroup = getpgid(cpid);
			}

		} else {
			perror("fork");
			_exit(1);

		} // end if
		
	} // end for loop


  //wait for all children in a process group
  while(waitpid(-pgroup, &status, 0) > 0);
	 
  //SUCCESS!
  return 0;
}
