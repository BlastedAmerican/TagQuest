package guiObjects;

import rpgElements.Player;

public class Button extends Sprite {
	protected String buttonUp;
	protected String buttonDown;
	protected String stateSet;
	protected Player player;
	/*
	 * Blank constructor, does not actually do anything.
	 * 
	 */
	public Button()
	{
		
	}
	/*
	 * Creates a new button. 
	 * @param image String a string the gives the location of the image.
	 * @param image2 String a string that gives the second image to be loaded on the button.
	 * @param input String the string that defines the input the button sends to the player.
	 * @param inputReceive Player the player object that is sent the input when the button is clicked.
	 */
	public Button(String image, String image2, String input, Player inputReceive  ) 
	{
		buttonUp = image;
		buttonDown = image2;
		stateSet = input;
		player = inputReceive;
		init(buttonUp);
		// TODO Auto-generated constructor stub
	}
	/*
	 * The onClick overwritten hook. 
	 */
	public void onClick()
	{
		player.setAction(stateSet);
	}
	
	public void setPlayer(Player newPlayer)
	{
		player = newPlayer;
	}
	
	
	public void mouseDown( boolean mouseDown )
	{
		if( mouseDown && mouseOver )
		{
			//System.out.println("Mouse Clicked");
			isClicked = true;
			onClick();
		}
		else
		{
			isClicked = false;
		}
	}

	public void setLocation(int x, int y)
	{
		updateXLocation(x);
		updateYLocation(y);
	}
	public Button(int xPosition, int yPosition) 
	{
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

}
