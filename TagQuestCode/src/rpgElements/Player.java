/**
 * 
 */
package rpgElements;

import java.util.Random;

import guiObjects.GameDisplayHandler;
import guiObjects.Sprite;
import guiObjects.WordCube;
//import rpgElements.*;
import guiObjects.*;
import wordHandler.*;
import java.util.*;

/**
 * @author FritzOld
 *
 */
public class Player extends CombatantBase implements Combatant,Moveable
{

	protected int damageReduction = 0;
	protected int damageDone = 0;
	protected String action = "none";
	protected BlockGenerator tagMaker;
	protected BlockManager tagManager;
	protected AdventureHandler controller;
	protected GameDisplayHandler display;
	protected boolean turnFinished = false;
	protected ArrayList<String> levelSeeds;
	//protected Sprite highlight;
	protected Random generation = new Random();
	protected Combatant currentTarget;
	protected Sprite imageDisplay;
	protected int actionsTaken = 0;
	protected int levelCount = 0;
	protected int blinkDamageTimer = 0;
	
	protected float[] colorArray = new float[3];
	
	protected int level;
	protected int exp;
	protected int nextLevelExp;
	protected boolean won = false;
	
	
	
	
	/**
	 * 
	 */
	public Player() 
	{
		//Need to set up image, and other stuff.
		
		// TODO Auto-generated constructor stub
	}
	
	
	public Player(AdventureHandler gameRunner, GameDisplayHandler mainDisplay) 
	{
		
		//Need to set up image, and other stuff.
		controller = gameRunner;
		display = mainDisplay;
		setUp();
		health = 50;
		healthMax = 50;
		// TODO Auto-generated constructor stub
	}
	
	
	public Player(AdventureHandler gameRunner, GameDisplayHandler mainDisplay, int health2 ) 
	{
		colorArray[0] = 0;
		colorArray[1] = 0;
		colorArray[2] = 0;
		
		controller = gameRunner;
		display = mainDisplay;
		healthMax = 50;
		this.health = health2;
		setUp();
		
	}
	
	/**
	 * @return the action
	 */
	public String getAction() 
	{
		//String oldAction = action;
		//action = "null";
		return action;
	}
	
	
	
	
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) 
	{
		this.action = action;
		if(action.equals("attack"))
		{
			//highlight.updateXLocation(-15);
			//highlight.updateYLocation(-10);
		}
		else
		{
			//highlight.updateXLocation(90);
			//highlight.updateYLocation(-10);
		}
		//System.out.println(this.action);
	}
	
	public void takeAction( String action )
	{
		//New attack strings 
		// heal, attack, defend, split
		this.action = action;
		if(action.equals("heal"))
		{
			
		}
		if(action.equals("attack"))
		{
			
		}
		if(action.equals("defend"))
		{
			
		}
		if(action.equals("split"))
		{
			
		}

	}
	
	public void levelUp()
	{
		
	}
	
	
	
	public void setUp()
	{
		
		
		//setUpWordCubes();
		//
		//highlight = new Sprite();
		//highlight.init("sun.png");
		//display.addNewSprite(highlight);
		//highlight.setLocation(-200, -200);
		
		
		//
		//graphicDisplay = new Sprite();
		healthDisplay = new WordCube();
		this.init("player.png");
		healthDisplay.init();
		display.addNewSprite(this);
		display.addNewWordCube(healthDisplay);
		
		
		
		
		//healthDisplay.init();
		
		updateHealthDisplay();
		//CODE FOR DISPLAYING WORDS AND WORD TAGS
//		tagMaker = new BlockGenerator();
//		tagManager = new BlockManager(display,this);
//		tagMaker.setBlockManager(tagManager);
//		tagMaker.setUp();
//		tagMaker.makeNewBlock(5);
//		tagMaker.makeNewBlock(5);
//		tagMaker.makeNewBlock(5);
//		tagMaker.makeNewBlock(5);
//		tagMaker.makeNewBlock(5);
		display.addNewCombatant(this);
		
		
	}
	
	public void setLinkedColor( float r, float g, float b)
	{
		colorArray[0] = r;
		colorArray[1] = g;
		colorArray[2] = b;
	}
	
//	protected void setUpWordCubes()
//	{
//		//TODO Player needs to be disconnected from the control of the displayable image.
//		//Need to get a image to display.
//		//Need to display a textbook.
//		//Need to roll randomly.
//		//Select a seed at random.
//		//Remove that seed.
//		//Generate the blocks with that seed.
//		//Display that seeds image.
//		//Set up word seeds.
//		levelSeeds = new ArrayList<String>();
//		levelSeeds.add("lizard");
//		levelSeeds.add("gunnery");
//		levelSeeds.add("boat");
//		
//		String seed = levelSeeds.get( generation.nextInt(levelSeeds.size()));
//		levelSeeds.remove(seed);
//		//CODE FOR DISPLAYING WORDS AND WORD TAGS
//		tagMaker = new BlockGenerator();
//		tagManager = new BlockManager(display,this);
//		tagMaker.setBlockManager(tagManager);
//		tagMaker.setUp();
//		//System.out.println("Start");
//		tagMaker.makeNewBlock(5,seed);
//		tagMaker.makeNewBlock(4,seed);
//		tagMaker.makeNewBlock(4,seed);
//		tagMaker.makeNewBlock(3,seed);
//		tagMaker.makeNewBlock(2,seed);
//		//System.out.println("End");
//		//Display the image.
//		imageDisplay = new Sprite();
//		String concat = seed + ".png";
//		imageDisplay.init(concat);
//		imageDisplay.setLocation(0, 0);
//		display.addNewSprite(imageDisplay);
//		
//		
//	
//		
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	public void victory()
	{
		//imageDisplay.setLocation(900, 900);
		//tagManager.onWin();
		won = true;
		//highlight.setLocation(-200, -200);
	}
	public void animate()
	{
		if(this.currentTarget.getXLocation()-this.getXLocation() > 100 && !won)
		{
			this.setLocation(this.xLoc+1, this.yLoc);
		}
		
		
		
	}
	public void animateVictory()
	{
		
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
		if((damage - damageReduction) < 0)
		{
			;
		}
		else
		{
			health = health - (damage - damageReduction);
			blinkDamageTimer = 30;
			this.setLinkedColor(1, 0, 0);
		}
		
		checkIfDead();
		damageReduction = 0;
		this.updateHealthDisplay();

	}
	public void setCombatant( Combatant enemy )
	{
		currentTarget = enemy;
	}

	@Override
	public void dealDamage(Combatant target) 
	{
		actionsTaken += 1;
		//System.out.println("ActionsTaken"+actionsTaken);
		// TODO Auto-generated method stub
		target.takeDamage(1);
	}
	
	public void dealDamage( int damageNumber ) 
	{
		// TODO Auto-generated method stub
		//System.out.println("ActionsTaken"+actionsTaken);
		if(damageNumber > 0)
		{
			currentTarget.takeDamage(damageNumber*3);
		}
		else
		{
			health = health - Math.abs(damageNumber);
			blinkDamageTimer = 30;
			this.setLinkedColor( .5f, .5f, .5f );
		}
		updateHealthDisplay();
		
	}

	@Override
	public void checkIfDead() 
	{
		// TODO Auto-generated method stub
		if(health <= 0)
		{
			controller.onPlayerDeath();
			//Callback to main function goes here based on if its a player
			//or a computer.
		}
		

	}

	public void drawQuad()
	{
		super.drawQuad();
		blinkDamageTimer += -1;
		if(blinkDamageTimer > 0 )
		{
			healthDisplay.setColor( colorArray[0],colorArray[1],colorArray[2]);
		}
		else
		{
			healthDisplay.setColor( 0, 0, 0);
		}
	}
	
	@Override
	public void takeTurn() 
	{
		
		

	}
	
	public void heal(int healSize)
	{
		healSize = healSize*5;
		int newHealth = this.health + healSize;
		
		
		
		//System.out.println(healSize);
		//System.out.println("Healingup");
		if(healSize > 0)
		{
		blinkDamageTimer = 30;
		this.setLinkedColor(0, 1, 0);
		}
		if( newHealth > this.healthMax)
		{
			this.health = this.healthMax;
		}
		else
		{
			this.health = newHealth;
		}
		this.updateHealthDisplay();
	}
	public void takeTurn( int damage, int defenseBoost, int healSize ) 
	{
		damageReduction = defenseBoost;
		dealDamage(damage);
		heal(healSize);
		//currentTarget.takeTurn();
		

	}
	

}
