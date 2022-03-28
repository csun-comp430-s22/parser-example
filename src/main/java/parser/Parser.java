package parser;

// recursive-descent parsing: O(n^3) - in practice, usually O(n)
// recursive-descent parsing: lot of stack space (recursive!) - O(n); n: # of tokens
// you don't have to use this; alternatives: ANTLR, bison

// idea: follow the grammar, implement one method/function per production rule
// parseOp, parseExp, parseStmt, parseProgram
//
// exp ::= x | i | exp op exp
//
// # list of tokens
// tokens = [...]
//
// def parseExp(position_in_tokens):
//   # handle variables
//   # handle integers
//   # otherwise...
//   left_parse_result = parseExp(position_in_tokens)
//   op_parse_result = parseOp(left_parse_result.position)
//   right_parse_result = parseExp(op_parse_result.position)
//   return ParseResult(OpExp(left_parse_result.result,
//                            op_parse_result.result,
//                            right_parse_result.result),
//                      right_parse_result.position)
//
// I'm going to call these methods "parsers".
// Every parser only handles what it parses.
// Parsers will fail if they see input they don't recognize.
// Parsers start at a particular token (parameter - starting token).
//    - The starting token position is a parameter to the parser
// Parsers return a "ParseResult", which contains:
//    - Whatever it parsed in
//    - The position of the next token

import java.util.List;

public class Parser {
    private final List<Token> tokens;

    public Parser(final List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getToken(final int position) throws ParseException {
        if (position >= 0 && position < tokens.size()) {
            return tokens.get(position);
        } else {
            throw new ParseException("Invalid token position: " + position);
        }
    }

    // public ParseResult<Stmt> parseStmt(final int position) throws ParseException { ... }
    
    // parser for op
    // op ::= + | - | < | ==
    public ParseResult<Op> parseOp(final int position) throws ParseException {
        final Token token = getToken(position);
        // with pattern matching (in Scala)
        // token match {
        //   case PlusToken => ParseResult(PlusOp(), position + 1)
        //   case MinusToken => ParseResult(MinusOp(), position + 1)
        //   ...
        // }
        if (token instanceof PlusToken) {
            return new ParseResult<Op>(new PlusOp(), position + 1);
        } else if (token instanceof MinusToken) {
            return new ParseResult<Op>(new MinusOp(), position + 1);
        } else if (token instanceof LessThanToken) {
            return new ParseResult<Op>(new LessThanOp(), position + 1);
        } else if (token instanceof EqualsToken) {
            return new ParseResult<Op>(new EqualsOp(), position + 1);
        } else {
            throw new ParseException("expected operator; received: " + token);
        }
    }

    // recursive descent parsing can't handle a grammar with left-recursion
    // exp ::= x | i | exp op exp
    public ParseResult<Exp> parseExp(final int position) throws ParseException {
        final Token token = getToken(position);
        // with pattern matching (in Scala)
        // getToken(position) match {
        //   case VariableToken(name) => ParseResult(VariableExp(name), position + 1)
        //   case IntegerToken(value) => ParseResult(IntegerExp(value), position + 1)
        //   ...
        // }
        if (token instanceof VariableToken) {
            final String name = ((VariableToken)token).name;
            return new ParseResult<Exp>(new VariableExp(name), position + 1);
        } else if (token instanceof IntegerToken) {
            final int value = ((IntegerToken)token).value;
            return new ParseResult<Exp>(new IntegerExp(value), position + 1);
        } else {
            // Issue #1: operator precedence
            // Issue #2: ??? - hint: this is recursive code
            // Have base cases, have a recursive case.
            final ParseResult<Exp> left = parseExp(position);
            final ParseResult<Op> op = parseOp(left.position);
            final ParseResult<Exp> right = parseExp(op.position);
            return new ParseResult<Exp>(new OpExp(left.result,
                                                  op.result,
                                                  right.result),
                                        right.position);
        }
    }
            
}
