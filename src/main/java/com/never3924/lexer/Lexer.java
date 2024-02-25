package com.never3924.lexer;

import java.util.*;

/**
 * Performs Syntax Analysis
 * @author Never3924
 * @version 0.0.1
 */
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

    /**
     * parse method
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

    /**
     * addSplitter method
     * @param addCharacter Character of the character you want to split
     * @return Whether the addition was successful or not
     */
    public boolean addSplitter(char addCharacter){
        return userSplit.add(addCharacter);
    }

    /**
     * getUserSplit method
     * Returns a list of user-defined split characters.
     * @return List of user-defined split characters
     */
    public ArrayList<Character> getUserSplit() {
        return userSplit;
    }

    /**
     * getParseString method
     * Returns the string to parse.
     * @return string to parse
     */
    public String getParseString() {
        return parseString;
    }

    /**
     * getBrackets method
     * Get a list of predefined parentheses.
     * @return list of defined parentheses
     */
    public List<Character> getBrackets() {
        return brackets;
    }

    /**
     * setBrackets method
     * Modifies the list of defined parentheses.
     * @param brackets list to change to
     */
    public void setBrackets(List<Character> brackets) {
        this.brackets = brackets;
    }

    /**
     * getOperators method
     * Gets a predefined operator.
     * @return list of defined operators
     */
    public List<Character> getOperators() {
        return operators;
    }

    /**
     * setOperators method
     * Modify the list of defined operators.
     * @param operators list to change to
     */
    public void setOperators(List<Character> operators) {
        this.operators = operators;
    }

    /**
     * getStrings method
     * Returns a list of characters that signal the start/end of a defined string.
     * @return List of characters that signal the start/end of the defined string
     */
    public List<Character> getStrings() {
        return strings;
    }

    /**
     * setStrings method
     * Changes the list of characters that signal the start/end of a defined string.
     * @param strings list to change to
     */
    public void setStrings(List<Character> strings) {
        this.strings = strings;
    }

    /**
     * getSplits method
     * Returns a delimited list of defined statement endings and arguments
     * @return Delimited list of defined end-of-statement and arguments
     */
    public List<Character> getSplits() {
        return splits;
    }

    /**
     * setSplits method
     * Change the defined sentence endings or argument delimited list
     * @param splits list to change to
     */
    public void setSplits(List<Character> splits) {
        this.splits = splits;
    }

    /**
     * setUserSplit method
     * Modify the list of user-defined split characters
     * @param userSplit list to change to
     */
    public void setUserSplit(ArrayList<Character> userSplit) {
        this.userSplit = userSplit;
    }

    /**
     * getType method
     * Gets the type of the specified Character.
     * @param c Character whose type you want to check
     * @return type
     */
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

    /**
     * analyze method
     * Parses the given Character.
     * @param c Character you want to parse
     */
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

    /**
     * endAnalyze method
     * finish The Analysis
     */
    private void endAnalyze(){
        if(!s.equals("")) {
            list.add(s);
            s = "";
        }
    }

    /**
     * listRefresh method
     * return Variables Etc To Their Initial State
     */
    private void listRefresh(){
        list.clear();
        s = "";
        isString = false;
        startString = ' ';
    }

    /**
     * Returns an array containing tokens.
     * @return list containing tokens
     */
    private ArrayList<String> getList(){
        return list;
    }
}
