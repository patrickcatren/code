#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

void wcfile(int mode, char* filename, int* wtotal, int* ltotal, int* ctotal);

void wcstd(int mode, int* wtotal, int* ltotal, int* ctotal);

int main(int argc, char * argv[]){
    int mode = 1;
    int count = 0;
    int wordtotal = 0;
    int linetotal = 0;
    int chartotal = 0;
    int stilloptions = 1; //indicates if it has finished scanning for options
    int works = 0; //acts as a bool to show if command line arg was valid or not

    for(int i = 1; i < argc; i++) {
        if(argv[i][0] == '-' && stilloptions){
            //set works to 0, add if it does work
            works = 0;
            //sets options mode
            if(argv[i][1] == 'l'){
                if(mode == 3){
                    mode = 5;
                }
                else if(mode == 4){
                    mode = 6;
                }
                else if(mode ==1){
                    mode = 2;
                }
                works++;
            }
            if(argv[i][1] == 'w'){
                if(mode == 2){
                    mode = 5;
                }
                else if(mode == 4){
                    mode = 7;
                }
                else if(mode ==1){
                    mode = 3;
                }
                works++;
            }
            if(argv[i][1] == 'c'){
                if(mode == 2){
                    mode = 6;
                }
                else if(mode == 3){
                    mode = 7;
                }
                else if(mode ==1){
                    mode = 4;
                }
                works++;
            }
            if(works == 0){
                fprintf(stderr, "%s %s \n", "ERROR: unknown option ", argv[i]);
                exit(2);
            }
        }
        //WHERE THE ACTUAL FILES ARE PARSED
        else{
            stilloptions = 0;
            if(argv[i][0] == '+'){
                //GRAB FROM STDIN
                wcstd(mode, &wordtotal, &linetotal, &chartotal);
                count++;
            }
            else{
                wcfile(mode, argv[i], &wordtotal, &linetotal, &chartotal);
                count ++;
            }
        }
    }
    if(count > 0){
        int totaltotal = wordtotal + linetotal + chartotal;
        if(mode == 1){
            printf("%s %i %i %i\n", "total", linetotal, wordtotal, chartotal);
        }
        if(mode == 2){
            printf("%s %i\n", "total", linetotal);
        }
        if(mode == 3){
            printf("%s %i\n", "total", wordtotal);
        }
        if(mode == 4){
            printf("%s %i\n", "total", chartotal);
        }
        if(mode == 5){
            //l + w
            printf("%s %i %i\n", "total", linetotal, wordtotal);
        }
        if(mode == 6){
            //l + c
            printf("%s %i %i\n", "total", linetotal, chartotal);
        }
        if(mode == 7){
            //w + c
            printf("%s %i %i\n", "total", wordtotal, chartotal);
        }
    }
}

void wcfile(int mode, char* filename, int* wtotal, int* ltotal, int* ctotal){
    FILE * infile = fopen(filename, "r");
    if(infile == NULL){
        fprintf(stderr, "%s %s %s", "ERROR: file", filename, "cannot be opened\n");
        return;
    }
    int words = 0;
    int lines = 0;
    int characters = 0;
    char a = fgetc(infile);
    char prevchar = ' ';
    int wordbegun;
    while(a != EOF){
        if(a == '\n'){
            lines++;
        }
        if((isspace(prevchar)) && !(isspace(a))){
            wordbegun = 1;
        }
        if((wordbegun ==1) && isspace(a)){
            words++;
            wordbegun = 0;
        }
        characters++;
        prevchar = a;
        a = fgetc(infile);
    }
    *wtotal += words;
    *ltotal += lines;
    *ctotal += characters;
    if(mode == 1){
        printf("%s %i %i %i\n", filename, lines, words, characters);
    }
    if(mode == 2){
        printf("%s %i\n", filename, lines);
    }
    if(mode == 3){
        printf("%s %i\n", filename, words);
    }
    if(mode == 4){
        printf("%s %i\n", filename, characters);
    }
    if(mode == 5){
        //l + w
        printf("%s %i %i\n", filename, lines, words);
    }
    if(mode == 6){
        //l + c
        printf("%s %i %i\n", filename, lines, characters);
    }
    if(mode == 7){
        //w + c
        printf("%s %i %i\n", filename, words, characters);
    }
}

void wcstd(int mode, int* wtotal, int* ltotal, int* ctotal){
    int words = 0;
    int lines = 0;
    int characters = 0;
    char a = fgetc(stdin);
    char prevchar = ' ';
    int wordbegun;
    while(a != EOF){
        if(a == '\n'){
            lines++;
        }
        if((isspace(prevchar)) && !(isspace(a))){
            wordbegun = 1;
        }
        if((wordbegun ==1) && isspace(a)){
            words++;
            wordbegun = 0;
        }
        characters++;
        prevchar = a;
        a = fgetc(stdin);
    }
    *wtotal += words;
    *ltotal += lines;
    *ctotal += characters;
    if(mode == 1){
        printf("%s %i %i %i\n", "-stdin-", lines, words, characters);
    }
    if(mode == 2){
        printf("%s %i\n", "-stdin-", lines);
    }
    if(mode == 3){
        printf("%s %i\n", "-stdin-", words);
    }
    if(mode == 4){
        printf("%s %i\n", "-stdin-", characters);
    }
    if(mode == 5){
        //l + w
        printf("%s %i %i\n", "-stdin-", lines, words);
    }
    if(mode == 6){
        //l + c
        printf("%s %i %i\n", "-stdin-", lines, characters);
    }
    if(mode == 7){
        //w + c
        printf("%s %i %i\n", "-stdin-", words, characters);
    }
}
