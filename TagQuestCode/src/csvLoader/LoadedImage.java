package csvLoader;

import java.util.*;

public class LoadedImage 
{
	//Is going to need.... what exactly?
	//A list of tags.
	//The weight of each tag.
	//Should I make another tag class?....
	//Probbaly, Word Tag is designed to work with the handlers..
	//So, need a Vector of Tags.
	//Need the images name.
	//Need A fake counterpart?
	protected Vector<LoadedWordTag> imageTags;
	protected String imageName;
	
	protected int count = 0;
	
	/*
	 * Make a new loaded image with no tags.
	 */
	public LoadedImage() 
	{
		imageTags = null;
		
		// TODO Auto-generated constructor stub
	}
	public LoadedImage( String imageName )
	{
		this.imageName = imageName;
		imageTags = new Vector<LoadedWordTag>();
	}
	public void addNewTag( LoadedWordTag tagToAdd )
	{
		imageTags.add(tagToAdd);
	}
	public LoadedWordTag getNextTag()
	{
		try
		{
			LoadedWordTag tagToReturn = imageTags.get(count);
			count++;
			return tagToReturn;
		}
		catch(Exception error)
		{
			System.out.println("Not enough tags, error.");	
			return null;
		}
		
	}
	public String getImageName()
	{
		return imageName;
	}
	
	
	
	
	

}
