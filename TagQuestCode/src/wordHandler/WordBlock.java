package wordHandler;

import java.util.ArrayList;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.io.Writer;
//import java.io.OutputStreamWriter;

public class WordBlock 
{
	public ArrayList<WordTag> wordList = new ArrayList<WordTag>(); // The ArrayList storing WordTags.
	protected boolean playState = true; //Can this block be played or not?
	protected BlockManager gameHandler;
	
	
	/*
	 * Placeholder constructor. 
	 * Created by version 2 of the generator.
	 * Needs to create a new wordList.
	 */
	public WordBlock()
	{
		wordList = new ArrayList<WordTag>();
	}
	
	/*
	 * Creates a new WordBlock from a single WordTag.
	 * @param newWord WordTag The new word to be used as the base of the tag.
	 */
	public WordBlock( WordTag newWord, BlockManager hq )
	{
		wordList = new ArrayList<WordTag>();
		wordList.add(newWord);
		gameHandler = hq;
		
	}
	
	/*
	 * Sets the GameHandler object.
	 * BlockManager GameHandle
	 */
	public void setGameHandler(BlockManager gameHandle)
	{
		gameHandler = gameHandle;
	}
	/*
	 * Constructs a new wordList, taking a word list.
	 * @param newList ArrayList<WordTag> the new Array list for the new WordBlock.
	 */
	public WordBlock( ArrayList<WordTag> newList )
	{
		wordList = newList;
	}
	
	/*
	 * Adds a new word from a WordTag.
	 * @param WordTag newWord
	 */
	public void addWord( WordTag newWord)
	{
		wordList.add(newWord);
	}
	/*
	 * Contructs and adds a new word tag.
	 * @param newWord the new Word being added.
	 * @param tagValue is the tag real or fake.
	 * @param tagW the new tag weight
	 */
	public void addWord( String newWord, boolean tagValue, int tagW )
	{
		
		wordList.add( new WordTag(newWord, tagValue, tagW, this ) );
	}
	/*
	 * Attempts to remove a word from the group. If
	 * no word can be found returns null.
	 * 
	 */
	public WordTag removeWord( String removedWord )
	{
		WordTag toRemove = findWord( 0, removedWord );
		if( toRemove != null)
		{
			wordList.remove(toRemove);
			gameHandler.makeNewWordBlock(toRemove);
			return toRemove;
		}
		else
		{
			System.out.println("ERROR WORD NOT FOUND");
			return null;
		}
		
	}
	/*
	 * Removes a word, and destroys its links to the list.
	 * 
	 * 
	 */
	public WordTag destroyWord( String removedWord )
	{
		WordTag toRemove = findWord( 0, removedWord );
		if( toRemove != null)
		{
			writeToFile(removedWord);
			wordList.remove(toRemove);
			
			toRemove.updateWordBlock(null);
			
			//gameHandler.makeNewWordBlock(toRemove);
			return toRemove;
		}
		else
		{
			System.out.println("ERROR WORD NOT FOUND");
			return null;
		}
	}
	
	public void writeToFile( String toWrite )
	{
		System.out.println(toWrite);
 
		
	}
	
	
	public void onClick( WordTag callTag )
	{
		if(getState())
		{
			//Call to Manager I've been clicked.
			String actionToTake = gameHandler.onClick();
			if(actionToTake.equals("attack"))
			{
				int damageValue = this.playBlock();
				gameHandler.PlayerTurnAction(damageValue, 0, 0);
				//Secondary forward call again.
			}
			else if(actionToTake.equals("defend"))
			{
				int damageValue = this.playBlock();
				gameHandler.PlayerTurnAction(0,damageValue,0);
				//Secondary call forward to inform turn over.
			}
			else if(actionToTake.equals("heal"))
			{
				int healSize = this.playBlock();
				gameHandler.PlayerTurnAction(0,0,healSize);
				//System.out.println("Error, fairy in bottle not found");
			}
			else if(actionToTake.equals("split"))
			{
				//this.removeWord(callTag.getWord());
				this.destroyWord(callTag.getWord());
				
			}
			else
			{
				//System.out.println("An error has occurred. Current action: "+ actionToTake );
			}
		}
		else
		{
			
		}
	}
	
	
	public boolean getState()
	{
		return playState;
	}
	/*
	 * This method currently returns a value for the block.
	 * Right now, it handles if a false value is found.
	 * However, this should probbaly be split in the future.
	 * @return int The score of the block being returned.
	 */
	public int playBlock()
	{
		playState = false;
		int playSum = 0;
		int playMult = 1;
		for( int x = 0; x < wordList.size();  x++ )
		{
			WordTag curWord = wordList.get(x);
			if( curWord.isWordFakeTag() )
			{
				playMult = -1;
			}
			playSum += 1;
		}
		return (playSum * playMult);
	}
	/*
	 * Recursive helper method to find if a word is in a string.
	 * @param int curPos The current position in the array list.
	 * @param String removeWord The string in the WordTag being searched for.
	 * @return WordTag the WordTag found. Null if no tag found.
	 */
	private WordTag findWord(int curPos, String removeWord)
	{
		//String curWord = wordList.get(curPos).getWord();
		if( curPos > wordList.size() - 1)
		{
			return null;
		}
		else 
		{
			String curWord = wordList.get(curPos).getWord();
			if( curWord.equals(removeWord))
			{
				return wordList.get(curPos);
			}
			else
			{
				return findWord(curPos + 1, removeWord);
			}
		}
		
	}
	public int getWordTagLocX( int startPoint, WordTag searchTag )
	{
		//System.out.println("Start");
		if( startPoint > wordList.size() - 1)
		{
			//System.out.println(wordList)
			return -1;
		}
		else if( wordList.get(startPoint) == searchTag )
		{
			//System.out.println(wordList.size());
			//System.out.println("End");
			return startPoint;
			
		}
		else
		{
			//System.out.println("Test");
			return getWordTagLocX(startPoint+1,searchTag);
		}
		
	}
    public void passSizeUp(int yLocation)
    {
    	gameHandler.sizeUp(yLocation);
    }
	public int getWordTagLocY( int startPoint )
	{
		return gameHandler.getWordTagLoc(startPoint, this);
	}

	public String toString()
	{
		return StringAppender(0,"");
	}
	private String StringAppender(int pos, String curAdd)
	{
		if(pos > wordList.size() - 1 )
		{
			return curAdd;
		}
		else
		{
			curAdd = curAdd + "("+wordList.get(pos)+")";
			return StringAppender(pos+1, curAdd);
		}
	}
	/*
	 * Testing method for WordTag
	 * @param String[] argv
	 */
	public static void main(String[] argv) 
    {
		WordBlock testBlock = new WordBlock();
		testBlock.addWord("test",false,5);
		WordTag testTag = new WordTag("potato",true,3,testBlock);
		testBlock.addWord(testTag);
		//WordTag test3 = testBlock.removeWord("cake");
		//WordBlock testBlock2 = new WordBlock(test3);
		//System.out.println(testBlock2);
		//System.out.println(testBlock);
		//System.out.println(testBlock.playBlock());
		
    }
}
