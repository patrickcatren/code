#include <stdio.h>
#include<stdlib.h>

int main(){
    printf("%ld", sizeof(int));
    printf("%ld", sizeof(char));
    printf("%ld", sizeof(int*));
    printf("%ld", sizeof(float*));
    printf("%ld", sizeof(char*));
    printf("%ld", sizeof(short));
    printf("%ld", sizeof(int**));
    printf("%ld", sizeof(float));
    printf("%ld", sizeof(double));

    return 0;
}