#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef int (*PTRFUN)();
typedef void (*PTRFUNVOID)();

typedef struct Base {
    PTRFUNVOID* vptr;
    int m_;
} Base;

void setm(Base* this, int x){
    this->m_ = x;
}
PTRFUNVOID BaseVT[1] = {setm};

void BaseConstructor(Base* base){
    base->vptr = BaseVT;
    base->m_ = 0;
}

typedef struct Worker {
    PTRFUN* vptr;
} Worker;

int b(Worker* this){
    return 42;
}

PTRFUN WorkerVT[1] = {b};

void WorkerConstructor(Worker* worker){
    worker->vptr = WorkerVT;
} 

void ClientConstructor(Base* base, Worker* pw){
    BaseConstructor(base);
    base->vptr[0](base, pw->vptr[0](pw));
}

int main(){
    Worker w;
    WorkerConstructor(&w);

    Base c;
    ClientConstructor(&c, &w);

    printf("%d", c.m_);

}

