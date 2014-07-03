package rpgElements;

public interface Combatant 
{
	/*
	 * Set start up combat variables.
	 */
	public void setUp();
	/*
	 * Action to be taken on this Combatants death.
	 */
	public void onDeath();
	/*
	 * Have the object take damage.
	 */
	public void takeDamage( int damage );
	/*
	 * Deal damage to another target combatant.
	 */
	public void dealDamage( Combatant target );
	/*
	 * Check to see if the combatant is dead. 
	 */
	public void checkIfDead();
	/*
	 * Takes the players turn.
	 */
	public void takeTurn();
	/*
	 * Sets the location of the combatant.
	 */
	public void setLocation(int x, int y);
	/*
	 * Set image display
	 */
	public void setImage(String imageName);
	/*
	 * Render the graphics of the Combatant.
	 */
	public void render();
	/*
	 * Sets another combatant instance 
	 */
	public void enterCombat( Combatant enemy );
	/*
	 * Updates the health display to the current ammount.
	 */
	public void updateHealthDisplay();
	
	/*
	 * Animate method hook, called by the main display.
	 */
	public void animate();
	
	public int getXLocation();
	
	public int getYLocation();
	

}
