#include <stdio.h>
#define STRLEN 12

int main() {

	int i;
	char s1[STRLEN];
	int s2[STRLEN] = {0xB1, 0xB6, 0xB1, 0xBA, 0xAB, 0xA6, 0xAB, 0xA8, 0xB0, 0xDE, 0xDE, 0};
	
	for (i=0; i<STRLEN; i++) {
		s1[i] = ~s2[i];
	}
	
	for (i=0; i<STRLEN; i++) {
		s1[i] = ~s2[i];
	}	
	
	return 0;
}
