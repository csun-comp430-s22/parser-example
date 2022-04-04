package parser;

public class PrintlnToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof PrintlnToken;
    }

    public int hashCode() {
        return 11;
    }
    
    public String toString() {
        return "PrintlnToken";
    }
}
