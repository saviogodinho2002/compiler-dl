package lexer;

public enum Tag {
    PROGRAM("PROGRAM"),
    BEGIN("BEGIN"),END("END"),

    INT("INT"),REAL("REAL"),BOOL("BOOL"),

    FALSE("FALSE"),TRUE("TRUE"),
    READ("READ"),WRITE("WRITE"),

    IF("IF"),ELSE("ELSE"),

    ASSIGN("ASSIGN"),
    ID("ID"),
    EQ("EQ"),

    LIT_INT("LIT_INT"),
    LIT_REAL("LIT_REAL"),

    SUM("SUM"),SUB("SUB"),MUL("MUL"),DIV("DIV"),
    OR("OR"), NOT("NOT"), AND("AND"),
    LT("LT"), LE("LE"),GE("GE"), GT("GT"),  NE("NE"),

    LPAREN("LPAREN"),RPAREN("RPAREN"),
    COMMA("COMMA"),SEMI("SEMI"),
    EOF("EOF"), UNK("UNK");

    public final String name;
    private Tag(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
