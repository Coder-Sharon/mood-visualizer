package moodvisualizer;

import java.awt.Color;

/**
 * Encapsulates the representation of a Word.
 * @author Sharon Tartarone
 */
public class Word{
    
    public enum Mood {POSITIVE, NEGATIVE, NEUTRAL, COMMON};
    
    private final String word;
    private Mood mood;
    private int x, y, boxSize;
    
    public Word(String word){
        this.word = word;
        this.mood = Mood.NEUTRAL;
    }
    
    public void visualizeWord(){
        Main.sg.drawFilledBox(x, y, boxSize, boxSize, moodColor(), 1.0, null);
        Main.sg.drawBox(x, y, boxSize, boxSize, Color.black, 1.0, 1, null);
    }
    
    private Color moodColor(){
        Color moodColor = Color.white;
        
        if(this.mood == Mood.POSITIVE)
            moodColor = Color.green;
        if(this.mood == Mood.NEGATIVE)
            moodColor = Color.red;
        if(this.mood == Mood.NEUTRAL)
            moodColor = Color.lightGray;
        
        return moodColor;
    }
    
    public String getWord(){
        return word;
    }
    
    public void setMood(Mood mood){
        this.mood = mood;
    }
    
    public Mood getMood(){
        return mood;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setBoxSize(int boxSize){
        this.boxSize = boxSize;
    }
    
    public boolean isCommon(){
        return (this.mood == Mood.COMMON);
    }
    
    public boolean isNeutral(){
        return (this.mood == Mood.NEUTRAL);
    }
    
    public boolean isInside(int clickedX, int clickedY){
        return((clickedX >= x && clickedX <= x + boxSize) && (clickedY >= y && clickedY <= y + boxSize));
    }
}
