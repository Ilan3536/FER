#include <stdio.h>

class Base{
public:
  //if in doubt, google "pure virtual"
  virtual void set(int x)=0;
  virtual int get()=0;
};
class PlainOldClass{
public:
  void set(int x){x_=x;};
  int get(){return x_;};
private:
  int x_;
};
class CoolClass: public Base{
public:
  virtual void set(int x){x_=x;};
  virtual int get(){return x_;};
private:
  int x_;
};
int main(){
  PlainOldClass poc;
  Base* pb=new CoolClass;
  poc.set(42);
  pb->set(42);

  printf("%d", sizeof(size_t)); //pointer velicine 8B
}  
/*
ptr zauzima 8
142-145 pointer na objekt, pointer na vtable, pointer na vtable[0], pointer na objekt
1. poc 138-141, pb 132-137
rsp - vrh stoga, rbp - dno stoga
2. PlainOldClass se stvara na stogu, a CoolClass se stvara na heapu(pomoću operatora new)
3. Ne postoji takav poziv
4. U konstruktoru od CoolClass(94) prvo se poziva konstruktor od Base(73), postavi se njegova vtablica(81),
zatim se postavi vtablica od CoolClass(107), u rax se spremi pointer na vtable
5. Stvaranjem objekta s operatorom new stvaramo objekt na heapu pomocu konstruktora
te se definira i inicijalizira virtualna tablica funkcija. Kad pozovemo metodu pb->set() (odnosi se na CoolClass)
već imamo inicijaliziranu funkciju i trebamo je samo pozvat. Stvaranjem objekta na stogu NE ovo se ne izvodi, nego
se prilikom poziva funckije poc.set() (PlainOldClass) tek definira funkcija. Stoga poziv pomoću -> zahtjeva manje instrukcija
jer je funkcija već definirana i inicijalizirana
za prvi poziv ne treba kod s instrukcijom call
6. definicija  vtablice (159), inicijalizacija

3SetEi i-prima integer za funkcije
C2Ev - konstruktor
*/