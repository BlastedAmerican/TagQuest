package guiObjects;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import wordHandler.*;

public class WordTagDisplay extends WordCube 
{

	protected WordTag parentTag;
	protected int globalXStart = 300;
	protected int globalYStart = 0;
	protected int globalYShift = 25;
	protected int globalXShift = 75;
	protected boolean rowMouseOver = false;
	
	
	/**
	 * @return the rowMouseOver
	 */
	public boolean isRowMouseOver() {
		return rowMouseOver;
	}




	/**
	 * @param rowMouseOver the rowMouseOver to set
	 */
	public void setRowMouseOver(boolean rowMouseOver) 
	{
		this.rowMouseOver = rowMouseOver;
	}




	public WordTagDisplay() 
	{
		// TODO Auto-generated constructor stub
	}

	
	
	
	public WordTagDisplay( WordTag tagDisplay ) 
	{
		parentTag = tagDisplay;
		label = parentTag.getWord();
		
		
		//super(text);
		// TODO Auto-generated constructor stub
	}

	public void setNewParent( WordTag tagDisplay )
	{
		parentTag = tagDisplay;
		label = parentTag.getWord();
	}
	
	public void updateLocation()
	{
		int xMovementFactor;
		int yMovementFactor;
		WordBlock parentContainer = parentTag.getWordBlock();
		try
		{
		 xMovementFactor = parentContainer.getWordTagLocX(0, parentTag);
		 yMovementFactor = parentContainer.getWordTagLocY(0);
		}
		catch(NullPointerException error)
		{
			xMovementFactor = -100;
			yMovementFactor = -100;
		}
		//System.out.println(xMovementFactor + " by " + yMovementFactor );
		setWordTagLink(parentTag);
		this.updateXLocation( globalXStart + globalXShift*xMovementFactor );
		this.updateYLocation( globalYStart + globalYShift*yMovementFactor );
		//Test method to set color by line.
		
		if(yMovementFactor%3 == 1)
		{
			setColor(0.75f,0f,0f);
		}
		else if(yMovementFactor%3 == 2)
		{
			setColor(0f,0.75f,0f);
		}
		else
		{
			setColor(0f,0f,0.75f);
		}
		
		
		
	}
	
	public void render() 
	{
		if( parentTag != null )
		{
			this.updateLocation();
			Color.green.bind();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			//Color.white.bind();
			if(parentTag.isPlayable())
			{
				GL11.glColor3f(redLevel,blueLevel,greenLevel);
			}
			else
			{
				GL11.glColor3f(0.3f,0.3f,0.3f);
			}
			GL11.glBegin(GL11.GL_QUADS);
			//GL11.glTexCoord2f(0,0);
			//if()
//			if(mouseOver && parentTag.isPlayable())
//			{
//				sizeUpLinkedWords();
//				GL11.glVertex2f(xPos-5,yPos-5);
//				//GL11.glTexCoord2f(1,0);
//				GL11.glVertex2f(xPos+width+5,yPos-5);
//				//GL11.glTexCoord2f(1,1);
//				GL11.glVertex2f(xPos+width+5,yPos+height+5);
//				//GL11.glTexCoord2f(0,1);
//				GL11.glVertex2f(xPos-5,yPos+height+5);
//
//			}
//			else if(!mouseOver && rowMouseOver )
//			{
//				//sizeUpLinkedWords();
//				GL11.glVertex2f(xPos-5,yPos-5);
//				//GL11.glTexCoord2f(1,0);
//				GL11.glVertex2f(xPos+width+5,yPos-5);
//				//GL11.glTexCoord2f(1,1);
//				GL11.glVertex2f(xPos+width+5,yPos+height+5);
//				//GL11.glTexCoord2f(0,1);
//				GL11.glVertex2f(xPos-5,yPos+height+5);
//			}
//			else
//			{
				GL11.glVertex2f(xPos,yPos);
				//GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(xPos+width+50,yPos);
				//GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(xPos+width+50,yPos+height);
				//GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(xPos,yPos+height);
			//}
			GL11.glEnd();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			if(parentTag.isPlayable())
			{
				font.drawString(xPos, yPos, label, Color.white);
			}
			else
			{
				font.drawString(xPos, yPos, label, Color.black);
			}
			rowMouseOver = false;
		}

	}
	
	
	public void sizeUpLinkedWords()
	{
		parentTag.passSizeUp(this.getYPos());
	}
	
	public void mouseDown( boolean mouseDown )
	{
		if( mouseDown && mouseOver )
		{
			
			//Write a new onClickFunction for the WordTags
			
			//Rewrite combat flow a bit.
			parentTag.onClick();
			//parentTag.removeTag();
			isClicked = true;
			updateLocation();
		}
		else
		{
			isClicked = false;
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
