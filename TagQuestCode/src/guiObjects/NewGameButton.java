package guiObjects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rpgElements.*;

import javax.swing.Timer;
//import java.swing.Action

public class NewGameButton extends Button 
{
	//Updated version of the button, revamping for twitch combat.
	protected boolean canBeClicked = true;
	protected Timer clickTimer;
	protected ActionListener clickListener;
	
	
	
	protected AdventureHandler call;
	/*
	 * Should I add the cooldown of the attack to the button call?
	 */
	public NewGameButton(String image, String image2, AdventureHandler caller)
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
	
	
	
	public NewGameButton(String image, String image2, String input,
			Player inputReceive) 
	{
		super(image, image2, input, inputReceive);
		// TODO Auto-generated constructor stub
	}

	public NewGameButton(int xPosition, int yPosition) 
	{
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	public void onClick()
	{
		if(canBeClicked)
		{
			canBeClicked = false;
			call.playSetUp();
			clickTimer = new Timer( 1000, clickListener );
			clickTimer.start();
			System.out.println("Clicked");
		}
		else
		{
			System.out.println("OnCooldown");
		}
	}

}
