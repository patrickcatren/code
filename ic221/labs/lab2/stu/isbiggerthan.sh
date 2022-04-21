#!/bin/bash
#isbiggerthan.sh

declare -i i

if [ -z $1 ] && [ -z $2 ]
then
    echo "ERROR: Require path and size" 1>&2
    exit
else
    if [ $1 -lt 0 ] #if it is negative
    then
        echo "ERROR: Require a positive number for size" 1>&2
        exit
    else
        if [ "$1" -eq "$1" ] # check if it's a number 
        then
            if [ -e "$2" ] # check if it's a file 
            then
                i=$(ls -l | grep "$2" | tr -s ' ' | cut -d ' ' -f 5)
                if [ $i -gt $1 ] || [ $i == $1 ]
                then
                    echo "yes"
                else
                    echo "no"
                fi
            else
                echo "ERROR: File "$2" does not exist" 1>&2
                exit
            fi
        else
            echo "ERROR: Require a number for size" 1>&2
        fi
    fi
fi
