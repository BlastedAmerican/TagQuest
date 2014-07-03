package guiObjects;

import rpgElements.Player;

public class Button extends Sprite {
	protected String buttonUp;
	protected String buttonDown;
	protected String stateSet;
	protected Player player;
	
	public Button()
	{
		
	}
	
	public Button(String image, String image2, String input, Player inputReceive  ) 
	{
		buttonUp = image;
		buttonDown = image2;
		stateSet = input;
		player = inputReceive;
		init(buttonUp);
		// TODO Auto-generated constructor stub
	}
	public void onClick()
	{
		player.setAction(stateSet);
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
