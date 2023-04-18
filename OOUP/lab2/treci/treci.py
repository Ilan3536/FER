def mymax(iterable, key):
    max_x=max_key=None

    for x in iterable:
        if (max_x == None):
            max_x = x
            max_key = key(x)
        if (key(x) > max_key):
            max_key = key(x)
            max_x = x

    return max_x

maxint = mymax([1, 3, 5, 7, 4, 6, 9, 2, 0], lambda x: x)
maxchar = mymax("Suncana strana ulice", ord)
maxstring = mymax([
  "Gle", "malu", "vocku", "poslije", "kise",
  "Puna", "je", "kapi", "pa", "ih", "njise"], len)

D={'burek':8, 'buhtla':5}
maxdict = mymax(D, D.get)

najjeftiniji = mymax(D, lambda x: -D.get(x))

people = [('Ivan', 'Bat'), ('Matija', 'Kralj'), ('Bob', 'Smith'), ('Matija', 'Topić')]

maxpeople = mymax(people, lambda x: x)

print(maxint) 
print(maxchar) 
print(maxstring) 
print(maxdict)
print(maxpeople)
print(najjeftiniji)

