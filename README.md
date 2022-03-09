```
x is a variable
i is an integer
op ::= + | - | < | ==
exp ::= x | i | exp op exp
stmt ::= if (exp) stmt else stmt | { stmt* } // statements are separated with ;
```

Tokens:
- PlusToken
- MinusToken
- LessThanToken
- EqualsToken
- IfToken
- ElseToken
- VariableToken(name)
- IntegerToken(integer_value)
- LeftParenToken
- RghtParenToken
- LeftCurlyToken
- RightCurlyToken
- SemicolonToken


Abstract syntax tree (AST) nodes:
- Leaves
    - Variable
    - Integers
    - Plus
    - Minus
    - LessThan
    - Equals
- Internal nodes
    - Binary operations
    - If
    - Blocks

- Expression
    - Leaves
        - Variable
        - Integers
    - Internal nodes
        - Binary operations
- Statements
    - Internal nodes
        - If
        - Blocks
