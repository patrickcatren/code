/* string-array.c */
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

  char * strings[]={"Go Navy!",
                     "Beat Army!",
                     "Crash Airforce!",
                     "Destroy the Irish!"};
  int i;

  printf("strings: %p\n",strings);
  for(i=0;i<4;i++){
    printf("strings[%d]: '%s' %p\n",i,strings[i],strings[i]);
  }
}
