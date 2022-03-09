package parser;

// 1 + 3
// new OpExp(new IntegerExp(1), new PlusOp(), new IntegerExp(3))
//
// 2 - (1 + 3)
// new OpExp(new IntegerExp(2),
//           new MinusOp(),
//           new OpExp(new IntegerExp(1),
//                     new PlusOp(),
//                     new IntegerExp(3))
//
public class OpExp implements Exp {
    public final Exp left;
    public final Op op;
    public final Exp right;

    public OpExp(final Exp left,
                 final Op op,
                 final Exp right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }
}
