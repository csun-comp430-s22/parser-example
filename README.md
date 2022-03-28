Original grammar.  Left-recursive, no operator precedence:

```
x is a variable
i is an integer
op ::= + | - | < | ==
exp ::= x | i | exp op exp
stmt ::= if (exp) stmt else stmt | { stmt* } // statements are separated with ;
program ::= stmt
```

New grammar.  No longer left-recursive, and handles operator precedence:
```
x is a variable
i is an integer
primary_exp ::= x | i | `(` exp `)`
additive_op ::= + | -
additive_exp ::= primary_exp (additive_op primary_exp)*
less_than_exp ::= additive_exp (`<` additive_exp)*
equals_exp ::= less_than_exp (`==` less_than_exp)*
exp ::= equals_exp
stmt ::= if (exp) stmt else stmt | { stmt* } | println(exp);
program ::= stmt
```

- Base case for `stmt`: `{}`

Example `additive_exp`s:
- `123`
- `foobar`
- `2 + 3`
- `2 + 3 + 4`
- `2 < 3 == 4`
    - `(2 < 3) == 4`
    - `2 < (3 == 4)`

Key idea: separate out expressions into priority levels.
Highest to lowest precedence:
- `+`, `-`
- `<`
- `==`

- `x < y < z`
- Mathematically, equivalent to: `x < y && y < z`
- Valid expression in C.  Does NOT mean what the math says.
- `(x < y) < z`
    - If `x < y`: 1
    - If NOT `x < y`: 0

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
- RightParenToken
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
