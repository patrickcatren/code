CC=gcc
CFLAGS=-g -Wall
LDFLAGS=-lreadline

all: mini-sh timer

mini-sh:mini-sh.c
	gcc $(CFLAGS) -o mini-sh mini-sh.c -lreadline

timer:timer.c
	gcc $(CFLAGS) -o timer timer.c -lreadline

clean:
	rm -f mini-sh timer
