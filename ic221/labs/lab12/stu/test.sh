#!/bin/bash

red='\e[0;31m'
green='\e[0;32m'
nc='\e[0m'     

base=$(dirname $0)


function tobase(){
    cd $(dirname $0)
}

function ctime(){
    date +"%a %b %d %H:%M:%S %Y"
}

function ucount(){

    python -c "
import sys
d={}
for l in sys.stdin:
   d.setdefault(l.strip(),0) 
   d[l.strip()]+=1
for k in d: 
   print d[k],k
"

}


function utest( ){

    if [ "$1" == "$2" ]
    then
	echo -ne "$green pass $nc"
    else
	echo -ne "$red FAIL $nc" 
    fi
    echo -e "\t\t $3"
}

function utest_ne( ){

    if [ "$1" != "$2" ]
    then
	echo -ne "$green pass $nc"
    else
	echo -ne "$red FAIL $nc" 
    fi
    echo -e "\t\t $3"
}

function utest_nz( ){
    if [ ! -z "$1" ]
    then
	echo -ne "$green pass $nc" 
    else
	echo -ne "$red FAIL $nc" 
    fi
    echo -e "\t\t $2"
}

function utest_z( ){
    if [ -z "$1" ]
    then
	echo -ne "$green pass $nc" 
    else
	echo -ne "$red FAIL $nc" 
    fi
    echo -e "\t\t $2"
}


function test_myhost(){
    echo "--- TEST MYHOST ---"

    make clean >/dev/null 2>/dev/null
    make >/dev/null 2>/dev/null

	# run01
    cmd="./myhost web-ext-vip.erp.usna.edu"
    res=$(eval $cmd)
    expect="web-ext-vip.erp.usna.edu has address 10.4.36.20"
    utest "$res" "$expect" "web-ext-vip.erp.usna.edu ($cmd)"

	# run02
    cmd="./myhost microsoft.com | grep \"has address\" | sort | uniq | wc -l "
    res=$(eval $cmd)
    expect="5"
    utest "$res" "$expect" "find 5 addresses for microsoft.com ($cmd)"

	# run03
    cmd="../myhost microsoft.com | grep \"has address\" | sort | uniq -d 2>&1"
    res=$(eval $cmd)
    expect=""
    utest "$res" "$expect" "addresses don't repeat for microsoft.com ($cmd) ($res) ($expect)"

	# run04
    cmd="./myhost yahoo.com | grep \"has address\" | sort | uniq | wc -l "
    res=$(eval $cmd)
    expect="6"
    utest "$res" "$expect" "find 6 addresses for yahoo.com ($cmd)"

	# run05
    cmd="./myhost yahoo.com | grep \"has address\" | sort | uniq -d"
    res=$(eval $cmd)
    expect=""
    utest "$res" "$expect" "addresses don't repeat for yahoo.com ($cmd)"

	# run07
    cmd="./myhost 2>err 1>/dev/null; cat err; rm -f err"
    res=$(eval $cmd)
    expect="getaddrinfo: Name or service not known"
    utest "$res" "$expect" "Error prints to stderr on failed lookup -- no domain name provided ($cmd)"

	# run08
    cmd="./myhost bad.bad.bad 2>err 1>/dev/null; cat err; rm -f err"
    res=$(eval $cmd)
    expect="getaddrinfo: Name or service not known"
    utest "$res" "$expect" "Error prints to stderr on failed lookup -- bad domain name provided ($cmd)"

	# run09
    echo "=== EXTRA CREDIT ==="
    cmd="./myhost www.cis.upenn.edu | sort | tr \"\\\\n\" \" \" "
    res=$(eval $cmd)
    expect="www.cis.upenn.edu has address 158.130.69.163 www.cis.upenn.edu has IPv6 address 2607:f470:8:64:5ea5::d "
    utest "$res" "$expect" "(ipv6 address for www.cis.upenn.edu) $cmd ($res)"

    echo
}

if [ ! -z $1 ] 
then
    cd $1
else
    cd $(dirname $0)
fi

test_myhost

