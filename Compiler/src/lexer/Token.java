package lexer;

public class Token {
    private final Tag tag;
    private final String lexeme;

    public Token(Tag tag,String lexeme){
        this.tag = tag;
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Tag getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return String.format("< %s , '%s' >",this.tag,this.lexeme);
    }
}
