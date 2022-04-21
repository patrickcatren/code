#include <stdio.h>
#include <stdlib.h>

int main(){
    int a[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int b[10];

    //copy from a to b
    b = a; 

    b[3] = 0;
    for(int i=0; i<10; i++){
        printf("%d ", a[i]);
    } 

}