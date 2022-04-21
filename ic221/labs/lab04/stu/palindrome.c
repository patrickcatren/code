#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int check1(char * str);
int check2(char * str1);

int check1(char *str){
  //TODO: Complete by iterating from front-to-back and back-to-front
  //      to check for a palindrome
  int length = strlen(str);
  int tof = 0;
  for(int i = 0; i < (length/2); i++)
  {
    if(str[i] == str[length - i -1])
    {
        tof = 1;
    }
    else
    {
        tof = 0;
    }
    if(tof == 0)
    {
      return 0;
    }
  }
  return 1;
}

int check2(char *str1){
  char str2[1024]; //string to copy to

  //TODO: Complete by copying str1 to str2, backwards, and then
  //      checking that str1 and str2 are the equal using strcmp()
  int length = strlen(str1);
  for(int i = 0; i < length; i++)
  {
    str2[length -1 -i] = str1[i];
  }
  int tof = strcmp(str1, str2);

  if(tof == 0){return 1;}
  else{return 0;}
}

int main(int argc, char * argv[]){

  char str[1024];

  printf("Enter a string:\n");

  scanf("%1023s",str);

  if(check1(str)){
    printf("Palindrome according to check 1\n");
  }else{
    printf("NOT a palindrome according to check 1\n");
  }
   

  if(check2(str)){
    printf("Palindrome according to check 2\n");
  }else{
    printf("NOT a palindrome according to check 2\n");
  }
  
}
