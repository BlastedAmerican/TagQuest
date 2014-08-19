package guiObjects;

import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


/*
 * Sprite generates a quad object in the shape of 
 * rectangle. The class is robust, but not extensive.
 * There are most likely better implementations,
 * but this one is written quickly and intended for 
 * prototyping.
 */
public class Sprite implements Moveable
{
	protected Texture boundImage;
	protected int xPos; //X location of the quad when constructed.
	protected int yPos; //Y location of the quad when constructed.
	protected int mouseXPos;
	protected int mouseYPos;
	protected int width; //Width of the quad.
	protected int height; //Height of the quad.
	protected boolean mouseOver = false;
	protected boolean isClicked = false;

	/*
	 * Basic Constructor. Takes no values.
	 */
	public Sprite()
	{
		xPos = 50;
		yPos = 50;
		

	}
	/*
	 * Secondary constructor.
	 * xPos int The x location of the constructed quad.
	 */
	public Sprite( int xPosition, int yPosition )
	{
		xPos = xPosition;
		yPos = yPosition;
		
	}
	/*
	 * Gets the xPosition of the object.
	 * @return int The X Position of the sprite. Should be top-left corner.
	 * 
	 */
	public int getXPos()
	{
		return xPos;
	}
	/*
	 * Get the y position of the object.
	 * @return int 
	 */
	public int getYPos()
	{
		return yPos;
	}
	public void drawQuad()
	{
		try
		{
			Color.white.bind();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, boundImage.getTextureID());
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
	        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
	        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	        
			// set the color of the quad (R,G,B,A)
			GL11.glColor3f(1.0f,1.0f,1.0f);
			
			// draw quad
			GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(xPos,yPos);
			
			GL11.glTexCoord2f(0.0f,1.0f);
			GL11.glVertex2f(xPos,yPos+boundImage.getTextureHeight());
			
			GL11.glTexCoord2f(1.0f,1.0f);
			GL11.glVertex2f(xPos+boundImage.getTextureWidth(),yPos+boundImage.getTextureHeight());
			
			GL11.glTexCoord2f(1.0f,0.0f);
			GL11.glVertex2f(xPos+boundImage.getTextureWidth(),yPos);
			
			GL11.glEnd();
			
		}
		catch( Exception e )
		{
			System.err.println("No openGL Context Found");

		}
	}
	/*
	 * Load and bind an image.
	 * 
	 */
	public void init(String imageLocation) 
	{

		try 
		{
			// load texture from PNG file
			//boundImage = TextureLoader.getTexture("GIF", ResourceLoader.getResourceAsStream("spaceinvaders/"+imageLocation));
			//TODO Update this to load from a single target.
			boundImage = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("tagQuestImages/"+imageLocation),GL11.GL_NEAREST);
			//boundImage.bind();
//			System.out.println("Texture loaded: "+boundImage);
			//System.out.println(">> Image width: "+boundImage.getImageWidth());
			//System.out.println(">> Image height: "+boundImage.getImageHeight());
//			System.out.println(">> Texture width: "+boundImage.getTextureWidth());
//			System.out.println(">> Texture height: "+boundImage.getTextureHeight());
//			System.out.println(">> Texture ID: "+boundImage.getTextureID());
			height = boundImage.getImageHeight();
			width = boundImage.getImageWidth();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		//Mouse theMouse = new Mouse();
		//mouse.create();
	}
	public void setLocation(int x, int y)
	{
		updateXLocation(x);
		updateYLocation(y);
	}
	
	public void updateXLocation(int newXLoc)
	{
		xPos = newXLoc;
	}
	public void updateYLocation(int newYLoc)
	{
		yPos = newYLoc;
	}
	public void moveByInX(int xMovement)
	{
		xPos = xMovement + xPos;
	}
	public void moveByInY(int yMovement)
	{
		yPos = yMovement + yPos;
	}
	public void getMousePos( int mouseX, int mouseY)
	{
		mouseXPos = mouseX;
		mouseYPos = mouseY;
		boolean test = this.detectMouseOver();
		
	}
	
	public boolean detectMouseOver()
	{
		if(xPos < mouseXPos && xPos + width > mouseXPos)
		{
			if(yPos < mouseYPos && yPos + height > mouseYPos)
			{
				//System.out.println("MOuseOvered");
				mouseOver = true;
				return mouseOver;
			}
		}
		
		
		mouseOver = false;
		return mouseOver;
			
	}
	public void animate()
	{
		
	}
	public void mouseDown( boolean mouseDown )
	{
		if( mouseDown && mouseOver )
		{
			//System.out.println("Mouse Clicked");
			isClicked = true;
		}
		else
		{
			isClicked = false;
		}
	}
	
	public void checkButtonStates(boolean oneKeyDown, boolean twoKeyDown, boolean threeKeyDown, boolean fourKeyDown )
	{
		//Empty method, exists to get overrode, replace with something better later.
	}
}
