#include <iostream>
#include <algorithm>
#include <string.h>
#include <set>
#include <vector>

using namespace std;

template <typename Iterator, typename Predicate>
Iterator mymax(
  Iterator first, Iterator last, Predicate pred){
    
    Iterator max = first;
    while (first!=last){
        if (pred(*first, *max)){
            max = first;
        }
        ++first;
    }

    return max;

}

int gt_int(int a, int b) {
   return a > b;
}

int gt_char(char a, char b) {
    return a > b;
}

int gt_str(const char* a, const char* b) {
    return strcmp(a, b) > 0;
}

int gt_string(string a, string b){
    return a > b;
}

template<typename Iterator>
int my_pred(Iterator it1, Iterator it2) {
    return (it1 > it2) ? 1 : 0;
}


int main(){
    int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
    char arr_char[]="Suncana strana ulice";
    const char* arr_str[] = {
        "Gle", "malu", "vocku", "poslije", "kise",
        "Puna", "je", "kapi", "pa", "ih", "njise"
    };
    string arr_string[] = {"puna", "je", "kapi", "pa", "ih", "njise"};
    set<int> set = { 1, 7, 3, 4, 53 };
    vector<int> vector = {1, 8, 42, 4, 5};



    int* max_int = mymax(&arr_int[0], &arr_int[sizeof(arr_int)/sizeof(*arr_int)], gt_int);
    char* max_char = mymax(&arr_char[0], &arr_char[sizeof(arr_char)/sizeof(*arr_char)], gt_char);
    const char** max_str = mymax(&arr_str[0], &arr_str[sizeof(arr_str)/sizeof(*arr_str)], gt_str);
    string* max_string = mymax(&arr_string[0], &arr_string[sizeof(arr_string)/sizeof(*arr_string)], gt_string);
    auto max_set =  mymax(set.begin(), set.end(), my_pred<int>);
    auto max_vector =  mymax(vector.begin(), vector.end(), my_pred<int>);

    

    cout << "Max of arr_int: " << *max_int << endl;
    cout << "Max of arr_char: " << *max_char << endl;
    cout << "Max of arr_str: " << *max_str << endl;
    cout << "Max of arr_string: " << *max_string << endl;
    cout << "Max of set: " << *max_set << endl;
    cout << "Max of vector: " << *max_vector << endl;
    
    
   
}