package rhythmgame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
	
	private Animation enemyAnim;
	
	Enemy() throws SlickException {
		
		Image[] enemyFrames = {
			new Image("res/enemy/rat-0.png"),
			new Image("res/enemy/rat-1.png")
		};
		
		enemyAnim = new Animation(enemyFrames, 500, true);
		
	}
	
	public Animation getEnemyAnim() {
		return enemyAnim;
	}
	
}
