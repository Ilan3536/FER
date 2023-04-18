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
1. poc 141, pb 142-145 je dinamički polimorfizam call r8 - 148 je poziv set
call _Znwy je poziv operatora new
rsp - vrh stoga, rbp - dno stoga

2. PlainOldClass se stvara na stogu, tijekom prevođenja, a CoolClass se stvara na heapu(pomoću operatora new), tijekom izvođenja

3. Ne postoji takav poziv, nema virtualnu tablicu nista ne inicijalizira

4. U konstruktoru od CoolClass(94) prvo se poziva konstruktor od Base(73), 
postavi se njegova vtablica(81),
zatim se postavi vtablica od CoolClass(107), u rax se spremi pointer na vtable

5. 
poc.set 
1. stavlja se 42 na stoga i varijabla poc na stog
2. poziva se funkcija s fiksne memorijeske lokacije bez dinamičkog polimorfizma

pb->set
1. pb se dereferencira dvaput da bi dobili pokazivač na dio memorije
gdje je pohranjena virtualna tablica
2. stavlja se 42 na stog i varijabla pb na stog
3. poziva se funkcija iz virtualne tablice


poc.set zahtjeva manje instrukcija jer nije potrebno dohvaćati pokazivač
na virtualnu tablicu kao kod pb->set

6. definicija  vtablice (159), inicijalizacija

3SetEi i-prima integer za funkcije
C2Ev - konstruktor
*/