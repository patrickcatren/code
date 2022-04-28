CC=gcc
CFLAGS=-g -Wall
SRC=write-log.c read-log.c
BIN=$(SRC:.c=)

all: $(BIN)
	rm -f log.txt
	touch log.txt
	chmod 600 log.txt
	chmod u+s read-log
	chmod u+s write-log

clean:
	rm -f $(BIN) *~
