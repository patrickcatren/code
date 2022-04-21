#!/bin/bash
export CFLAGS="-Wall -Wextra -Wno-unused"
red='\e[0;31m'
green='\e[0;32m'
nc='\e[0m'     

base=$(dirname $0)


function tobase(){
    cd $(dirname $0)
}

function utest( ){

    if [ "$1" == "$2" ]
    then
	echo -ne "$green pass $nc"
    else
	echo -ne "$red FAIL $nc" 
    fi
    echo -ne "\t\t "
    echo "$3"
}

function utest_ne( ){

    if [ "$1" != "$2" ]
    then
	echo -ne "$green pass $nc"
    else
	echo -ne "$red FAIL $nc" 
    fi
    echo -ne "\t\t "
    echo "$3"
}

function utest_nz( ){
    if [ ! -z "$1" ]
    then
	echo -ne "$green pass $nc" 
    else
	echo -ne "$red FAIL $nc" 
    fi

    echo -ne "\t\t "
    echo "$2"
}

function utest_z( ){
    if [ -z "$1" ]
    then
	echo -ne "$green pass $nc" 
    else
	echo -ne "$red FAIL $nc" 
    fi

    echo -ne "\t\t "
    echo "$2"
}


function test_makefile_simple(){
    echo "--- TEST MAKEFILE SIMPLE ---"
    
    rm -f compileme

    make -f Makefile_Simple 1>/dev/null 2>&1
    cmd="ls -l compileme 2>/dev/null | cut -d \" \" -f 1 | grep x"
    res=$(eval $cmd)
    utest_nz "$res" "make -f Makefile_Simple ($cmd)"

    cmd="./compileme | grep \"You compiled me!\""
    res=$(eval $cmd)
    #expect="You compiled me! .... Hurray!"
    utest_nz "$res" "./compileme correct execution ($cmd)"

    make -f Makefile_Simple clean 1>/dev/null 2>&1
    cmd="ls compileme 2>/dev/null"
    res=$(eval $cmd)
    utest_z "$res" "make -f Makefile_Simple clean ($cmd)"

    
    echo
}

function test_makefile_multi(){
    echo "--- TEST MAKEFILE MULTI ---"
    
    rm -f runme runme_too *.o *~

    make -f Makefile_Multi runme >/dev/null 2>&1
    cmd="ls -l runme | cut -d \" \" -f 1 | grep x"
    res=$(eval $cmd)
    utest_nz "$res" "make -f Makefile_Multi runme ($cmd)"

    cmd="ls runme.o library.o | grep \"ls: cannot access\""
    res=$(eval $cmd)
    utest_z "$res" "object files ($cmd)"

    cmd="./runme | grep \"\!dlroW olleH\""
    res=$(eval $cmd)
    utest_nz "$res" "runme expected execution ($cmd)"

    rm -f runme runme_too *.o *~
    make -f Makefile_Multi runme_too >/dev/null 2>&1
    cmd="ls -l runme_too | cut -d \" \" -f 1 | grep x"
    res=$(eval $cmd)
    utest_nz "$res" "make -f Makefile_Multi runmetoo ($cmd)"

    cmd="./runme_too | grep \"\!d\!r0W 0\!\!3H\""
    res=$(eval $cmd)
    utest_nz "$res" "runme_too expected execution ($cmd)"

    rm -f runme runme_too *.o *~
    make -f Makefile_Multi >/dev/null 2>&1
    cmd="ls -l runme | cut -d \" \" -f 1 | grep x"
    res=$(eval $cmd)
    utest_nz "$res" "make all -runme- ($cmd)"

    cmd="ls -l runme_too | cut -d \" \" -f 1 | grep x"
    res=$(eval $cmd)
    utest_nz "$res" "make -f Makefile_Multi all -runme_too- ($cmd)"

    cmd="./runme | grep \"\!dlroW olleH\""
    res=$(eval $cmd)
    utest_nz "$res" "make -f Makefile_Multi all -runme expected execution- ($cmd)"

    cmd="./runme_too | grep \"\!d\!r0W 0\!\!3H\""
    res=$(eval $cmd)
    utest_nz "$res" "make -f Makefile_Multi all -runme_too expected execution- ($cmd)"

    make -f Makefile_Multi clean >/dev/null 2>&1
    cmd="ls runme runme_too runme.o library.o fun.o runme_too.o 2>&1 | wc -l "
    res=$(eval $cmd)
    expect="6"
    utest "$res" "$expect" "make -f Makefile_Multi clean ($cmd)"

    rm -f runme runme_too *.o *~

    
    echo
}


if [ ! -z $1 ]
then
    cd $1
else
    cd $(dirname $0)
fi


echo

test_makefile_simple
test_makefile_multi
