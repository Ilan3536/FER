#include <stdio.h>

class Base{
public:
  Base() {
    metoda();
  }

  virtual void virtualnaMetoda() {
    printf("ja sam bazna implementacija!\n");
  }

  void metoda() {
    printf("Metoda kaze: ");
    virtualnaMetoda();
  }
};

class Derived: public Base{
public:
  Derived(): Base() {
    metoda();
  }
  virtual void virtualnaMetoda() {
    printf("ja sam izvedena implementacija!\n");
  }
};

int main(){
  Derived* pd=new Derived();
  pd->metoda();
}

/*
1. U mainu se konstuktor Derived()
2. Derived() poziva konstruktor Base()
3. Base() postavlja svoju virtualnu tablicu i poziva metodu metoda()
4. "Metoda kaze:" i poziva se virtualnaMetoda() od Base jer virtualna tablica od Derived jos nije inicijalizirana, samo je definirana
5. Vraćamo se u konstruktor Derived(), definira se njegova virtualna tablica
6. Poziva se metoda() iz Base(), zatim virtualnaMetoda(), ali ovaj put iz Derived() jer je inicijalizirana virtualna tablica

pd->metoda() 
Poziva se metoda u virtualnaMetoda() od Derived jer je objekt tipa Derived 

*/