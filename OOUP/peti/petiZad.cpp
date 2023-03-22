#include <stdio.h>

class B{
public:
  virtual int __cdecl prva()=0;
  virtual int __cdecl druga(int)=0;
};

class D: public B{
public:
  virtual int __cdecl prva(){return 42;}
  virtual int __cdecl druga(int x){return prva()+x;}
};


void manualCallOfVirtualfunction(B *d){

    size_t* vtable =  *((size_t**) d); 

    int (*pfun1)(B*) = (int (*)(B*))  vtable[0];
    int (*pfun2)(B*, int) = (int (*)(B*, int)) vtable[1];

    printf("%d\n", pfun1(d));
    printf("%d", pfun2(d, 4));

}

int main(){

    B *d = new D();
    manualCallOfVirtualfunction(d);

    return 0;

}