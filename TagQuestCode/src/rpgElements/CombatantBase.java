package rpgElements;

import guiObjects.*;



public class CombatantBase extends Sprite implements Combatant,Moveable 
{
	
	//protected Sprite this;
	protected WordCube healthDisplay;
	protected int health;
	protected int healthMax;
	protected String graphicLocation;
	protected int xLoc;
	protected int yLoc;
	protected Combatant enemy;

	
	public CombatantBase()
	{
		
		
		
	}
	@Override
	public void setUp() 
	{
		// TODO Auto-generated method stub
		//this = new Sprite();
		healthDisplay = new WordCube();
		

	}

	@Override
	public void onDeath() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void takeDamage( int damage ) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dealDamage(Combatant target) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void checkIfDead() 
	{
		// TODO Auto-generated method stub
		if(health <= 0)
		{
			//Callback to main function goes here based on if its a player
			//or a computer.
		}
		

	}

	@Override
	public void takeTurn() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocation(int x, int y) 
	{
		// TODO Auto-generated method stub
		xLoc = x;
		yLoc = y;
		this.updateXLocation(x);
		this.updateYLocation(y);
		healthDisplay.updateXLocation(x);
		healthDisplay.updateYLocation(y);
		
		

	}

	@Override
	public void setImage(String imageName) 
	{
		// TODO Auto-generated method stub
		this.init(imageName);

	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void render() 
	{
		// TODO Auto-generated method stub
		this.drawQuad();
		healthDisplay.render();
		
	}
	@Override
	public void enterCombat(Combatant newEnemy) 
	{
		// TODO Auto-generated method stub
		enemy = newEnemy;
	}
	@Override
	public void updateHealthDisplay() 
	{
		// TODO Auto-generated method stub
		healthDisplay.setText(health+"/"+healthMax);
		
	}
	public int getXLocation()
	{
		return xLoc; 
	}
	public int getYLocation()
	{
		return yLoc;
	}
	public void animateAttack()
	{
		
	}
	public void animateTakeDamage()
	{
		
	}
	public void animateDefeat()
	{
		
	}
	public void animate()
	{
		
	}

}
