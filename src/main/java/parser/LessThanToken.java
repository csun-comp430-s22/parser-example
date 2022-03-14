package parser;

public class LessThanToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof LessThanToken;
    }

    public int hashCode() {
        return 2;
    }
    
    public String toString() {
        return "LessThanToken";
    }
}
