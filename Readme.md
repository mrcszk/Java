# Java

factorial(0,1).
factorial(Number, Result) :- 
    Number>0,
    Number1 is Number-1,
    factorial(Number1,SmallResult),
    Result is SmallResult * Number.
