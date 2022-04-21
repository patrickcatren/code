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


function test_part1(){
    echo "--- TEST part1 ---"


    rm -f palindrome
    cmd="gcc -Wall -g palindrome.c -o palindrome 2>&1"
    res=$(eval $cmd)
    utest_z "$res" "Compiles without warnings or errors ($cmd)"

    if [ ! -z "$res" ]
    then
	echo
	echo "--- ERRORS --- "
	echo "$res"
	echo
    fi

    
    
    
    cmd='echo "madamimadam" | ./palindrome  | tr "\n" " "'
    res=$(eval $cmd)
    expect="Enter a string: Palindrome according to check 1 Palindrome according to check 2 "
    utest "$res" "$expect" "Test madamimadam ($cmd)"

    
    cmd='echo "amanaplanacanalpanama" | ./palindrome  | tr "\n" " "'
    res=$(eval $cmd)
    expect="Enter a string: Palindrome according to check 1 Palindrome according to check 2 "
    utest "$res" "$expect" "Test amanaplanacanalpanama ($cmd)"

    cmd='echo "natapalindrome" | ./palindrome  | tr "\n" " "'
    res=$(eval $cmd)
    expect="Enter a string: NOT a palindrome according to check 1 NOT a palindrome according to check 2 "
    utest "$res" "$expect" "Test notapalidrome ($cmd)"
    
    echo
}

function test_part2(){
    echo "--- TEST part2 ---"
 
    rm -f check
    cmd="gcc -Wall -g check.c -o check 2>&1"
    res=$(eval $cmd)
    utest_z "$res" "Compiles without warnings or errors ($cmd)"
    if [ ! -z "$res" ]
    then
	echo
	echo "--- ERRORS --- "
	echo "$res"
	echo
    fi

    cmd='./check 6474562389972756'
    res=$(eval $cmd)
    expect="VALID"
    utest "$res" "$expect" "Test valid credit card number ($cmd)"

    cmd='./check 6474562389972756 >/dev/null; echo $?'
    res=$(eval $cmd)
    expect="0"
    utest "$res" "$expect" "Test valid return value is 0 ($cmd)"

    cmd='./check 6474562389972757 >/dev/null; echo $?'
    res=$(eval $cmd)
    expect="2"
    utest "$res" "$expect" "Test invalid return value is 2 ($cmd)"

    cmd='./check 2>&1'
    res=$(eval $cmd)
    expect="ERROR: require credit card number"
    utest "$res" "$expect" "Test error no arguments ($cmd)"

    cmd='./check >/dev/null 2>.tmp;cat .tmp'
    res=$(eval $cmd)
    expect="ERROR: require credit card number"
    utest "$res" "$expect" "Test error no arguments to stderr ($cmd)"
    rm -f .tmp

    cmd='./check >/dev/null 2>&1 ; echo $?'
    res=$(eval $cmd)
    expect="1"
    utest "$res" "$expect" "Test error bad argument return value is 1 ($cmd)"

    cmd='./check 123452 2>&1'
    res=$(eval $cmd)
    expect="ERROR: Invalid credit card number: Bad Length"
    utest "$res" "$expect" "Test error bad length ($cmd)"

    cmd='./check 123452 >/dev/null 2>.tmp;cat .tmp'
    res=$(eval $cmd)
    expect="ERROR: Invalid credit card number: Bad Length"
    utest "$res" "$expect" "Test error bad length to stderr ($cmd)"
    rm -f .tmp

    cmd='./check 12345 >/dev/null 2>&1; echo $?'
    res=$(eval $cmd)
    expect="1"
    utest "$res" "$expect" "Test error bad length return value is 1 ($cmd)"

    cmd='./check 12345a7890123456 2>&1'
    res=$(eval $cmd)
    expect="ERROR: Invalid credit card number: Bad number 'a'"
    utest "$res" "$expect" "Test error invalid number (1) ($cmd)"

    cmd='./check 123456789b123456 2>&1'
    res=$(eval $cmd)
    expect="ERROR: Invalid credit card number: Bad number 'b'"
    utest "$res" "$expect" "Test error invalid number (2) ($cmd)"

    cmd='./check 123456789b123456 >/dev/null 2>.tmp;cat .tmp'
    res=$(eval $cmd)
    expect="ERROR: Invalid credit card number: Bad number 'b'"
    utest "$res" "$expect" "Test error invalid number to stderr (2) ($cmd)"
    rm -f .tmp

    cmd='./check 123456789b123456 >/dev/null 2>&1; echo $?'
    res=$(eval $cmd)
    expect="1"
    utest "$res" "$expect" "Test error invalid number return value is 1 ($cmd)"

    echo
    echo "Extra Credit Tests"
    card1=$(./rand_creditcard.sh)
    card2=$(./rand_creditcard.sh)

    cmd=' [ "$card1" -eq "$card2" ] ; echo $? '
    res=$(eval $cmd)
    expect="1"
    utest "$res" "$expect" "Random credit card 1 and card 2 not the same ($cmd) ($res)"

    cmd='./check $card1'
    res=$(eval $cmd)
    expect="VALID"
    utest "$res" "$expect" "Random credit card 1 passes ($cmd)"

    cmd='./check $card2'
    res=$(eval $cmd)
    expect="VALID"
    utest "$res" "$expect" "Random credit card 2 passes ($cmd)"
    
    echo
}




if [ ! -z $1 ]
then
    cd $1
else
    cd $(dirname $0)
fi

echo

test_part1
test_part2
