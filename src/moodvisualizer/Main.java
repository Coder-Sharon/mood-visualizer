/*TO DO: getStream for any file; letterList remove punctuation and rewind file; auto repaint off;
    print to contain description of each visual; fix begin game*/
//Graph, positive vs. negative overtime, analyze speeches, analyze mood progression, save image file/data of progressions!
package moodvisualizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import simplegui.SimpleGUI;
import simplegui.GUIListener;
import simplegui.SGMouseListener;
import moodvisualizer.hashtables.HashSearch;

/**
 * Drives visualization of user-input content.
 * @author Sharon Tartarone
 */
public class Main implements GUIListener, SGMouseListener{
    
    private final boolean MOOD = false, SOUND = true;
    private final int ALL = 0, UNCOMMON = 1, SENTIMENT = 2; 
    
    static SimpleGUI sg;
    private final Visualizer v;
    private InputStream in;
    private LinkedList<Word> allWords, uncommonWords, sentimentWords;
    private boolean state;
    private int visual;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        new Main();
    }
    
    public Main() throws IOException{
        sg = new SimpleGUI(500,500);
        v = new Visualizer();
        sg.centerGUIonScreen();
        sg.setTitle("docuMOOD");
        sg.labelButton1("");
        sg.labelButton2("");
        sg.labelSwitch("");
        beginRun();
        sg.registerToGUI(this);
        sg.registerToMouse(this);
    }
    
    private void beginRun() throws IOException{
        in = getStream();
        setWordLists();
        in.close();
        reactToSwitch(false);
    }
    
    private InputStream getStream(){
        InputStream input;
        
        sg.print("Please enter your text file and its source folder: ");
        
        input = getClass().getResourceAsStream(sg.keyReadString());
        while(input == null){
            sg.print("File not found. Try again.");
            input = getClass().getResourceAsStream(sg.keyReadString());
        }
        sg.print("Visualizations are ready. Choose an option below.");
                
        return input;
    }
    
    private void setWordLists(){
        LinkedList<Word> wordList = new LinkedList<>();
        LinkedList<Word> uncommonList, sentimentList;
        Iterator<Word> iter;
        InputStream temp = in;
        Scanner s = new Scanner(temp);
        s.useDelimiter("[^\\w']+");
        
        while(s.hasNext()){
            wordList.add(new Word(s.next().toLowerCase()));
        }
        
        HashSearch.searchAndSetMood(wordList);
        
        allWords = wordList;
        
        uncommonList = (LinkedList<Word>) wordList.clone();
        iter = uncommonList.iterator();
        while(iter.hasNext()){
            Word current = iter.next();
            if(current.isCommon())   
                iter.remove();
        }
        
        uncommonWords = uncommonList;
        
        sentimentList = (LinkedList<Word>) uncommonList.clone();
        iter = sentimentList.iterator();
        while(iter.hasNext()){
            Word current = iter.next();
            if(current.isNeutral())   
                iter.remove();
        }
        
        sentimentWords = sentimentList;
    }
    
    //need to rewind file
    private LinkedList<Letter> getLetterList(){
        LinkedList<Letter> letterList = new LinkedList<>();
        InputStream temp = in;
        Scanner s = new Scanner(temp);
        s.useDelimiter("");
        
        while(s.hasNext()){
            letterList.add(new Letter(s.next().toLowerCase()));
        }
        
        HashSearch.searchAndSetSound(letterList);
        
        return letterList;
    }

    @Override
    public void reactToButton1(){
        if(state == MOOD){
            if(visual == ALL){
                v.visualizeMood(uncommonWords);
                sg.labelButton1("Sentiment Words");
                visual++;
            }
            else if(visual == UNCOMMON){
                v.visualizeMood(sentimentWords);
                sg.labelButton1("All Words");
                visual++;
            }
            else if(visual == SENTIMENT){
                 v.visualizeMood(allWords);
                 sg.labelButton1("Uncommon Words");
                 visual = 0;
            }
        }
        else{
            
        }
    }

    @Override
    public void reactToButton2(){
        try {
            beginRun();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void reactToSwitch(boolean switchState){
        if(switchState == MOOD){
            v.visualizeMood(allWords);
            sg.labelButton1("Uncommon Words");
            sg.labelButton2("New File");
            sg.labelSwitch("Visualize Sound");
            state = switchState;
            visual = ALL;
        }
        else if(switchState == SOUND){
            v.visualizeSound(null);
            sg.labelSwitch("Visualize Mood");
            sg.labelButton1("All Sounds");
            sg.labelButton2("New File");
            state = switchState;
            visual = ALL;
        }
    }

    @Override
    public void reactToSlider(int i){
    }   

    @Override
    public void reactToMouseClick(int x, int y){
        if(visual == ALL)
            v.displayWord(allWords, x, y);
        else if(visual == UNCOMMON)
            v.displayWord(uncommonWords, x, y);
        else if(visual == SENTIMENT)
            v.displayWord(sentimentWords, x, y);
    }
}