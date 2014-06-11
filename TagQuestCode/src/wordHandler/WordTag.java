package wordHandler;
/**
 * @author Fritz Wallace
 *
 *WordTags are the lowest base of words.
 *Multiple WordTags make up a word block.
 */
public class WordTag 
{
	private String currentWord;
	private boolean isFake;
	private int tagWeight;
	
	public WordTag()
	{
		
	}
	public WordTag(String newWord, boolean tagValue, int tagW)
	{
		currentWord = newWord;
		isFake = tagValue;
		tagWeight = tagW;
		
		
	}
	public void setWord( String newLetter )
	{
		currentWord = newLetter;
	}
	public boolean isWordFakeTag()
	{
		
		return isFake;
	}

}
