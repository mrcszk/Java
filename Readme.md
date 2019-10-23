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
