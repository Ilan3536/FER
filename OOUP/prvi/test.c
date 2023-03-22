#include <stdio.h>

    typedef char const* (*PTRFUN)();

    char const* dogGreet(void){
        printf("dogGreet");
    }

int main(){
    char const* (*p)()  = dogGreet;
    PTRFUN p1 = dogGreet; 
    const char* (*table[1])() = {dogGreet};
    table[1]();

}

 