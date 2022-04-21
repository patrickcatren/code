#!/bin/bash
#getusers.sh

if [ $1 ]
    then grep "$1" /etc/passwd | cut -d ":" -f 5 | cat

fi
