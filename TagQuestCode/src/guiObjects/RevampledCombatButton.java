/**
 * 
 */
package guiObjects;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import org.lwjgl.opengl.GL11;
//import org.newdawn.slick.Color;

import rpgElements.AdventureHandler;
import rpgElements.Player;



/**
 * @author FritzOld
 *
 */
public class RevampledCombatButton extends Button 
{
	
	protected boolean canBeClicked = true;
	protected Timer clickTimer;
	protected ActionListener clickListener;
	protected int maxTimerCount;
	protected String linkedKey;
	
	
	protected int timerCount = 0;
	
	

	protected AdventureHandler call;

	/**
	 * 
	 */
	public RevampledCombatButton() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public RevampledCombatButton(String image, String image2, AdventureHandler caller)
	{
		call = caller;
		buttonUp = image;
		buttonDown = image2;
		init("startButton.jpg");
		///////
		clickListener = new ActionListener() 
		{
		      public void actionPerformed(ActionEvent evt) 
		      {
		          canBeClicked = true;
		          
		          
		      }
		};
		//clickTimer = new Timer(1000,clickListener);
		
		
	}

	/**
	 * @param image
	 * @param image2
	 * @param input
	 * @param inputReceive
	 */
	public RevampledCombatButton(String image, String image2, String input,
			Player inputReceive, int maxTimer) 
	{
		super(image, image2, input, inputReceive);
		maxTimerCount = maxTimer;
		timerCount = maxTimer;
		///////
//		clickListener = new ActionListener() 
//		{
//		      public void actionPerformed(ActionEvent evt) 
//		      {
//		          canBeClicked = true;
//		          
//		          
//		      }
//		};
		
		
		// TODO Auto-generated constructor stub
	}
	public RevampledCombatButton(String image, String image2, String input,
			Player inputReceive, int maxTimer, String linkKey) 
	{
		super(image, image2, input, inputReceive);
		maxTimerCount = maxTimer;
		timerCount = maxTimer;
		linkedKey = linkKey;
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	

	/**
	 * @param xPosition
	 * @param yPosition
	 */
	public RevampledCombatButton(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void drawQuad()
	{
		//this.setLocation(this.xPos+1, this.yPos+1);
		super.drawQuad();
		if( !player.getAction().equals(stateSet) )
		{
			timerCount = timerCount + 1;
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
		//Color.green.bind();
		//boundImage.bind(); // or GL11.glBind(texture.getTextureID());
		// set the color of the quad (R,G,B,A)
		GL11.glColor4f(.5f,.5f,.5f,.7f);
		
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex2f(xPos,yPos+((float)timerCount/(float)maxTimerCount*boundImage.getImageHeight()));
		GL11.glTexCoord2f(0.0f,1.0f);
		GL11.glVertex2f(xPos+boundImage.getImageWidth(),yPos+((float)timerCount/(float)maxTimerCount*boundImage.getImageHeight()));
		GL11.glTexCoord2f(1.0f,1.0f);
		GL11.glVertex2f(xPos+boundImage.getImageWidth(),yPos+boundImage.getImageHeight());
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex2f(xPos,yPos+boundImage.getImageHeight());
		GL11.glEnd();
		if( timerCount > maxTimerCount)
		{
			
			timerCount = maxTimerCount;
		}
		
		
		
		
	}
	
	public void onClick()
	{
		//System.out.println(timerCount);
		if(timerCount >= maxTimerCount)
		{
			
			canBeClicked = false;
			timerCount = 0;
			//call.playSetUp();
			//clickTimer = new Timer( 1000, clickListener );
			//clickTimer.start();
			//System.out.println("Clicked");
			//System.out.println(stateSet);
			player.setAction(stateSet);
		}
		else
		{
			//player.setAction(stateSet);
			//System.out.println("OnCooldown");
		}
		
	}
	
	public void checkButtonStates(boolean oneKeyDown, boolean twoKeyDown, boolean threeKeyDown, boolean fourKeyDown )
	{
		//Empty method, exists to get overrode, replace with something better later.
		if(linkedKey.equals("1"))
		{
			if( oneKeyDown )
			{
				this.onClick();
			}
		}
		if(linkedKey.equals("2"))
		{
			if( twoKeyDown )
			{
				this.onClick();
			}
		}
		if(linkedKey.equals("3"))
		{
			if( threeKeyDown )
			{
				this.onClick();
			}
		}
		if(linkedKey.equals("4"))
		{
			if( fourKeyDown )
			{
				this.onClick();
			}
		}
	}

}
