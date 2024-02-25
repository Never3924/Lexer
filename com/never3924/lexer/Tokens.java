package com.never3924.lexer;

import java.util.ArrayList;
import java.util.List;

/***
 * This is a class based on ArrayList published by the {@link Lexer Lexer} class.
 */
public class Tokens extends ArrayList<String>{
    private ArrayList<String> tokens = new ArrayList<>();

    public Tokens(){
        super();
    }

    /***
     * Make the existing ArrayList an instance of the Tokens class.
     * @param arrayList The ArrayList you want to convert to Tokens class
     */
    public Tokens(ArrayList<String> arrayList){
        super();
        this.addAll(arrayList);
    }

    /***
     * Returns the previous element of the specified string as a List.
     * @param search String to search for
     * @return search results
     */
    public List<String> getPrevious(String search){
        ArrayList<String> response = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            String now = this.get(i);
            if(now.equals(search)){
                if((i - 1) >= 0) {
                    response.add(this.get(i - 1));
                }
            }
        }

        return response.stream().toList();
    }

    /***
     * Returns the next element of the specified string as a List.
     * @param search String to search for
     * @return search results
     */
    public List<String> getNext(String search){
        ArrayList<String> response = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            String now = this.get(i);
            if(now.equals(search)){
                if((i + 1) <= this.size()) {
                    response.add(this.get(i + 1));
                }
            }
        }

        return response.stream().toList();
    }

    /***
     * Returns the index number of {@link Tokens#getPrevious(String) getPrevious()}.
     * @param search String to search for
     * @return Index of search results
     */
    public List<Integer> getPreviousIndex(String search){
        ArrayList<Integer> response = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            String now = this.get(i);
            if(now.equals(search)){
                if((i - 1) >= 0) {
                    response.add(i - 1);
                }
            }
        }

        return response.stream().toList();
    }

    /***
     * Returns the index number of {@link Tokens#getNext(String)} getNext()}.
     * @param search String to search for
     * @return Index of search results
     */
    public List<Integer> getNextIndex(String search){
        ArrayList<Integer> response = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            String now = this.get(i);
            if(now.equals(search)){
                if((i + 1) <= this.size()) {
                    response.add(i + 1);
                }
            }
        }

        return response.stream().toList();
    }

    private List<Integer> getStringCharIndex(List<Character> search){
        ArrayList<Integer> response = new ArrayList<>();
        List<String> searchString = search.stream().map(String::valueOf).toList();
        for (int i = 0; i < this.size(); i++) {
            String now = this.get(i);
            if(searchString.contains(now)){
                response.add(i);
            }
        }

        return response.stream().toList();
    }

    /***
     * Searches for a string that is grammatically a string.
     * @param stringChar Character list where the string begins/ends.
     * @return List of strings that are grammatically strings
     */
    public List<String> getString(List<Character> stringChar){
        ArrayList<String> response = new ArrayList<>();
        List<Integer> stringCharIndex = getStringCharIndex(stringChar);
        for (int i = 0; i < stringCharIndex.size(); i++) {
            if((i % 2) == 0){
                response.add(this.get(stringCharIndex.get(i) + 1));
            }
        }

        return response.stream().toList();
    }

    /***
     * Convert {@link Tokens Tokens class} to List class.
     * @return Converted List class
     */
    public List<String> toList(){
        return this.stream().toList();
    }

    /***
     * Convert {@link Tokens Tokens class} to an array.
     * @return converted array
     */
    public String[] toArray(){
        return (String[]) this.stream().toList().toArray();
    }
}
