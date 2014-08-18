package guiObjects;

import org.lwjgl.opengl.GL11;
//import org.lwjgl.LWJGLException;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;
//import org.lwjgl.opengl.GL11;

public class TestQuad 
{
	public TestQuad()
	{
		
	}
	public void drawQuad()
	{
		
		// Clear the screen and depth buffer
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
 
	    // set the color of the quad (R,G,B,A)
	    GL11.glColor3f(0.5f,0.5f,1.0f);
 
	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(100,100);
		GL11.glVertex2f(100+300,100);
		GL11.glVertex2f(100+200,100+200);
		GL11.glVertex2f(100,100+200);
	    GL11.glEnd();
	}
	public void drawQuadAt(int xPos, int yPos)
	{
		
		// Clear the screen and depth buffer
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
 
	    // set the color of the quad (R,G,B,A)
	    GL11.glColor3f(0.5f,0.5f,1.0f);
 
	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(xPos,yPos);
		GL11.glVertex2f(xPos+200,yPos);
		GL11.glVertex2f(xPos+200,yPos+200);
		GL11.glVertex2f(xPos,yPos+200);
	    GL11.glEnd();
	}

}
