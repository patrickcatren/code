#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char * argv[]){
  //TODO: Complete the program 
  //   printf("%d ", arrsize);
  //ERROR string for you to use to pass automated tests (print stderrr!)
  //  -- "ERROR: require credit card number\n"
  //  -- "ERROR: Invalid credit card number: Bad Length\n"
  //  -- "ERROR: Invalid credit card number: Bad number '%c'\n"
  if(argc != 2){
    fprintf(stderr, "ERROR: require credit card number\n");
    return 1;
  }
    int length = strlen(argv[1]);
  if(length != 16){
    fprintf(stderr, "ERROR: Invalid credit card number: Bad Length\n");
    return 2;
  }
  for(int i = 0; i < 16; i++){
      if(argv[1][i] > '9' || argv[1][i] < '0'){
        fprintf(stderr, "ERROR: Invalid credit card number: Bad number '%c'\n", argv[1][i]);
        return 2;
      }
  }


  int Array[16];
  for(int i = 0; i < 16; i++){
    Array[i] = argv[1][i] - 48;
  }
  for(int i = 0; i < 16; i++){
    if(i%2==0)
    {
      Array[i] = (Array[i]*2);
    }
  }
  for(int i = 0; i < 16; i++){
    if(i%2==0 && Array[i]>9)
    {
      Array[i] = (Array[i]/10)+(Array[i]%10);
    }
  }
  int sum = 0;
  for(int i = 0; i < 16; i++){
    sum = sum + Array[i];
  }
  if(sum%10 == 0){
    printf("VALID\n");
  }
  else{
    printf("INVALID\n");
  }

}
