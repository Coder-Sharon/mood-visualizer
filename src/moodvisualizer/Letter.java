package moodvisualizer;

/**
 * Encapsulates the representation of a Letter.
 * @author Sharon Tartarone
 */
public class Letter{
    
    public enum Sound {SOFT, NEUTRAL, HARSH};
    
    private final String letter;
    private Sound sound;
    
    public Letter(String letter){
        this.letter = letter;
        this.sound = Sound.NEUTRAL;
    }
    
    public void visualizeLetter(){
        
    }
    
    public String getLetter(){
        return letter;
    }
    
    public void setSound(Sound sound){
        this.sound = sound;
    }
    
    public Sound getSound(){
        return sound;
    }
}
