package guiObjects;

import org.lwjgl.opengl.GL11;

/*
 * Sprite generates a quad object in the shape of 
 * rectangle. The class is robust, but not extensive.
 * There are most likely better implementations,
 * but this one is written quickly and intended for 
 * prototyping.
 */
public class Sprite 
{
	private int xPos; //X location of the quad when constructed.
	private int yPos; //Y location of the quad when constructed.
	private int width; //Width of the quad.
	private int height; //Height of the quad.
	
	/*
	 * Basic Constructor. Takes no values.
	 */
	public Sprite()
	{
		xPos = 50;
		yPos = 50;
		width = 50;
		height = 50;
				
	}
	/*
	 * Secondary constructor.
	 * xPos int The x location of the constructed quad.
	 */
	public Sprite( int xPosition, int yPosition )
	{
		xPos = xPosition;
		yPos = yPosition;
		width = 50;
		height = 50;
	}
	public Sprite( int xPosition, int yPosition, int widthN, int heightN )
	{
		xPos = xPosition;
		yPos = yPosition;
		width = widthN;
		height = heightN;
	}
	public void drawQuad()
	{
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		 
	    // set the color of the quad (R,G,B,A)
	    GL11.glColor3f(0.5f,0.5f,1.0f);
 
	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(xPos,yPos);
		GL11.glVertex2f(xPos+width,yPos);
		GL11.glVertex2f(xPos+width,yPos+height);
		GL11.glVertex2f(xPos,yPos+height);
	    GL11.glEnd();
	}
	public void updateXLocation(int newXLoc)
	{
		xPos = newXLoc;
	}
}
