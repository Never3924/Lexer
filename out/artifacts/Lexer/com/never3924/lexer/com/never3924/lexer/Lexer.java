package com.never3924.lexer;

import java.util.*;

public class Lexer {
    private final ArrayList<String> list = new ArrayList<>();

    private static final Map<String, String> Type = new HashMap<>() {
        {
            put("alphabet", "0");
            put("digit", "1");
            put("space", "2");
            put("operator", "3");
            put("bracket", "4");
            put("string", "5");
            put("split", "6");
            put("other", "7");
            put("user", "999");
        }
    };
    private List<Character> brackets = Arrays.asList('(',')','{','}','[',']');
    private List<Character> operators = Arrays.asList('=','+','-','*','/');
    private List<Character> strings = Arrays.asList('\'','"');
    private List<Character> splits = Arrays.asList(',', ';');

    private ArrayList<Character> userSplit = new ArrayList<>();

    private String s = "";
    private boolean isString = false;
    private char startString = ' ';

    private final String parseString;

    public Lexer(String string){
        this.parseString = string;
    }

    /***
     * Parses a string and returns the {@link Tokens Tokens} class.
     * @return parsed token
     */
    public Tokens parse(){
        listRefresh();
        for(char c : parseString.toCharArray()){
            analyze(c);
        }
        endAnalyze();
        return new Tokens(getList());
    }

    public boolean addSplitter(char addCharacter){
        return userSplit.add(addCharacter);
    }

    public ArrayList<Character> getUserSplit() {
        return userSplit;
    }

    public String getParseString() {
        return parseString;
    }

    public List<Character> getBrackets() {
        return brackets;
    }

    public void setBrackets(List<Character> brackets) {
        this.brackets = brackets;
    }

    public List<Character> getOperators() {
        return operators;
    }

    public void setOperators(List<Character> operators) {
        this.operators = operators;
    }

    public List<Character> getStrings() {
        return strings;
    }

    public void setStrings(List<Character> strings) {
        this.strings = strings;
    }

    public List<Character> getSplits() {
        return splits;
    }

    public void setSplits(List<Character> splits) {
        this.splits = splits;
    }

    public void setUserSplit(ArrayList<Character> userSplit) {
        this.userSplit = userSplit;
    }

    private String getType(final Character c){
        if(Character.isLetter(c)) return Type.get("alphabet");
        else if(Character.isDigit(c)) return Type.get("digit");
        else if(Character.isWhitespace(c)) return Type.get("space");
        else if(operators.contains(c)) return Type.get("operator");
        else if(brackets.contains(c)) return Type.get("bracket");
        else if(strings.contains(c)) return Type.get("string");
        else if(splits.contains(c)) return Type.get("split");
        else if(this.userSplit.contains(c)) return Type.get("user");

        return Type.get("other");
    }

    private void analyze(char c){
        String type = getType(c);
        if(!isString){
            if(type.equals(Type.get("other"))){
                System.out.println("That Character Is Unavailable: '%c'".formatted(c));
                return;
            }
            if(type.equals(Type.get("alphabet"))){
                s += c;
            } else if(type.equals(Type.get("digit"))){
                s += c;
            }else if(type.equals(Type.get("space"))){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
            }else if(type.equals(Type.get("operator"))){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
                s += c;
                list.add(s);
                s = "";
            }else if(type.equals(Type.get("bracket"))){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
                s += c;
                list.add(s);
                s = "";
            }else if(type.equals(Type.get("split"))){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
                s += c;
                list.add(s);
                s = "";
            }else if(type.equals(Type.get("string"))){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
                s += c;
                list.add(s);
                s = "";
                startString = c;
                isString = true;
            }else if(type.equals(Type.get("user"))){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
                s += c;
                list.add(s);
                s = "";
            }
        }else{
            if(c == startString){
                if(!s.equals("")) {
                    list.add(s);
                }
                s = "";
                s += c;
                list.add(s);
                s = "";
                startString = ' ';
                isString = false;
            }else{
                s += c;
            }
        }
    }

    private void endAnalyze(){
        if(!s.equals("")) {
            list.add(s);
            s = "";
        }
    }

    private void listRefresh(){
        list.clear();
        s = "";
        isString = false;
        startString = ' ';
    }

    private ArrayList<String> getList(){
        return list;
    }
}
