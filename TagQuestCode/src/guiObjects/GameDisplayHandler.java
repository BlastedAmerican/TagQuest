package guiObjects;
import rpgElements.*;
//import wordHandler.*;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
/*
 * Handles tasks involving displaying objects.
 */
public class GameDisplayHandler 
{

	protected ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	protected ArrayList<WordCube> textList = new ArrayList<WordCube>();
	protected ArrayList<Combatant> fighterList = new ArrayList<Combatant>();
	protected ArrayList<Sprite> backroundOnly = new ArrayList<Sprite>();
	//protected GameDisplayHandler display;
	protected boolean keyOneDown = false;
	protected boolean keyTwoDown = false;
	protected boolean keyThreeDown = false;
	protected boolean keyFourDown = false;
	protected boolean arePanning = false;
	protected int panCount = 0;
	

	protected AdventureHandler gameController;
	
	/*
	 * The public game development is for making 
	 */
	public GameDisplayHandler() 
	{
		// TODO Auto-generated constructor stub
	}
	public void StartGameDraw()
	{
		//Start up and create the display.
		try 
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Display.setVSyncEnabled(false);
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
		GL11.glClearDepth(1);                                       
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glViewport(0,0,800,600);
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		//GL11.glMatrixMode(GL11.GL_PROJECTION);
		//GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		this.updateLoop();

	}

	public void pollInput() 
	{

//		if (Mouse.isButtonDown(0)) 
//		{
//			int x = Mouse.getX();
//			int y = Mouse.getY();
//
//			//System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
//		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) 
		{
			System.out.println("SPACE KEY IS DOWN");
		}

		while (Keyboard.next()) 
		{
			if (Keyboard.getEventKeyState()) 
			{
				if (Keyboard.getEventKey() == Keyboard.KEY_1) 
				{
					keyOneDown = true;
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_2) 
				{
					keyTwoDown = true;
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_3)
				{
					keyThreeDown = true;
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_4)
				{
					keyFourDown = true;
				}
			} 
			else 
			{
				if (Keyboard.getEventKey() == Keyboard.KEY_1)
				{
					keyOneDown = false;
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_2)
				{
					keyTwoDown = false;
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_3)
				{
					keyThreeDown = false;
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_4)
				{
					keyFourDown = false;
				}
			}
		}
		}



		public void updateLoop()
		{
			//TEMPORARY
			//techDemoLoadUp();
			loadUpNewGame();
			//TEMPORARY
			while (!Display.isCloseRequested()) 
			{
				Display.sync(60);
				this.pollInput();
				int mouseXPos = Mouse.getX();
				int mouseYPos = Math.abs( 600-Mouse.getY() );
				boolean isMouseDown = Mouse.isButtonDown(0);

				if( arePanning )
				{
					for( int spriteCount = 0; spriteCount < spriteList.size(); spriteCount++ )
					{
						Sprite moveSprite = spriteList.get(spriteCount); 
						moveSprite.setLocation(moveSprite.getXPos()-1, moveSprite.getYPos());
					}
					for( int wordCount = 0; wordCount < textList.size(); wordCount++ )
					{
						WordCube fetchedCube = textList.get(wordCount);
						fetchedCube.setLocation(fetchedCube.getXPos()-1, fetchedCube.getYPos());
					}
					panCount = panCount - 1;
					if( panCount < 0)
					{
						arePanning = false;
						//gameController.playSetUp();
						System.out.println("Pan done");
						
					}
				}
				
				
				for( int backCount = 0; backCount < backroundOnly.size(); backCount++ )
				{
					Sprite backround = backroundOnly.get(backCount); 
					backround.drawQuad();	
					

				}
				for( int spriteCount = 0; spriteCount < spriteList.size(); spriteCount++ )
				{
					Sprite fetchSprite = spriteList.get(spriteCount); 
					fetchSprite.drawQuad();
					fetchSprite.animate();
					fetchSprite.getMousePos(mouseXPos, mouseYPos);
					fetchSprite.mouseDown(isMouseDown);
					fetchSprite.checkButtonStates(keyOneDown, keyTwoDown, keyThreeDown, keyFourDown);
					

				}
				for( int wordCount = 0; wordCount < textList.size(); wordCount++ )
				{
					WordCube fetchCube = textList.get(wordCount);
					fetchCube.render();
					fetchCube.getMousePos(mouseXPos, mouseYPos);
					fetchCube.mouseDown(isMouseDown);
				}
				
				
				
				for(Combatant e: fighterList)
				{
					e.animate();
				}
				Display.update();
			}
			Display.destroy();
		}




		public void addNewSprite(Sprite newSprite)
		{
			spriteList.add( newSprite );
			//System.out.println(spriteList);
			//System.out.println(spriteList.size());

		}
		
		public void setBackround( Sprite  backroundSprite)
		{
			backroundOnly.add( backroundSprite );
		}
		
		public void wipeDrawnObjects()
		{
			textList = new ArrayList<WordCube>();
			spriteList =  new ArrayList<Sprite>();
		}
		public void addNewWordCube( WordCube newWordCube )
		{
			textList.add( newWordCube );
		}
		public void addNewCombatant( Combatant newCombatant )
		{
			fighterList.add(newCombatant);
		}
		/*
		 * Test only function to demonstrate current state.
		 */
		public void techDemoLoadUp()
		{
			/*
		Sprite Player = new Sprite(100,350);
		Player.init("Player.png");
		addNewSprite(Player);

		for(int enemyCount = 0; enemyCount < 3; enemyCount++ )
		{
			Sprite enemy = new Sprite(200 + 200*enemyCount,350);
			enemy.init("Enemy.png");
			addNewSprite(enemy);
		}
		Sprite ButtonAttack = new Sprite(250,0);
		ButtonAttack.init("Attack.png");
		addNewSprite(ButtonAttack);
		Sprite ButtonDefend = new Sprite(350,0);
		ButtonDefend.init("Defend.png");
		addNewSprite(ButtonDefend);
			 */
		}
		
		public void panScreen()
		{
			Sprite Backround = new Sprite(0,0);
			
			Backround.init("TagQuestBackround2.png");
			
			setBackround(Backround);
			Backround.setLocation(800,0);
			arePanning = true;
			panCount = 800;
			
		}
		
		public void loadUpNewGame()
		{

			Sprite Backround = new Sprite(0,0);
			Backround.init("TagQuestBackround2.png");
			setBackround(Backround);
			gameController = new AdventureHandler(this);
		}

		public static void main(String[] args) 
		{
			// TODO Auto-generated method stub
			GameDisplayHandler display = new GameDisplayHandler();
			display.StartGameDraw();

			System.out.println("Testingtesing");

		}

	}
