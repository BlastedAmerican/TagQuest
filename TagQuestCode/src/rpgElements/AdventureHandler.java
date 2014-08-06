package rpgElements;
import java.util.*;
//import guiObjects.GameDisplayHandler;
import guiObjects.*;
import rpgElements.*;
/*
 * Adventure Handler is the main control for the 
 * rpg elements in TagQuest. 
 */
public class AdventureHandler 
{
	protected ArrayList<Enemy> nixonsList;
	protected Player player;
	protected Combatant activeEnemy;
	protected GameDisplayHandler objectDisplay;
	////////
	protected Button attackButton;
	/////////nnnnnnnnnnnnnn
	protected RevampledCombatButton newAttack;
	protected RevampledCombatButton newDefend;
	protected RevampledCombatButton newSplit;
	protected RevampledCombatButton newHeal;
	/////////nnnnnnnnnnnnnn
	protected Button defendButton;
	////////
	
	protected int enemyCount = 2;
	protected Sprite highlight;
	protected NewGameButton startButton;
	protected int playerHealth = 50;
	protected int level = 1;
	protected ArrayList<Moveable> moveThings = new ArrayList<Moveable>();
	
	
	
	public AdventureHandler()
	{
		
	}
	public AdventureHandler( GameDisplayHandler display)
	{
		objectDisplay = display;
		//playSetUp();
		mainMenu();
	}
	//Clean up and remove unused assets.
	protected void zoneClear()
	{
		//startButton.setLocation(900,900);
		
	}
	
	public void mainMenu()
	{
		startButton = new NewGameButton("a","a",this);
		startButton.setLocation(250, 250);
		objectDisplay.addNewSprite(startButton);
	}
	public void levelUpMenu()
	{
		
	}
	//Creates GUI and other objects for first time set up. To save resources, are refreshed with each new level.
	/*
	public void createHolders()
	{
		player = new Player(this,objectDisplay);
		attackButton = new Button("attack.png","attack.png","attack",player );
		defendButton = new Button("defend.png","defend.png","defend",player );
		nixonsList = new ArrayList<Enemy>();
	}
	public void population()
	{
		
	}
	*/
	public void setUpButtons()
	{
		startButton.setLocation(900,900);
		//Making new sets of buttons here.
		//Why not just hard code them.
		if(attackButton == null)
		{
//			attackButton = new Button("attack.png","attack.png","attack",player );
//			moveThings.add(attackButton);
//			defendButton = new Button("defend.png","defend.png","defend",player );
//			moveThings.add(defendButton);
//			System.out.println("Making new button.");
		
			newAttack = new RevampledCombatButton("Tag_Quest/attack_transparent_border.png","Tag_Quest/attack_transparent_border.png","attack",player,180 );
			moveThings.add(newAttack);
			newDefend = new RevampledCombatButton("Tag_Quest/shield_transparent_border.png","Tag_Quest/shield_transparent_border.png","defend",player,120);
			moveThings.add(newDefend);
			newSplit = new RevampledCombatButton("Tag_Quest/trash_transparent_border.png","Tag_Quest/trash_transparent_border.png","split",player,60);
			moveThings.add(newSplit);
			newHeal = new RevampledCombatButton("Tag_Quest/heal_transparent_border.png","Tag_Quest/heal_transparent_border.png","heal",player,600);
			moveThings.add(newHeal);
			
			 
		}
		else
		{
			newAttack.setPlayer(player);
			newDefend.setPlayer(player);
			newSplit.setPlayer(player);
			newHeal.setPlayer(player);
		}
		
		
		
	}
	public void playSetUp()
	{
		//Move all other objects somewhere else?
		//Create Player Object
		player = new Player(this,objectDisplay,playerHealth);
		moveThings.add(player);
		//SET UP BUTTONS
		setUpButtons();
		
//		highlight = new Sprite();
//		highlight.init("sun.png");
//		objectDisplay.addNewSprite(highlight);
//		objectDisplay.addNewSprite(attackButton);
//		objectDisplay.addNewSprite(defendButton);
		////////
//		attackButton.setLocation(0,0);
//		defendButton.setLocation(100,0);
		////////
		newAttack.setLocation(0, 500);
		newDefend.setLocation(100, 500);
		//newSplit.setLocation(200, 500);
		newHeal.setLocation(300, 500);
		newSplit.setLocation(200, 500);
		objectDisplay.addNewSprite(newAttack);
		objectDisplay.addNewSprite(newDefend);
		objectDisplay.addNewSprite(newSplit);
		objectDisplay.addNewSprite(newHeal);
		//END BUTTON SET UP
		
		nixonsList = new ArrayList<Enemy>();
		//System.out.println(nixonsList.size());
		//System.out.println(nixonsList);
		for(int x = 0; nixonsList.size() != enemyCount; x++)
		{
			
			Enemy enemyInstance = new Enemy(this,objectDisplay);
			moveThings.add(enemyInstance);
			objectDisplay.addNewCombatant(enemyInstance);
			//enemyInstance.setUp();
			nixonsList.add(enemyInstance);
			//System.out.println(nixonsList.size());
			//System.out.println(nixonsList);
		
			
		}
		setUpLocations();
		if( nixonsList.size() > 0 )
		{
			nixonsList.get(0).enterCombat(player);
			player.setCombatant(nixonsList.get(0));
			activeEnemy = nixonsList.get(0);
		}
		
		
	}
	
	protected void setUpLocations()
	{
		int x = 0;
		for(Enemy e: nixonsList)
		{
			x++;
			e.setLocation(400+100*x,300);
			//e.setLocation(300+10,200);
			
		}
		player.setLocation(-200, 300);
	}
	
	public void onEnemyDeath()
	{
		
		
	}
	
	public void onEnemyDeath(Enemy deadFighter)
	{
		nixonsList.remove(deadFighter);
		if( nixonsList.size() > 0)
		{
			deadFighter.die();
			
			nixonsList.get(0).enterCombat(player);
			activeEnemy = nixonsList.get(0);
			player.setCombatant(activeEnemy);
		}
		else
		{
			//Needs to generate a new level characters.
			System.out.println("You win!");
			playerHealth = player.health;
			player.victory();
			this.zoneClear();
			this.playSetUp();
			//this.zoneClear();
			
			
		}
		
		
	}
	
	public void endTurn( Combatant lastPlayer )
	{
		
		if(lastPlayer == player)
		{
			activeEnemy.takeTurn();
		}
		else if(lastPlayer == activeEnemy)
		{
			player.takeTurn();
		}
	}
	public void onPlayerDeath()
	{
		//Code to restart the game goes here.
		objectDisplay.wipeDrawnObjects();
		Sprite gameOver = new Sprite(0,0);
		gameOver.init("gameOver.png");
		objectDisplay.addNewSprite(gameOver);
	}

}
