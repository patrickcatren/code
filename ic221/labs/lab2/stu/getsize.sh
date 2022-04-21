#!/bin/bash
#getsize.sh

if [ -d "$1" ]
then 
    ls -l | grep "$1" | tr -s ' ' | cut -d ' ' -f 5 | cat
elif [ -e "$1" ]
then
    ls -l | grep "$1" | tr -s ' ' | cut -d ' ' -f 5 | cat
else
    if [ "$2" == "2" ]
    then
        exit
    else
        echo ERROR: FILE "$1" DOES NOT EXIST 1>&2
    fi
fi  
