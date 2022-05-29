package rhythmgame;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

public class Game extends BasicGame {
	
	private BackgroundAnimation bgEffect;
	private Enemy rat;
	
	private Image background;
	private Image emptyBackground;
	private Image goodMiss;
	private Image good;
	private Image miss;
	private Image blank;
	private Image selection;
	
	private Music music;
	private Sound missedNote;
	
	private int position = 100;
	private int combo = 0;
	private int highestCombo = 0;
	private int notesHit = 0;
	private int damage = 0;
	
	private boolean gameStart = false;
	private boolean started = false;
	private boolean hitNote = false;
	
	private Font font;
	private TrueTypeFont trueTypeFont;
	
	private ArrayList<Note> notes = new ArrayList<Note>();
	
	
	public Game() {
		// Title of game window
		super("RATS RATS WE ARE THE RATS, CELEBRATING YET ANOTHER BIRTHDAY BASH");
	}

	
	// runs once when game is created
	// used for loading sprites and stuff
	@Override
	public void init(GameContainer container) throws SlickException {
		
		bgEffect = new BackgroundAnimation();
		rat = new Enemy();
		
		blank = new Image("res/BLANK.png");
		goodMiss = blank;
		good = new Image("res/GOOD.png");
		miss = new Image("res/MISS.png");
		selection = new Image("res/selection.png");
		
		missedNote = new Sound("res/soundEffects/missed-note.wav");
		music = new Music("res/music/rats-birthday-mixtape.wav");
		
		font = new Font("Time New Roman", Font.BOLD, 15);
		trueTypeFont = new TrueTypeFont(font, true);
		
		background = new Image("res/play-field-simple.png");
		emptyBackground = new Image("res/bg/empty-bg.png");

		
		loadNotes();
		
	}
	
	
	public void loadNotes() throws SlickException {
		
		// load text file containing the note positions
		try {
			File file = new File("res/songMaps/song.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			
			int counter = 0;
			
			// loop through each line in the txt file
			while ((line = br.readLine()) != null) {
				
				// if a line contains an "x" we spawn a note at that position
				// "x" = regular note
				// "+" = skull note
				// everything else in txt file is ingored
				if (line.contains("x")) {
					int notePos = line.indexOf("x");
					
					Note note = new Note((notePos * 125) + 100, -counter * 125, false);
					notes.add(note);
							
				} else if (line.contains("+")) {
						
						int index = line.indexOf("+");
						while (index >= 0) {
							Note note = new Note((index * 125) + 100, -counter * 125, true);
							notes.add(note);
							
							index = line.indexOf("+", index + 1);
						}
				}
				
				counter++;
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	// used to update game logic (update player position, etc)
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		// check for key presses
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_H)) {
			position = 100;
		}
		
		if (input.isKeyPressed(Input.KEY_J)) {
			position = 225;
		}
		
		if (input.isKeyPressed(Input.KEY_K)) {
			position = 350;
		}
		
		if (input.isKeyPressed(Input.KEY_L)) {
			position = 475;
		}
		
		if (!gameStart) {
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				music.play();
				music.setVolume(0.3f);
				gameStart = true;
			}
		}
		
		if (gameStart) {

			// loop through each note
			for (int i = 0; i < notes.size(); i++) {
				Note note = notes.get(i);
				
				// if the note "collides" with the player cursor
				if (note.getPosY() >= 775 && note.getPosY() <= 800 && note.getPosX() == position) {
					// if the note is not a skull note then we add to the combo, else we play the wrong note sound
					if (note.getEvil() == false) {
						hitNote = true;
						combo += 1;
						notesHit += 1;
						
						if (combo > highestCombo) {
							highestCombo = combo;
						}
	
					} else {
						missedNote.play(1, 0.2f);
						hitNote = false;
						combo = 0;
						damage += 1;
					}
					
					note.destroy();
					notes.remove(note);
					started = true;
					
				}
				// if a note goes to the bottom of the screen (the player doesnt catch it)
				else if (note.getPosY() >= 900) {
					
					if (note.getEvil() == false) {
						missedNote.play(1, 0.2f);
					}
					
					note.destroy();
					notes.remove(note);
					hitNote = false;
					combo = 0;
					
					started = true;
					
				} 
				else {
					// update note position
					note.setPosY(note.getPosY() + 0.65f * delta);
				}
				
			}
		}
		
	}
	
	
	// used to display everything to the screen.
	// The amount of times per second that this gets called determines the FPS of the game
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		if (gameStart) {
		
			// draw bg tiles
			for (int h = 0; h < 3; h++) {
				for (int w = 0; w < 2; w++) {
					bgEffect.getBgAnim().draw(bgEffect.getFrameSize() * w, bgEffect.getFrameSize() * h);
				}
			}
			
			g.drawImage(background, 100, 0);
			g.drawImage(blank, 600, 850);
			
			// draw combo and message displaying whether or not player missed or hit a note
			if (hitNote == true && started) {
				g.setFont(trueTypeFont);
				g.setColor(new Color(210, 115, 138, 255));
				g.drawString("COMBO! X" + combo, 600, 870);
				goodMiss = good;
			} else if (started){
				goodMiss = miss;
			}
			
			g.drawImage(goodMiss, 0, 850);
			
			// draw the notes themselves
			for (int i = 0; i < notes.size(); i++) {
				Note note = notes.get(i);
				// draw notes only if they're on screen
				if (note.getPosY() >= -120) {
					g.drawImage(note.getImage(), note.getPosX(), note.getPosY());
				}
			}
			
			// draw player cursor
			g.drawImage(selection, position + 31, 806);
			// draw the dancing rat
			rat.getEnemyAnim().draw(200, 0);
			
			// end score screen when all notes are gone
			if (notes.size() == 0) {
				g.drawImage(emptyBackground, 0, 0);
				
				g.setFont(trueTypeFont);
				g.setColor(new Color(210, 115, 138, 255));
				g.drawString("Highest Combo: " + highestCombo, 0, 60);
				g.drawString("Score: " + (notesHit * highestCombo - damage), 0, 100);
				g.drawString("Yes, the song was not fully finished and I had to cut it off early :(", 0, 140);
				goodMiss = good;
			}
		
		} else {
			g.setFont(trueTypeFont);
			g.setColor(new Color(210, 115, 138, 255));
			g.drawString("Try to collide with/collect the falling notes", 0, 60);
			g.drawString("Avoid notes with a skull on them", 0, 100);
			g.drawString("Controls are the H, J, K, L keys to change what column you're in", 0, 140);
			g.drawString("Press space when you are ready!", 0, 180);
		}
			
	}
	
}
