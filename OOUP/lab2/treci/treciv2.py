def mymax(iterable, key=lambda x: x):
    max_x=max_key=None

    for x in iterable:
        if (max_x == None):
            max_x = x
            max_key = key(x)
        if (key(x) > max_key):
            max_key = key(x)
            max_x = x

    return max_x

maxint = mymax([1, 3, 5, 7, 4, 6, 9, 2, 0])
maxchar = mymax("Suncana strana ulice")
maxstring = mymax([
  "Gle", "malu", "vocku", "poslije", "kise",
  "Puna", "je", "kapi", "pa", "ih", "njise"])

print(maxint) 
print(maxchar) 
print(maxstring) 
