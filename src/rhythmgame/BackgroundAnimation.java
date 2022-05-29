package rhythmgame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BackgroundAnimation {
	
	private Animation bgAnim;
	private int frameSize = 366;
	
	BackgroundAnimation() throws SlickException {
		
		// load each frame of background animation
		Image[] bgFrames = {
			new Image("res/bg/frame-0.gif"),
			new Image("res/bg/frame-1.gif"),
			new Image("res/bg/frame-2.gif"),
			new Image("res/bg/frame-3.gif"),
			new Image("res/bg/frame-4.gif"),
			new Image("res/bg/frame-5.gif"),
			new Image("res/bg/frame-6.gif"),
			new Image("res/bg/frame-7.gif"),
			new Image("res/bg/frame-8.gif"),
			new Image("res/bg/frame-9.gif"),
			new Image("res/bg/frame-10.gif"),
			new Image("res/bg/frame-11.gif"),
			new Image("res/bg/frame-12.gif"),
			new Image("res/bg/frame-13.gif"),
			new Image("res/bg/frame-14.gif"),
			new Image("res/bg/frame-15.gif"),
			new Image("res/bg/frame-16.gif"),
			new Image("res/bg/frame-17.gif"),
			new Image("res/bg/frame-18.gif"),
			new Image("res/bg/frame-19.gif"),
			new Image("res/bg/frame-20.gif"),
			new Image("res/bg/frame-21.gif"),
			new Image("res/bg/frame-22.gif"),
			new Image("res/bg/frame-23.gif")
		};
		
		bgAnim = new Animation(bgFrames, 40, true);
		
	}
	
	public Animation getBgAnim() {
		return bgAnim;
	}
	
	public int getFrameSize() {
		return frameSize;
	}
	
}
