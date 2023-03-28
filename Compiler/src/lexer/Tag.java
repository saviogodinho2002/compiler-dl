package lexer;

public enum Tag {
    PROGRAM("PROGRAM"),
    BEGIN("BEGIN"),END("END"),
    ASSIGN("ASSIGN"),
    ID("ID"),
    EQ("EQ"),

    LIT_INT("LIT_INT"),
    LIT_REAL("LIT_REAL"),

    SUM("SUM"),SUB("SUB"),MUL("MUL"),
    OR("OR"),
    LT("LT"), LE("LE"),GE("GE"), GT("GT"),

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
