#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct Broj;
typedef void (*PTRFUN)(struct Broj* pb,int x);

typedef struct Broj {
    PTRFUN* pfun;
} Broj;

typedef struct CijeliBroj {
    struct Broj baseClass;
    int broj;
} CijeliBroj;

typedef struct RacionalniBroj {
    struct Broj baseClass;
    int brojnik;
    int nazivnik;
} RacionalniBroj;

void uvecajCijeli(Broj* broj, int x){
    CijeliBroj* cijeli = (CijeliBroj*) broj;
    cijeli->broj += x;
    cijeli->broj = 1 / cijeli->broj;
}

void uvecajRac(Broj* broj, int x){
    RacionalniBroj* pcrb = (RacionalniBroj*) broj;
    pcrb->brojnik += x * pcrb->nazivnik;
    int tmp = pcrb->nazivnik;
    pcrb->nazivnik = pcrb->brojnik;
    pcrb->brojnik = tmp;

}

void uvecaj_pa_reciprociraj(Broj* broj, int x){
    broj->pfun[0](broj, x);
}

PTRFUN BrojVT[1] = {uvecaj_pa_reciprociraj};
PTRFUN RacVT[1] = {uvecajRac};
PTRFUN CijeliVT[1] = {uvecajCijeli};

void constructRacionalniBroj(RacionalniBroj* rac, int br, int naz){
    rac->baseClass.pfun = RacVT;
    rac->brojnik = br;
    rac->nazivnik = naz;
}
void constructCijeliBroj(CijeliBroj* cij, int broj ){
    cij->baseClass.pfun = CijeliVT;
    cij->broj = broj;
}

Broj* createBroj( int br, int naz){
    RacionalniBroj* memory = (RacionalniBroj*) malloc(sizeof(RacionalniBroj*));
    constructRacionalniBroj(memory, br, naz );
    return (Broj*) memory;
}


int main(){

    Broj* broj1 = createBroj(1,2);
    Broj* broj2 = createBroj(5,3);

    RacionalniBroj* rac1 = (RacionalniBroj*) broj1;
    RacionalniBroj* rac2 = (RacionalniBroj*) broj2;

    printf("%d/%d\n", rac1->brojnik, rac1->nazivnik);
    printf("%d/%d\n", rac2->brojnik, rac2->nazivnik);

    uvecaj_pa_reciprociraj(broj1, 4);
   
    uvecaj_pa_reciprociraj(broj2, 4);

    printf("%d/%d\n", rac1->brojnik, rac1->nazivnik);
    printf("%d/%d\n", rac2->brojnik, rac2->nazivnik);

}

