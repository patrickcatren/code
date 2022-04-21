#!/bin/bash

red='\e[0;31m'
green='\e[0;32m'
nc='\e[0m'     

base=$(dirname $0)

export CFLAGS="-Wall -Wextra -Wno-unused"

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

function test_compilation(){
    echo "--- TEST Compiler ---"

    rm -f manipulation
    cmd="gcc -Wall -g manipulator.c -o manipulator 2>&1"
    
    res=$(eval $cmd)
    utest_z "$res" "Compiles without warnings or errors"
    if [ ! -z "$res" ]
    then
	echo
	echo "--- ERRORS --- "
	echo "$res"
	echo
    fi
    echo
}


function test_part1(){
    echo "--- TEST part1 ---"
 
    cmd="echo a | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: ERROR: Invalid input "
    utest "$res" "$expect" "Error on invalid input (length) ($cmd)"

    cmd="echo 5 1 2 a d a m | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): ERROR: Invalid input "
    utest "$res" "$expect" "Error on invalid input (numbers 5) ($cmd)"

    cmd="echo 3 1 a 2 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 3 numbers (space separated): ERROR: Invalid input "
    utest "$res" "$expect" "Error on invalid input (numbers 3) ($cmd)"

    cmd="echo 5 1 2 3 4 5 a | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array ERROR: Invalid input. Choose again. Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Error on invalid input (choice a) ($cmd)"

    cmd="echo 5 1 2 3 4 5 a a | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array ERROR: Invalid input. Choose again. Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array ERROR: Invalid input. Choose again. Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Error on invalid input (choice a a) ($cmd)"

    cmd="echo 5 1 2 3 4 5 0 a | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Exit on zero ($cmd)"
    echo
}

function test_part2(){
    echo "--- TEST part2 ---"
 
    cmd="echo 5 1 2 3 4 5 1 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 1 2 3 4 5 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Print array 1 ($cmd)"

    cmd="echo 5 5 4 3 2 1 1 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 5 4 3 2 1 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Print array 2 ($cmd)"

    cmd="echo 2 100 200 1 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 2 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 100 200 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Print array 3 ($cmd)"

    echo
}



function test_part3(){
    echo "--- TEST part3 ---"

    cmd="echo 5 0 1 2 3 4 1 2 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 4 3 2 1 0 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Reverse Array 1 ($cmd)"

    cmd="echo 5 0 1 2 3 4 1 2 2 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 4 3 2 1 0 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Reverse Array 2 ($cmd)"

    echo
}

function test_part4(){
    echo "--- TEST part4 ---"

    cmd="echo 5 0 1 2 3 4 1 3 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 2 3 0 4 1 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Randomize Array 1 ($cmd)"

    cmd="echo 5 0 1 2 3 4 1 3 3 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 2 3 0 4 1 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 2 3 1 4 0 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Randomize Array 2 ($cmd)"

    echo
}

function test_part5(){
    echo "--- TEST part5 ---"

    cmd="echo 5 4 3 2 1 0 1 4 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 4 3 2 1 0 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Sort Array 1 ($cmd)"

    cmd="echo 5 0 3 4 1 2 1 4 0 | ./manipulator | tr '\n' ' ' "
    res=$(eval $cmd)
    expect="Enter the length: Enter 5 numbers (space separated): Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 3 4 1 2 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array { 0 1 2 3 4 }  Choose an operation: (0) : exit (1) : print array (2) : reverse array (3) : randomize array (4) : sort array "
    utest "$res" "$expect" "Sort Array 2 ($cmd)"

    echo
}
if [ ! -z $1 ]
then
    cd $1
else
    cd $(dirname $0)
fi

echo

test_compilation
test_part1
test_part2
test_part3
test_part4
test_part5
