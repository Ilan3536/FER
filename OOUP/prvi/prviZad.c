#include <stdio.h>
#include <stdlib.h>

typedef char const* (*PTRFUN)();
typedef struct Animal {
    char const* name;
    PTRFUN* pfun;
} Animal;

char const* dogGreet(void){
  return "vau!";
}
char const* dogMenu(void){
  return "kuhanu govedinu";
}
char const* catGreet(void){
  return "mijau!";
}
char const* catMenu(void){
  return "konzerviranu tunjevinu";
}

PTRFUN catTable[2] = {catGreet, catMenu};
PTRFUN dogTable[2] = {dogGreet, dogMenu};

void animalPrintGreeting(Animal *animal){
    printf("%s pozdravlja: %s\n", animal->name, animal->pfun[0]());
}
void animalPrintMenu(Animal *animal){
    printf("%s voli %s\n", animal->name, animal->pfun[1]());
}

void constructDog(Animal *animal, char const* animalName){
    animal->name = animalName;
    animal->pfun = dogTable;
}
void constructCat(Animal *animal, char const* animalName){
    animal->name = animalName;
    animal->pfun = catTable;
}  

Animal* createDog(char const* animalName){
    Animal *memoryDog = (Animal*) malloc(sizeof(Animal));
    constructDog(memoryDog, animalName);
    return memoryDog;
} 
Animal* createCat(char const* animalName){
    Animal *memoryCat = (Animal*) malloc(sizeof(Animal));
    constructCat(memoryCat, animalName);
    return memoryCat;
}

void createNDogs(int n){
    Animal *memoryNDogs = (Animal*) malloc(n * sizeof(Animal));
    for(int i=0; i<n; i++){
        char const* name = "dog";
        printf(name);
        constructDog(memoryNDogs+i, name);
    }
}

void testAnimals(){

    Animal* p1=createDog("Hamlet");
    Animal* p2=createCat("Ofelija");
    Animal* p3=createDog("Polonije");

    animalPrintGreeting(p1);
    animalPrintGreeting(p2);
    animalPrintGreeting(p3);
    animalPrintMenu(p1);
    animalPrintMenu(p2);
    animalPrintMenu(p3);

    free(p1); free(p2); free(p3);
}

int main(){ 
    testAnimals();

    //createNDogs(10);
    
    return 0;
}





