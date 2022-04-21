#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

int main(int argc, char *argv[]){

	struct addrinfo *result;		// to store results
	struct addrinfo *cur_result;    // to store results
	struct addrinfo hints;			// to indicate information we want
	struct sockaddr_in *saddr;		// to reference address (IPv4)
	int s; 							// for error checking
	
	
	//TODO: Complete the lab
	//
	// Outline:
	//   - set up the hints
	memset(&hints,0,sizeof(struct addrinfo));
	hints.ai_family = AF_INET;
	hints.ai_protocol = IPPROTO_TCP;
	//   - perform the getaddrinfo()
	if( (s = getaddrinfo(argv[1], NULL, &hints, &result)) != 0){
    	fprintf(stderr, "getaddrinfo: %s\n",gai_strerror(s));
    	exit(1);
  	}
	//   - iterate over the results
	for(cur_result = result; cur_result != NULL; cur_result = cur_result->ai_next){
		saddr = (struct sockaddr_in *) cur_result->ai_addr;
		printf("%s has address %s\n", argv[1], inet_ntoa(saddr->sin_addr));
	}
	//   - print the resolved ip address
	//   - clean up the results with freaddrinfo()
	freeaddrinfo(result);
}
