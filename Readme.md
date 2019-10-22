# Java

factorial(0,1).\\
factorial(Number, Result) :-\\
    Number>0,
    Number1 is Number-1,
    factorial(Number1,SmallResult),
    Result is SmallResult * Number.



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
