#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef char const* (*PTRFUN)();

struct Animal{
  PTRFUN* vtable;
  char const* name;

  // vtable entries:
  // 0: char const* name(void* this);
  // 1: char const* greet();
  // 2: char const* menu();
};

char const* name(struct Animal* this){
    return this->name;
}

char const* greet(){
    return "Dobar dan dragi korisnice!";
}

char const* menu(){
    return "sjemenke";
}
PTRFUN parrotTable[3] = {name, greet, menu};

void construct(struct Animal *animal, char const* animalName){
    animal->vtable = parrotTable;
    animal->name = animalName;
}

struct Animal* create(char const* animalName){
    struct Animal *memoryParrot = (struct Animal*) malloc(sizeof(struct Animal*));
    construct(memoryParrot, animalName);
    return memoryParrot;
} 