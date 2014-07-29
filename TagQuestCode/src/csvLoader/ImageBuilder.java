package csvLoader;

import java.util.*;

public class ImageBuilder 
{
	//Need list of loaded images, a way to get that list, and a file loader.
	//Maybe a tag maker as well?
	protected FileLoader gameLoader;
	protected Vector<LoadedImage> loadedImages;
	protected Vector<Vector<String>> loadedTags;
	protected String loadTarget = "Data/TagQuestSumterExport2/";
	
	/*
	 * Makes a new ImageBuilder
	 */
	public ImageBuilder() 
	{
		gameLoader = new FileLoader();
		// TODO Auto-generated constructor stub
	}
	/*
	 * Loads a new library.
	 */
	public void loadNewLibrary( String targetCSV )
	{
		loadedTags = gameLoader.readNewCSV(targetCSV);
	}
	/*
	 * makes a new word tag from a string Vector
	 * @param Vector<String> the Vector from which to make the new tag.
	 * @return LoadedWordTag the new word tag.
	 */
	public LoadedWordTag makeNewWordTag( Vector<String> tagString )
	{
		int tagWeight;
		try
		{
			tagWeight = Integer.parseInt(tagString.get(1));
		}
		catch(Exception error)
		{
			tagWeight = -1;
		}
		// TODO Fix and add actual pathing to the images.
		LoadedWordTag newTag = new LoadedWordTag(tagString.lastElement(),tagWeight,tagString.get(6),loadTarget);
		return newTag;
	}
	/*
	 * Loads up data from the CSV. 
	 * This does not actually load the image, it simply loads references to the imaage.
	 */
	public void loadFromVectors()
	{
		loadedImages = new Vector<LoadedImage>();
		if(loadedTags == null)
		{
			System.out.println("No library loaded.");
			
		}
		else
		{
			for(Vector<String> e: loadedTags )
			{
				LoadedWordTag newTag = makeNewWordTag(e);
				this.addTagToImage(newTag);
				//System.out.println("Loop");
			}
		}
	}
	
	
	/*
	 * Adds a tag to an image, by searching for the image in the existing image vector.
	 * If the image the tag is from cannot be found, a new image is created, and the
	 * tag is added to that one.
	 * 
	 */
	public void addTagToImage( LoadedWordTag wordTag )
	{
		if( addTagToImageHelper( wordTag, 0 ) )
		{
			System.out.println("Tag Added");
		}
		else
		{
			LoadedImage newImage = this.makeNewImage(wordTag.getTagImage());
			loadedImages.add(newImage);
			newImage.addNewTag(wordTag);
			//System.out.println(newImage.getImageName()+newImage.);
			//make a new image.
			//add that image to the list.
			//add the new tag to that image.
			
		}
		
	}
	/*
	 * Recursive helpder method for adding tags. True if the word is found,
	 * false if it is not.
	 */
	private boolean addTagToImageHelper( LoadedWordTag wordTag, int nextInt )
	{
		//recursive method.
		if( nextInt >= loadedImages.size())
		{
			return false;
		}
		else if(wordTag.getTagImage().equals(loadedImages.get(nextInt).getImageName()))
		{
			
			loadedImages.get(nextInt).addNewTag(wordTag);
			return true;
		}
		else
		{
			return addTagToImageHelper( wordTag, nextInt + 1 );
		}
		//return false;
	}
	/*
	 * Returns the list of images.
	 */
	public Vector<LoadedImage> getImageList()
	{
		return loadedImages;
	}
	
	public LoadedImage makeNewImage( String imageName )
	{
		//Make a new LoadedImage
		//Return it.
		LoadedImage newImage = new LoadedImage(imageName);
		return newImage;
	}
	public static void main(String[] args) 
	{
		
		ImageBuilder testBuild = new ImageBuilder();
		testBuild.loadNewLibrary("Data/TagQuestSumterExport2/TagQuestSumterExport2.csv");
		testBuild.loadFromVectors();
		System.out.println(testBuild.getImageList().size());
		for( LoadedImage e: testBuild.getImageList() )
		{
			System.out.println(e.getImagePath());
		}
		//System.out.println(testBuild.getImageList().get(1));
		
		//Need to write some test cases here.
		
	}

}
