package dl;

import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class DL {
    public static void main(String[] args) {


        File program = new File("prog.dl");
        Lexer lexer = new Lexer(program);
        Token token = lexer.nextToken();
        while (token.getTag() != Tag.EOF){
            System.out.println(token);
            token = lexer.nextToken();
        }
        System.out.println(token);

    }
}