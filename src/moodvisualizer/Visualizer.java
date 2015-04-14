//TO DO: make sure node coordinates are correct; separate visualizers?
package moodvisualizer;

import java.util.LinkedList;

/**
 *
 * @author Sharon Tartarone
 */
public class Visualizer {
  
    public void visualizeMood(LinkedList<Word> wordList){
        Main.sg.eraseAllDrawables();
        assignWordCoordinates(wordList);
        for (Word current : wordList)
            current.visualizeWord();
    }
    
    public void displayWord(LinkedList<Word> wordList, int clickedX, int clickedY){
        for (Word current : wordList){
            if(current.isInside(clickedX, clickedY)){
                Main.sg.print(current.getWord());
                return;
            }
        }
    }
    
    public void visualizeSound(LinkedList<Letter> letterList){ 
        Main.sg.eraseAllDrawables();
        assignSoundCoordinates(letterList);
        Main.sg.drawText("Sound", 10, 10);
    }
    
    private void assignWordCoordinates(LinkedList<Word> wordList){
        int screenX = Main.sg.getWidth(), screenY = Main.sg.getHeight();
        int boxSize = (int) Math.sqrt((screenX*screenY)/wordList.size());
        
        int x = 0, y = 0;
        for (Word current : wordList){
            if(x > screenX - boxSize){
                x = 0;
                y += boxSize;
            }
            current.setX(x);
            current.setY(y);
            current.setBoxSize(boxSize);
            x += boxSize;
        }
    }
    
    private void assignSoundCoordinates(LinkedList<Letter> letterList){
        
    }
}