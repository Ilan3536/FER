#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


struct Unary_Function;
typedef double (*PTRFUN)(struct Unary_Function* uf,double x);

struct Unary_Function {
    int lower_bound;
    int upper_bound;
    PTRFUN* pfun;
};

struct Square {
    struct Unary_Function baseClass;
};

struct Linear {
    struct Unary_Function baseClass;
    double a;
    double b;
};


double value_atSquare(struct Unary_Function* unary, double x){
   return x*x;
}
double value_atLinear(struct Unary_Function* unary, double x){
    struct Linear* linear = (struct Linear*) unary;
    return linear->a*x + linear->b;
}

double value_at(struct Unary_Function* unary, double x){
    return unary->pfun[0](unary, x);
}
double negative_value_at(struct Unary_Function* unary, double x){
    return -unary->pfun[0](unary, x);
}
PTRFUN UnaryFunctionTable[2] ={NULL, negative_value_at};
PTRFUN LinearTable[2]={value_atLinear, negative_value_at};
PTRFUN SquareTable[2]={value_atSquare, negative_value_at};

void constructSquare(struct Square* square, int lower_bound, int upper_bound){
    square->baseClass.pfun = SquareTable;
    square->baseClass.lower_bound = lower_bound;
    square->baseClass.upper_bound = upper_bound;
}
struct Unary_Function* createSquare(int lower_bound, int upper_bound){
    struct Square* memory = (struct Square*) malloc(sizeof(struct Square*));
    constructSquare(memory, lower_bound, upper_bound);
    return (struct Unary_Function*) memory;
}

void constructLinear(struct Linear* linear, int lower_bound, int upper_bound, double a, double b){
    linear->baseClass.pfun = LinearTable;
    linear->a = a;
    linear->b = b;
    linear->baseClass.lower_bound = lower_bound;
    linear->baseClass.upper_bound = upper_bound;
}
struct Unary_Function* createLinear(int lower_bound, int upper_bound, double a, double b){
    struct Linear* memory = (struct Linear*) malloc(sizeof(struct Linear*));
    constructLinear(memory, lower_bound, upper_bound, a, b);
    return (struct Unary_Function*) memory;
}


void tabulate(struct Unary_Function* uf){
    for (int x = uf->lower_bound; x <= uf->upper_bound; x++){
        printf("f(%d)=%lf\n", x, value_at(uf,x));
    }
}

bool same_functions_for_ints(struct Unary_Function* f1, struct Unary_Function* f2, double tolerance) {
      if(f1->lower_bound != f2->lower_bound) return false;
      if(f1->upper_bound != f2->upper_bound) return false;
      for(int x = f1->lower_bound; x <= f1->upper_bound; x++) {
        double delta = value_at(f1, x) - value_at(f2, x);
        if(delta < 0) delta = -delta;
        if(delta > tolerance) return false;
      }
      return true;
};


int main(){
    
    struct Unary_Function* f1 = createSquare(-2, 2);
    struct Unary_Function* f2 = createLinear(-2, 2, 5, -2);
    tabulate(f1);
    tabulate(f2);
    
    printf("f1==f2: %s\n", same_functions_for_ints(f1, f2, 1E-6) ? "DA" : "NE");
    printf("neg_val f2(1) = %lf\n", f2->pfun[1](f2, 1.0));
    free(f1);
    free(f2);
    return 0;


}