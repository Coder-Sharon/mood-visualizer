package moodvisualizer.hashtables;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Scanner;
import moodvisualizer.Word;

/**
 * Hashtable containing all Words for lookup.
 * @author Sharon Tartarone
 */
public class WordHashtable{
    
    private final Hashtable<String, Word> wordHash;
    private final InputStream neg, pos, common;
    
    public WordHashtable(){
        wordHash = new Hashtable<>();
        neg = getClass().getResourceAsStream("negative_words.txt");
        pos = getClass().getResourceAsStream("positive_words.txt");
        common = getClass().getResourceAsStream("common_words.txt");
        buildHash();
    }
    
    private void buildHash(){
        Scanner p = new Scanner(pos);
        Scanner n = new Scanner(neg);
        Scanner c = new Scanner(common);
        
        while(p.hasNextLine()){
           Word temp = new Word(p.nextLine());
           temp.setMood(Word.Mood.POSITIVE);
           wordHash.put(temp.getWord(), temp);
        }
        
        while(n.hasNextLine()){
           Word temp = new Word(n.nextLine());
           temp.setMood(Word.Mood.NEGATIVE);
           wordHash.put(temp.getWord(), temp);
        }
        
        while(c.hasNextLine()){
           Word temp = new Word(c.nextLine());
           temp.setMood(Word.Mood.COMMON);
           wordHash.put(temp.getWord(), temp);
        }
    }
    
    public Hashtable<String, Word> getHash(){
        return wordHash;
    }
}
