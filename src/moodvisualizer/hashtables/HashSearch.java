/*TO DO: ignore case, CITE DOCUMENTS*/

package moodvisualizer.hashtables;

import java.util.LinkedList;
import moodvisualizer.Letter;
import moodvisualizer.Word;

/**
 *
 * @author Sharon Tartarone
 */
public class HashSearch{
    
    
    public static void searchAndSetMood(LinkedList<Word> wordList){
        WordHashtable searchMood = new WordHashtable();
        
        for (Word current : wordList){
            if(searchMood.getHash().containsKey(current.getWord()))
                current.setMood(searchMood.getHash().get(current.getWord()).getMood());
        }
    }
    
    public static void searchAndSetSound(LinkedList<Letter> letterList){
    }
}
