package guiObjects;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

//import wordHandler.BlockGenerator;
//import wordHandler.BlockManager;
//import wordHandler.WordTag;
//import wordHandler.WordBlock;

import java.util.ArrayList;

public class QuadExample {

	Sprite testSprite = new Sprite();
	Sprite testSprite2 = new Sprite( 50, 50 );
	Sprite testSprite3 = new Sprite( 200, 200 );
	Sprite testSprite4 = new Sprite( 300, 150 );
	
	WordCube testCube = new WordCube("potato");
	WordCube testCube2 = new WordCube("potato");
	protected ArrayList<Sprite> spriteBox = new ArrayList<Sprite>();
	protected int tempXLoc = 0;
	protected int tempYLoc = 0;
	ArrayList<WordCube> tagDisplays;
	
	
	public void start() 
	{    	
		//int count = 1;
		try 
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Display.setVSyncEnabled(true);
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		//GL11.glOrtho(800, 0, 600, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		/////
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,800,600);
		//GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		 GL11.glMatrixMode(GL11.GL_PROJECTION);
	     GL11.glLoadIdentity();
		 //GL11.glOrtho(0, 800, 0, 600, 1, -1);
		 //GL11.glOrtho(800, 0, 600, 0, -1, 1);
	     GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		 GL11.glMatrixMode(GL11.GL_MODELVIEW);
		//////
		
		
		spriteBox.add(testSprite);
		spriteBox.add(testSprite2);
		spriteBox.add(testSprite3);
		spriteBox.add(testSprite4);
		
		testSprite3.init("alien.gif");
		testSprite4.init("alien.gif");
		testSprite.init("alien.gif");
		testSprite2.init("alien3.gif");
		
		
		
		testCube.init();
		testCube2.init();
		spriteBox.add(testCube);
		spriteBox.add(testCube2);
		generationDisplayTest();
		for(int k = 0; k < tagDisplays.size(); k++)
		{
			tagDisplays.get(k).init();
		}
		//testCube.updateXLocation(800);
		while (!Display.isCloseRequested()) 
		{
			/**
	    // Clear the screen and depth buffer
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	

	    // set the color of the quad (R,G,B,A)
	    GL11.glColor3f(0.5f,0.5f,1.0f);

	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(100,100);
		GL11.glVertex2f(100+200+count,100+count);
		GL11.glVertex2f(100+200,100+200);
		GL11.glVertex2f(100,100+200);
	    GL11.glEnd();

	    Display.update();
	    count = count +1;
			 **/
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


			//testSprite2.drawQuad();
			//count = count +1;
			//testSprite.updateXLocation(count);
			//testSprite.drawQuad();
			//testSprite2.drawQuad();
			//testSprite3.drawQuad();
			//testSprite4.drawQuad();
			testCube.render();
			//testCube2.updateYLocation(300);
			testCube2.render();
			
			for(int k = 0; k < tagDisplays.size(); k++)
			{
				tagDisplays.get(k).updateXLocation(300);
				//System.out.println(k);
				tagDisplays.get(k).updateYLocation(200 + (k*30));
				tagDisplays.get(k).render();
				
			}
			
			testCube2.updateYLocation(300);
			for( int x = 0; x < spriteBox.size();x++)
			{
				spriteBox.get(x).getMousePos(Mouse.getX(),Math.abs(600-Mouse.getY()));
				//tagDisplays.get(x).getMousePos(Mouse.getX(),Math.abs(600-Mouse.getY()));
			}
			for( int x = 0; x < tagDisplays.size();x++)
			{
				//spriteBox.get(x).getMousePos(Mouse.getX(),Math.abs(600-Mouse.getY()));
				tagDisplays.get(x).getMousePos(Mouse.getX(),Math.abs(600-Mouse.getY()));
			}
			testCube.setText(  Mouse.getX() + " " + Math.abs(600-Mouse.getY()) );
			testCube2.setTextToPos();

			for( int x = 0; x < spriteBox.size();x++)
			{
				spriteBox.get(x).mouseDown(Mouse.isButtonDown(0));
				
			}
			for( int x = 0; x < tagDisplays.size();x++)
			{
				tagDisplays.get(x).mouseDown(Mouse.isButtonDown(0));
				
			}
			if(Mouse.isButtonDown(0))
			{
				
				if(tempXLoc != Mouse.getX() )
				{
				System.out.println("X Location:" + Mouse.getX() );
				System.out.println("Y Location:" + Mouse.getY() );
				tempXLoc = Mouse.getX();
				tempYLoc = Mouse.getY();
				}
				
			}



			Display.update();
		}

		Display.destroy();
	}
	
	public void generationDisplayTest()
	{
//		BlockGenerator genTest = new BlockGenerator();
//		BlockManager testManager = new BlockManager();
//		
//		genTest.setBlockManager(testManager);
//		genTest.setUp();
//		tagDisplays = new ArrayList<WordCube>();
//		genTest.makeNewBlock(5);
////		System.out.println("--------Next Group--------");
////		genTest.makeNewBlock(4);
////		System.out.println("--------Next Group--------");
////		genTest.makeNewBlock(3);
////		System.out.println("--------Next Group--------");
////     	genTest.makeNewBlock(2);
////		System.out.println("--------Next Group--------");
////		genTest.makeNewBlock(1);
//		//WordTag testTag = testManager.getBlockCase().get(0).wordList.get(0);
//		
//		for(int x = 0; x < testManager.getBlockCase().size(); x++)
//		{
//			WordBlock blockCube = testManager.getBlockCase().get(x);
//			for(int y = 0; y < blockCube.wordList.size(); y++)
//			{
//				blockCube.wordList.get(y);
//				WordCube newCube = new WordCube();
//				newCube.setWordTagLink(blockCube.wordList.get(y));
//				newCube.setTextSize(16);
//				tagDisplays.add(newCube);
//			}
//		}
//		System.out.println(tagDisplays.size());
	}

	public static void main(String[] argv) {
		QuadExample quadExample = new QuadExample();
		quadExample.start();
	}
}
