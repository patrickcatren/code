#!/bin/bash
#getallsizes.sh

for var in "$@"
do
    if [ -d "$var" ]
    then 
        echo -n "$var "
        ls -l | grep "$var" | tr -s ' ' | cut -d ' ' -f 5 | cat
    elif [ -e "$var" ]
    then
        echo -n "$var "
        ls -l | grep "$var" | tr -s ' ' | cut -d ' ' -f 5 | cat
    else
        if [ "$2" == "2" ]
        then
            echo fuck
        else
            echo ERROR: FILE "$var" DOES NOT EXIST 1>&2
        fi
    fi  
done