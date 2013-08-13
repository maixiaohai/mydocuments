#tips
==============

### MYSQL:

* when IF encounter strings

    IF(somestring, x, y)  
    what we want to see: somestring exits, return x, or, return y  
    correct: IF(somestring='', y, x) 