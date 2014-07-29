package csvLoader;

/**
 * @author FritzOld
 *
 */
public class LoadedWordTag 
{
	//Actual name of tag image.
	protected String tagImage;
	//Weight of the tag.
	protected int tagWeight;
	//Tag name-Actual value of the tag String
	protected String tagName;
	//Path to the image - for actually loading the image.
	protected String imagePath;

	/*
	 * In the event a word tag is constructed without any input, the tagImage is set to null.
	 * 
	 */
	public LoadedWordTag() 
	{
		tagImage = null;
	}
	
	public LoadedWordTag(String imageName, int weightTag, String nameTag, String pathImage ) 
	{
		
		tagImage = imageName;
		tagWeight = weightTag;
		tagName = nameTag;
		imagePath = pathImage;
	}
	/**
	 * 
	 * @return int tagWeight
	 */
	public int getWeight()
	{
		return tagWeight;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTagName()
	{
		return tagName;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTagImage()
	{
		return tagImage;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getImagePath()
	{
		return imagePath;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getFullImagePath()
	{
		return this.getImagePath()+this.getTagImage();
	}

}
