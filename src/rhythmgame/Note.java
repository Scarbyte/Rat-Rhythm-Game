package rhythmgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Note {
	
	private float posX;
	private float posY;
	
	private Image note;
	
	private boolean evil;
	
	Note(float posX, float posY, boolean isEvil) throws SlickException{
		this.posX = posX;
		this.posY = posY;
		this.evil = isEvil;

		
		if (!evil) {
			if (posX == 100) {
				note = new Image("res/notes/note-H.png");
			}
			else if (posX == 225) {
				note = new Image("res/notes/note-J.png");
			}
			else if (posX == 350) {
				note = new Image("res/notes/note-K.png");
			}
			else if (posX == 475) {
				note = new Image("res/notes/note-L.png");
			}
		} else {
			note = new Image("res/notes/note-skull.png");
		}
		
	}
	
	public void destroy() throws SlickException{
		this.posX = 0;
		this.posY = 0;
		note = null;
	}
	
	public Image getImage() {
		return note;
	}
	
	public Boolean getEvil() {
		return evil;
	}
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public void setPosX(float x) {
		posX = x;
	}
	
	public void setPosY(float d) {
		posY = d;
	}
	
}
