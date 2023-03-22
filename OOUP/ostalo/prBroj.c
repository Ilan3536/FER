#include <stdio.h>
#include <stdlib.h>

struct Broj;
typedef struct BrojVtable{
    void (*uvecaj_pa_reciprociraj)(struct Broj*, int);
}BVT;

struct Broj{
    const BVT* vt;
};

struct CijeliBroj{
    struct Broj super;
    int broj;
};

struct RacionalniBroj{
    struct Broj super;
    int brojnik;
    int nazivnik;
};

void RB_uvecaj_pa_reciprociraj(struct Broj* pb, int x){
    struct RacionalniBroj* rbp = (struct RacionalniBroj*) pb;
    rbp->brojnik += x * rbp->nazivnik;
    int tmp = rbp->nazivnik;
    rbp->nazivnik = rbp->brojnik;
    rbp->brojnik = tmp;
}

void CB_uvecaj_pa_reciprociraj(struct Broj* pb, int x){
    struct CijeliBroj* cbp = (struct CijeliBroj*) cbp;
    cbp->broj += x;
    cbp->broj = 1 / cbp->broj; // ne vidim poantu ako su cijeli brojevi?
}                              // uvijek ce biti broj [0, 1] zaokruzen na int

void uvecaj_pa_reciprociraj(struct Broj* pb, int x){
    pb->vt->uvecaj_pa_reciprociraj(pb, x);
}                          

const BVT RBvt = {RB_uvecaj_pa_reciprociraj};
const BVT CBvt = {CB_uvecaj_pa_reciprociraj};

void constructRacionalniBroj(struct RacionalniBroj* cbp, int b, int n){
    cbp->brojnik = b;
    cbp->nazivnik = n;
    cbp->super.vt = &RBvt;
}

struct Broj* createRacionalniBrojevi(int* brojnici, int* nazivnici, int n){
    struct RacionalniBroj* memorija = (struct RacionalniBroj*) malloc(n*sizeof(struct RacionalniBroj));
    for(int i=0; i<n; i++){
        constructRacionalniBroj(memorija+i,brojnici[i], nazivnici[i]);
    }
    return (struct Broj*) memorija;
}

int main(void){
    int brojnici[] = {1, 2, 3};
    int nazivnici[] = {2, 3, 4};
    struct Broj* brojevi = createRacionalniBrojevi(brojnici, nazivnici, 3);
    struct RacionalniBroj* prvi = (struct RacionalniBroj*) brojevi;
    printf("%d %d %d", prvi[0].nazivnik, prvi[1].brojnik, prvi[2].brojnik);

    return 0;
}