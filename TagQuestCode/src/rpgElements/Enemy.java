/**
 * 
 */
package rpgElements;

import guiObjects.Sprite;
import guiObjects.WordCube;

import java.util.Random;

import guiObjects.GameDisplayHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;


/**
 * @author FritzOld
 * Enemy is a simple basic enemy. It doesn't require much to function,
 * and could be extended into more complex variants.
 */
public class Enemy extends CombatantBase {

	protected AdventureHandler controller;
	protected Random randomGen = new Random();
	protected GameDisplayHandler display;
	protected int attackCount = 0;
	
	protected int attackTimer;
	protected int maxAttackTimer = 480;
	
	protected int flashTimer = 0;
	
	
	/**
	 * 
	 */
	public Enemy(AdventureHandler gameRunner) 
	{
		// TODO Auto-generated constructor stub
		setUp();
		controller = gameRunner;
	}
	public Enemy(AdventureHandler gameRunner, GameDisplayHandler mainDisplay) 
	{
		// TODO Auto-generated constructor stub
		
		controller = gameRunner;
		display = mainDisplay;
		setUp();
		
	}
	
	@Override
	public void setUp() 
	{
		// TODO Auto-generated method stub
		//graphicDisplay = new Sprite();
		healthDisplay = new WordCube();
		//graphicDisplay.init("enemy.png");
		this.init("enemy.png");
		healthDisplay.init();
		display.addNewSprite(this);
		display.addNewWordCube(healthDisplay);
		
		
		healthDisplay.init();
		health = 12;
		healthMax = 12;
		updateHealthDisplay();
		
		

	}
	
	public void die()
	{
		//Death animation goes here.
	}
	
	

	@Override
	public void onDeath() 
	{
		// TODO Auto-generated method stub
		//Will call a method in 
		//controller.enemyDeath(this);
		this.animateDefeat();
		controller.onEnemyDeath(this);
		//System.out.println("The de-tagger has been destroyed.");
		

	}

	@Override
	public void takeDamage(int damage) 
	{
		flashTimer = 30;
		// TODO Auto-generated method stub
		health = health - damage;
		updateHealthDisplay();
		

	}

	@Override
	public void dealDamage(Combatant target) 
	{
		//System.out.println("\"Beep boop destory stupid robot\"");
		attackCount++;
		
		// TODO Auto-generated method stub
		target.takeDamage(randomGen.nextInt(4)+3);
		//System.out.println("The detagger attacked! It has attacked "+attackCount+" times.");
		

	}

	@Override
	public void checkIfDead() 
	{
		// TODO Auto-generated method stub
		if(health <= 0)
		{
			this.onDeath();
			//Callback to main function goes here based on if its a player
			//or a computer.
		}
		

	}
	public boolean checkIfDead( String rand ) 
	{
		// TODO Auto-generated method stub
		if(health <= 0)
		{
			this.onDeath();
			return true;
			//Callback to main function goes here based on if its a player
			//or a computer.
		}
		return false;

	}
	
	public void drawQuad()
	{
		super.drawQuad();
		attackTimer += 1;
		if( attackTimer > maxAttackTimer && enemy != null )
		{
			takeTurn();
			attackTimer = 0;
		}
		
		flashTimer += -1;
		if( flashTimer > 0)
		{
			healthDisplay.setColor(1, 0, 0);
		}
		else
		{
			healthDisplay.setColor(0, 0, 0);
		}
		
		
		
		
		if( enemy != null )
		{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		//Color.green.bind();
		//System.out.println((float)(attackTimer)/300);
		//GL11.glColor3f((.07f*((float)(attackTimer))),.5f - (.07f*(float)(attackTimer)),.5f - (.07f*(float)(attackTimer)));
		GL11.glColor3f((float)(attackTimer)/300,0.3f,0.3f);
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(xPos,yPos-50);
		//+((float)attackTimer/(float)maxAttackTimer*boundImage.getImageHeight())
		GL11.glTexCoord2f(1,0);
		GL11.glVertex2f(xPos+((float)attackTimer/(float)maxAttackTimer*boundImage.getImageWidth()),yPos -50);
		//+((float)attackTimer/(float)maxAttackTimer*boundImage.getImageHeight()))
		GL11.glTexCoord2f(1,1);
		GL11.glVertex2f(xPos+((float)attackTimer/(float)maxAttackTimer*boundImage.getImageWidth()),yPos -25);
		GL11.glTexCoord2f(0,1);
		GL11.glVertex2f(xPos,yPos-25);
		GL11.glEnd();
		}
		
	}
	
	@Override
	public void takeTurn() 
	{
		if(!this.checkIfDead("a"))
		{
			this.dealDamage(enemy);
		}
		else
		{
			//clickTimer.stop();
		}
		controller.endTurn(this);
		
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	public void animate()
	{
		if( health <= 0)
			animateDefeat();
	}
	public void animateAttack()
	{
		
	}
	public void animateTakeDamage()
	{
		
	}
	public void animateDefeat()
	{
		if(this.yLoc < 600)
		{
			this.setLocation(xLoc, yLoc+1);
		}
	}
	public void animateIdle()
	{
		
	}
	
	
	@Override
	public void enterCombat(Combatant newEnemy) 
	{
		// TODO Auto-generated method stub
		enemy = newEnemy;
//		clickListener = new ActionListener() 
//		{
//		      public void actionPerformed(ActionEvent evt) 
//		      {
//		          takeTurn();
//		          
//		          
//		          
//		      }
//		};
//		clickTimer = new Timer(3000,clickListener);
//		clickTimer.start();
		
		
		
	}
}
