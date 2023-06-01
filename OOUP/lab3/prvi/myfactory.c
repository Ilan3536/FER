#include "myfactory.h"

#include <stdio.h>
#include <stdlib.h>
#include <windows.h>


void* myfactory(char const* libname, char const* ctorarg){
    
    HINSTANCE lib = LoadLibrary(libname);

    if (!lib){
      printf("Failed to load shared dynamic library.");
      return NULL;
    }

    FARPROC ctor = GetProcAddress(lib, "create");

    if (!ctor){
      printf("Failed to find constructor function.");
      FreeLibrary(lib);
      return NULL;
    }
    
    struct Animal* animal = ((struct Animal* (*) (char const*)) ctor)(ctorarg);


    return animal;
}

