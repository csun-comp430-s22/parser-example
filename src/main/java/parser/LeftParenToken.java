package parser;

public class LeftParenToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof LeftParenToken;
    }

    public int hashCode() {
        return 6;
    }
    
    public String toString() {
        return "LeftParenToken";
    }
}
