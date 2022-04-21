#include <stdio.h>
#include <stdlib.h>

int main(int argc, char * argv[]){

    FILE * gonavy = fopen("gonavy.txt", "w");
    FILE * beatarmy = fopen("beatarmy.txt", "w");

    fprintf(gonavy, "Go Navy");

    fprintf(beatarmy, "Beat Army");

    fprintf(stderr, "Crash Airforce");

    fclose(gonavy);
    fclose(beatarmy);
}