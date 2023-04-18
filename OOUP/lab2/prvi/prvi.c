#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


const void* mymax(
  const void *base, size_t nmemb, size_t size,
  int (*compar)(const void *, const void *)){
    
    const void* max = base;

    for (int i = 1; i < nmemb; i++){
        const void* current = base + i * size;
        if (compar(current, max)){
            max = current;
        }
    }

    return max;
  }

int gt_int(const void * a, const void * b) {
    return ( *(int*)a > *(int*)b );
}

int gt_char(const void * a, const void * b) {
    return ( *(char*)a > *(char*)b );
}

int gt_str(const void * a, const void * b) {
    return strcmp(*(const char**)a, *(const char**)b) > 0;
}


int main(){
    int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
    char arr_char[]="Suncana strana ulice";
    const char* arr_str[] = {
        "Gle", "malu", "vocku", "poslije", "kise",
        "Puna", "je", "kapi", "pa", "ih", "njise"
    };

    int max_int = *(int*) mymax(arr_int, sizeof(arr_int) / sizeof(arr_int[0]) , sizeof(arr_int[0]), gt_int);
    char max_char = *(char*) mymax(arr_char, strlen(arr_char), sizeof(arr_char[0]), gt_char);
    char const* max_str = *(char const**) mymax(arr_str, sizeof(arr_str) / sizeof(arr_str[0]), sizeof(arr_str[0]), gt_str);
    
    printf("Max of arr_int: %d\n", max_int);
    printf("Max of arr_char: %c\n", max_char);
    printf("Max of arr_str: %s\n", max_str);
}