# Java
--------------3
factorial(0,1).\\
factorial(Number, Result) :-\\
    Number>0,
    Number1 is Number-1,
    factorial(Number1,SmallResult),
    Result is SmallResult * Number.


-------------------1
:- dynamic (krewni/2).
jest_przodkiem(X,Y) :- jest_rodzicem(X,Y).
jest_przodkiem(X,Y) :- jest_rodzicem(Z,Y),
                       jest_przodkiem(X,Z).

jest_krewnym(X,Y) :- jest_przodkiem(Z,X),
                     jest_przodkiem(Z,Y),
    				 X \= Y,
    				 \+krewni(X,Y),
    				 \+krewni(Y,X),
    				 assert(krewni(X,Y)).
                     
----------------2              
process_command(add) :-
    	writeln("Podaj znajomego: "),
    	read(X),
    	assert(jest_znajomym(X)).
process_command(del) :-
    	writeln("Podaj znajomego do usunięcia: "),
    	read(X),
    	retract(jest_znajomym(X)).
process_command(list) :-
    	jest_znajomym(X),
    	writeln(X),
    	fail.
process_command(exit) :-
    	writeln("Do widzenia!"),
		fail.
-------------4
1
zero.
peano_number(zero,0).
peano_number(s(X), Y) :- peano_number(X, Z),Y is Z+1.
2
peano_add(zero, X, X).
peano_add(s(X), Y, s(Z)) :- peano_add(X, Y, Z).
3
peano_times(zero, X, zero).
peano_times(s(X), Y, s(Z)) :- peano_times(X, Y, Z).
