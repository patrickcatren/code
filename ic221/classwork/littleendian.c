#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]){

  unsigned int a = 0x1c2a55;    //hex number
  unsigned char * p = (unsigned char *) &a; 

  int i;
  printf("0x");
  for(i=0;i<4;i++){
    printf("%02x",p[i]);
  }
  printf("\n");
}

