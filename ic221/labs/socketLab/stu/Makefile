CC=gcc
CFLAGS=-g
LDFLAGS=-pthread

all: navycat

navycat: navycat.c 
	$(CC) $(CFLAGS) navycat.c $(LDFLAGS) -o navycat

clean:
	rm navycat *~ $(BIN)
