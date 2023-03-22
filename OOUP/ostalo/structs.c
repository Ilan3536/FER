#include <stdio.h>


typedef struct Animals{
    int ime;
} Animal;


int main()
{


    Animal *pas = (Animal*) malloc(sizeof(Animal));
    Animal* cat = (Animal*) malloc(sizeof(Animal));

    (*pas).ime = 1;
    (*cat).ime = 2;
    

	return 0;
}
