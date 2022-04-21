/*enternumber.c*/
#include <stdio.h>

int main(int argc, char * argv[]){
	int num;
	
	printf("Enter a number\n");
	scanf("%d", &num); //use &num to store 
					   //at the address of num
	
	printf("You entered %d\n", num);
}
