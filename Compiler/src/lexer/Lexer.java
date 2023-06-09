package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Lexer {
    private static final char EOF_CHAR = (char) -1;
    private static int line = 1;
    private BufferedReader reader;
    private char peek;
    private HashMap<String, Tag> keyWord;

    public Lexer(File file){
        keyWord = new HashMap<>();
        keyWord.put("programa",Tag.PROGRAM);
        keyWord.put("inicio",Tag.BEGIN);
        keyWord.put("fim", Tag.END);

        keyWord.put("inteiro", Tag.INT);
        keyWord.put("real",Tag.REAL);
        keyWord.put("booleano",Tag.BOOL);
        keyWord.put("falso",Tag.FALSE);
        keyWord.put("verdadeiro",Tag.TRUE);
        keyWord.put("leia",Tag.READ);
        keyWord.put("escreva",Tag.WRITE);
        keyWord.put("se",Tag.IF);
        keyWord.put("senao",Tag.ELSE);
        try {
            this.reader = new BufferedReader(new FileReader(file));

        }catch (Exception error){
            error.printStackTrace();
        }
        this.peek = ' ';
    }
    private char nextChar(){
        if(this.peek == '\n' || this.peek == '\r')
            line++;
        try {
            this.peek = (char) reader.read();
        }catch (IOException error){
            error.printStackTrace();
        }
        return this.peek;
    }
    public Token nextToken(){
        while (isWhiteSpace(peek))
            this.nextChar();
        switch (peek) {
            case '+' -> {
                this.nextChar();
                return new Token(Tag.SUM, "+");
            }
            case '-' -> {
                this.nextChar();
                return new Token(Tag.SUB, "-");
            }
            case '*' -> {
                this.nextChar();
                return new Token(Tag.MUL, "*");
            }
            case '/'->{
                nextChar();
                if(peek == '/'){

                    do{
                        nextChar();
                    }while (peek != '\n' && peek != '\r' );
                    return nextToken();
                }
                return new Token(Tag.DIV,"/");

            }

            case '|' -> {
                this.nextChar();
                return new Token(Tag.OR, "|");
            }
            case '&' -> {
                this.nextChar();
                return new Token(Tag.AND, "&");
            }
            case '=' -> {
                this.nextChar();
                if (peek == '=') {
                    nextChar();
                    return new Token(Tag.EQ, "==");
                }
                return new Token(Tag.ASSIGN, "=");
            }
            case '!' -> {
                this.nextChar();
                if (peek == '=') {
                    nextChar();
                    return new Token(Tag.NE, "!=");
                }
                return new Token(Tag.NOT, "!");
            }
            case '<' -> {
                nextChar();
                if (peek == '=') {
                    nextChar();
                    return new Token(Tag.LE, "<=");
                }
                return new Token(Tag.LT, "<");
            }
            case '>' -> {
                nextChar();
                if (peek == '=') {
                    nextChar();
                    return new Token(Tag.GE, ">=");
                }
                return new Token(Tag.GT, ">");
            }
            case '(' -> {
                nextChar();
                return new Token(Tag.LPAREN, "(");
            }
            case ')' -> {
                nextChar();
                return new Token(Tag.RPAREN, ")");
            }
            case ',' -> {
                nextChar();
                return new Token(Tag.COMMA, ",");
            }
            case ';' -> {
                nextChar();
                return new Token(Tag.SEMI, ";");
            }
            case EOF_CHAR -> {
                return new Token(Tag.EOF, "");
            }
            default -> {
                 if(Character.isDigit(peek)){
                     StringBuilder builderNum = new StringBuilder();

                     do{
                         builderNum.append(peek);
                         nextChar();
                     }while (Character.isDigit(peek));

                     if(peek == '.'){
                         do{
                             builderNum.append(peek);
                             nextChar();
                         }while (Character.isDigit(peek));
                         return new Token(Tag.LIT_REAL,builderNum.toString());
                     }
                     return new Token(Tag.LIT_INT,builderNum.toString());
                 }
            }
        }
        if(Character.isJavaIdentifierStart(peek)){
            StringBuilder builderId = new StringBuilder();
            do{
                builderId.append(peek);
                nextChar();
            }while (Character.isJavaIdentifierPart(peek));
            String id = builderId.toString();
            Tag tag = keyWord.get( id );
            if(tag!= null ){
                return new Token(tag,id );
            }
            return new Token(Tag.ID,builderId.toString());
        }
        String unk = String.valueOf(peek);
        nextChar();
        return new Token(Tag.UNK,unk);
    }
    private static boolean isWhiteSpace(int c){
        return switch (c) {
            case ' ', '\t', '\n','\r' -> true;
            default -> false;
        };
    }

    public static int getLine() {
        return line;
    }
}
