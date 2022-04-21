CC=gcc
CFLAGS=-g -Wall
LDFLAGS=-lreadline

all: unroll pipe0 pipe1 pipe2 pipe3 pipe4 pipe5

unroll:unroll.c
	$(CC) $(CFLAGS) unroll.c -o unroll $(LDFLAGS)	

pipe1:pipe.c
	@ $(CC) $(CFLAGS) pipe.c -o pipe
	@ ./pipe "cat sample-db.csv | cut -d , -f 8 | sort | uniq | wc -l"

pipe2:pipe.c
	@ $(CC) $(CFLAGS) -DPIPE=2 pipe.c -o pipe
	@ ./pipe "cat sample-db.csv | cut -d , -f 10 | cut -d - -f 1 | sort | uniq |wc -l"

pipe3:pipe.c
	@ $(CC) $(CFLAGS) -DPIPE=3 pipe.c -o pipe
	@ ./pipe "cat sample-db.csv | cut -d , -f 5 | cut -d - -f 1 | sort | uniq |wc -l"

pipe4:pipe.c
	@ $(CC) $(CFLAGS) -DPIPE=4 pipe.c -o pipe
	@ ./pipe "cat sample-db.csv | tail"

pipe5:pipe.c
	@ $(CC) $(CFLAGS) -DPIPE=5 pipe.c -o pipe 
	@ ./pipe "head sample-db.csv"

clean_pipe:
	rm -f pipe
	
clean_unroll:
	rm -f unroll
