package parser;

// represents success in parsing
public class ParseResult<A> {
    public final A result;
    public final int position;

    public ParseResult(final A result,
                       final int position) {
        this.result = result;
        this.position = position;
    }
}

// public class ExpParseResult {
//     public final Exp result;
//     public final int position;

//     public ExpParseResult(final Exp result,
//                           final int position) {
//         this.result = result;
//         this.position = position;
//     }
// }

// public class StmtParseResult {
//     public final Stmt result;
//     public final int position;

//     public StmtParseResult(final Stmt result,
//                            final int position) {
//         this.result = result;
//         this.position = position;
//     }
// }
