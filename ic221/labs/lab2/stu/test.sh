#!/bin/bash

red="\e[0;31m"
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


function test_task1(){

    echo "--- TEST TASK 1 ---"
    
    cmd="./allusers.sh"
    res=$(eval $cmd)
    expect=$(eval $(echo 'Y3V0IC1kICI6IiAtZiA1IC9ldGMvcGFzc3dkCg==' | base64 -d))
    utest "$res" "$expect" "all usernames ($cmd)"

    echo
}

function test_task2(){

    echo "--- TEST TASK 2 ---"
    
    cmd="./getname.sh adina"
    res=$(eval $cmd)
    expect=$(grep "^adina:" /etc/passwd | cut -d ":" -f 5)
    utest "$res" "$expect" "($cmd)"

    cmd="./getname.sh bilzor"
    res=$(eval $cmd)
    expect=$(grep "^bilzor:" /etc/passwd | cut -d ":" -f 5)
    utest "$res" "$expect" "($cmd)"

    cmd="./getname.sh wcbrown"
    res=$(eval $cmd)
    expect=$(grep "^wcbrown:" /etc/passwd | cut -d ":" -f 5)
    utest "$res" "$expect" "($cmd)"

    cmd="./getname.sh roche"
    res=$(eval $cmd)
    expect=$(grep "^roche:" /etc/passwd | cut -d ":" -f 5)
    utest "$res" "$expect" "($cmd)"

    echo
}

function test_task3(){

    echo "--- TEST TASK 3 ---"

    cmd="./getsize.sh 0_empty.txt"
    res=$(eval $cmd)
    expect="0"
    utest "$res" "$expect" "empty test ($cmd)"

    cmd="./getsize.sh 1_medium.txt"
    res=$(eval $cmd)
    expect="1847"
    utest "$res" "$expect" "medium test ($cmd)"

    cmd="./getsize.sh 2_larger.txt"
    res=$(eval $cmd)
    expect="4000"
    utest "$res" "$expect" "larger test ($cmd)"

    let i=$RANDOM%100
    cmd="head -c $i /dev/zero > test.txt ; ./getsize.sh test.txt ; rm -f test.txt"
    res=$(eval $cmd)
    expect="$i"
    utest "$res" "$expect" "random size ($cmd)"

    cmd="./getsize.sh file_does_not_exist 2>&1 | grep -i 'ERROR:.*does not exist.*'"
    res=$(eval $cmd)
    utest_nz "$res" "prints error on nonexistent file ($cmd)"

    cmd="./getsize.sh foobarbaz >/dev/null 2>error.tmp; grep -i 'ERROR:.*foobarbaz.*' error.tmp; rm -f error.tmp"
    res=$(eval $cmd)
    utest_nz "$res" "prints error and filename to stderr ($cmd)"

    echo
}

function test_task4(){

    echo "--- TEST TASK 4 ---"

    cmd="./getallsizes.sh 0_empty.txt"
    res=$(eval $cmd)
    expect="0_empty.txt 0"
    utest "$res" "$expect" "empty test ($cmd)"


    cmd="./getallsizes.sh 1_medium.txt"
    res=$(eval $cmd)
    expect="1_medium.txt 1847"
    utest "$res" "$expect" "medium test ($cmd)"

    cmd="./getallsizes.sh 2_larger.txt"
    res=$(eval $cmd)
    expect="2_larger.txt 4000"
    utest "$res" "$expect" "larger test ($cmd)"

    cmd="./getallsizes.sh 0_empty.txt 2_larger.txt 1_medium.txt | tr '\n' ' '"
    res=$(eval $cmd)
    expect="0_empty.txt 0 2_larger.txt 4000 1_medium.txt 1847 "
    utest "$res" "$expect" "three files test ($cmd)"
    
    let i=$RANDOM%100
    cmd="head -c $i /dev/zero > test.txt ; ./getallsizes.sh test.txt ; rm -f test.txt"
    res=$(eval $cmd)
    expect="test.txt $i"
    utest "$res" "$expect" "random size ($cmd)"

    cmd="./getallsizes.sh 1_medium.txt file_does_not_exist 2_larger.txt 2>&1 | grep -i 'ERROR:.*file_does_not_exist.*'"
    res=$(eval $cmd)
    utest_nz "$res" "prints error and filenmae on nonexistent file ($cmd)"

    cmd="./getallsizes.sh foobarbaz >/dev/null 2>error.tmp; grep -i 'ERROR:.*foobarbaz.*' error.tmp; rm -f error.tmp"
    res=$(eval $cmd)
    utest_nz "$res" "prints error and filename to stderr ($cmd)"

    echo
}

function test_task5(){
    echo "--- TEST TASK 5 ---"

    cmd="./isbiggerthan.sh 0 0_empty.txt"
    res=$(eval $cmd)
    expect="yes"
    utest "$res" "$expect" "empty test 0 ($cmd)"

    cmd="./isbiggerthan.sh 1 0_empty.txt"
    res=$(eval $cmd)
    expect="no"
    utest "$res" "$expect" "empty test 1 ($cmd)"

    cmd="./isbiggerthan.sh 10 1_medium.txt"
    res=$(eval $cmd)
    expect="yes"
    utest "$res" "$expect" "medium test 10 ($cmd)"

    cmd="./isbiggerthan.sh 2000 1_medium.txt"
    res=$(eval $cmd)
    expect="no"
    utest "$res" "$expect" "medium test 2000 ($cmd)"

    cmd="./isbiggerthan.sh 2000 2_larger.txt"
    res=$(eval $cmd)
    expect="yes"
    utest "$res" "$expect" "larger test 2000 ($cmd)"

    cmd="./isbiggerthan.sh 5000 2_larger.txt"
    res=$(eval $cmd)
    expect="no"
    utest "$res" "$expect" "larger test 5000 ($cmd)"

    let i=$RANDOM%100
    cmd="head -c $i /dev/zero > test.txt ; ./isbiggerthan.sh 100 test.txt ; rm -f test.txt"
    res=$(eval $cmd)
    expect="no"
    utest "$res" "$expect" "random size 100 ($cmd)"

    let i=$RANDOM%100+10
    cmd="head -c $i /dev/zero > test.txt ; ./isbiggerthan.sh 10 test.txt ; rm -f test.txt"
    res=$(eval $cmd)
    expect="yes"
    utest "$res" "$expect" "random size 10 ($cmd)"

    cmd="./isbiggerthan.sh 2>&1 | grep -i 'ERROR:.*Require path and size.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error no arguments ($cmd)"

    cmd="./isbiggerthan.sh >/dev/null 2> error.tmp ; grep -i 'ERROR:.*Require path and size.*' error.tmp; rm -f error.tmp"
    res=$(eval $cmd)
    utest_nz "$res" "Error no arguments to stderr ($cmd)"

    cmd="./isbiggerthan.sh num 2_larger.txt 2>&1 | grep -i 'ERROR:.*Require a number.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error not a number ($cmd)"

    cmd="./isbiggerthan.sh num 2_larger.txt >/dev/null 2> error.tmp ; grep -i 'ERROR:.*Require a number.*' error.tmp; rm -f error.tmp"
    res=$(eval $cmd)
    utest_nz "$res" "Error not a number to stderr ($cmd $res)"

    cmd="./isbiggerthan.sh -1 2_larger.txt 2>&1 | grep -i 'ERROR:.*Require a positive number for size.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error not a positive number ($cmd)"

    cmd="./isbiggerthan.sh -1 2_larger.txt >/dev/null 2>error.tmp ; grep -i 'ERROR:.*Require a positive number for size.*' error.tmp; rm -f error.tmp"
    res=$(eval $cmd)
    utest_nz "$res" "Error not a positive number to stderr ($cmd) ($res)"

    cmd="./isbiggerthan.sh 1 doesnotexist.txt 2>&1 | grep -i 'ERROR:.*does not exist.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error file does not exist ($cmd)"    

    echo
}

function test_task6(){
    echo "--- TEST TASK 6 ---"

    cmd="./isbiggerthanall.sh 0 0_empty.txt 2_larger.txt 1_medium.txt | tr -s '\n' ' '"
    res=$(eval $cmd)
    expect="0_empty.txt 2_larger.txt 1_medium.txt "
    utest "$res" "$expect" "bigger than 0 ($cmd)"
    
    cmd="./isbiggerthanall.sh 1 0_empty.txt 2_larger.txt 1_medium.txt | tr -s '\n' ' '"
    res=$(eval $cmd)
    expect="2_larger.txt 1_medium.txt "
    utest "$res" "$expect" "bigger than 1 ($cmd)"

    cmd="./isbiggerthanall.sh 2000 0_empty.txt 2_larger.txt 1_medium.txt | tr -s '\n' ' '"
    res=$(eval $cmd)
    expect="2_larger.txt "
    utest "$res" "$expect" "bigger than 2000 ($cmd)"

    cmd="./isbiggerthanall.sh 9999 0_empty.txt 2_larger.txt 1_medium.txt | tr -s '\n' ' '"
    res=$(eval $cmd)
    expect=""
    utest "$res" "$expect" "bigger than 9999 ($cmd)"
    
    cmd="./isbiggerthanall.sh num 2_larger.txt 2>&1 | grep -i 'ERROR:.*Require a number.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error not a number ($cmd)"

    cmd="./isbiggerthanall.sh -1 2_larger.txt 2>&1 | grep -i 'ERROR:.*Require a positive number.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error negative number ($cmd)"

    cmd="./isbiggerthanall.sh 2>&1 | grep -i 'ERROR:.*Require a size and at least one file.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error no arguments ($cmd)"

    cmd="./isbiggerthanall.sh 1 doesnotexist.txt 2>&1 | grep -i 'ERROR:.*File doesnotexist.txt does not exist.*'"
    res=$(eval $cmd)
    utest_nz "$res" "Error file does not exist ($cmd)"

    echo 
}


if [ ! -z $1 ]
then
    cd $1
else
    cd $(dirname $0)
fi

echo

test_task1
test_task2
test_task3
test_task4
test_task5
test_task6

