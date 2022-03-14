package parser;

public class SemicolonToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof SemicolonToken;
    }

    public int hashCode() {
        return 10;
    }
    
    public String toString() {
        return "SemicolonToken";
    }
}
