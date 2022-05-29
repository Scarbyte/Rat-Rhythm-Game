// Game created by Mason Armand for Mr Speros AP Comp Sci class

package rhythmgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Driver {
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Game());
			app.setDisplayMode(700, 900, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
