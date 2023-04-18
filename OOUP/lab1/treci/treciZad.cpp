#include <stdio.h>
#include <iostream>

class CoolClass{
public:
  virtual void set(int x){x_=x;};
  virtual int get(){return x_;};
private:
  int x_;
};
class PlainOldClass{
public:
  void set(int x){x_=x;};
  int get(){return x_;};
private:
  int x_;
};

int main(){

  std::cout << sizeof(CoolClass);
  std::cout << "\n";
  std::cout << sizeof(PlainOldClass);

  //PlainOldClass has only one private variable with size 4B, and no virtual functions
  //CoolClass has a pointer to the table of pointers to virtual functions with the size of 8B, and one private
  //          variable with a size of 4B, and compiler adds the padding so it so 16B

}