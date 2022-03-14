package parser;

public class EqualsToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof EqualsToken;
    }

    public int hashCode() {
        return 3;
    }
    
    public String toString() {
        return "EqualsToken";
    }
}
