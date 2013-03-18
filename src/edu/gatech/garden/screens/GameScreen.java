package edu.gatech.garden.screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.gatech.garden.gui.ActionBarButton;
import edu.gatech.garden.gui.BigButton;
import edu.gatech.garden.gui.CenterText;
import edu.gatech.garden.main.Garden;
import edu.gatech.garden.tiles.*;

/**
 * @version 1.0
 * @author Glenn
 */
public class GameScreen extends Screen {
	public enum Act {
		IDLE, TILL, PCORN, PCARROT, PTOMATO, WATER, PICK;
	}
	
	private Act curAct = Act.IDLE;
	
    private static Tile[][] map = new Tile[25][22];
    private static final Rectangle MAP_BOUNDS = new Rectangle(200, 0, WIDTH - 200, HEIGHT - 46),
    		MENU_BOUNDS = new Rectangle(200, HEIGHT - 46, WIDTH, 46);
    private int time = 300, week = 1, stamina = 25, score = 0;
    private long lastSecond;
    private BigButton nextWeek, quitGame;
	private ArrayList<ActionBarButton> actionBar = new ArrayList<ActionBarButton>();
	private boolean tutorial = true;

    /**
     * Constructor for GameScreen.
     */
    public GameScreen() {
    	quitGame = new BigButton("Quit Game", 100, HEIGHT - 50);
    	nextWeek = new BigButton("Next Week", 100, HEIGHT - 60 - quitGame.getHeight());
    	for (int i = 1; i <= 6; i++) {
        	actionBar.add(new ActionBarButton(i));
    	}
    	
    	//---------------------
        // Grass
        //---------------------
    	for (int x = 0; x < map.length; x++) {
    		for (int y = 0; y < map[0].length; y++) {
    			map[x][y] = new GrassTile(200 + (x * 32), y * 32);
    		}
    	}

    	lastSecond = System.currentTimeMillis();
    }
    
    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        //---------------------
        // Sidebar
        //---------------------
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 200, HEIGHT);
        g.setColor(Color.WHITE);
        CenterText.string(g, "Week " + week, 100, 15);
        CenterText.string(g, "Time Left: " + time / 60 + ":" + (time % 60 < 10 ? "0" + time % 60 : time % 60), 100, 45);
       
        // Stamina
        CenterText.string(g, "Stamina:", 100, 60);
        g.setColor(Color.BLACK);
        g.drawRect(24, 64, 151, 11);
        g.setColor(Color.GREEN);
        g.fillRect(25, 65, stamina * 6, 10);
        g.setColor(Color.RED);
        g.fillRect(25 + stamina * 6, 65, (25 - stamina) * 6, 10);
        g.setColor(Color.BLACK);
        g.drawLine(25 + stamina * 6, 65, 25 + stamina * 6, 75);
        g.setColor(Color.WHITE);
        
        CenterText.string(g, "Score: " + score, 100, 100);
        String curActStr = "";
        switch(curAct) {
        	case IDLE:
        		curActStr = "Idle";
        		break;
        	case TILL:
        		curActStr = "Tilling";
        		break;
        	case PCORN:
        		curActStr = "Planting Corn";
        		break;
        	case PCARROT:
        		curActStr = "Planting Carrot";
        		break;
        	case PTOMATO:
        		curActStr = "Planting Tomato";
        		break;
        	case WATER:
        		curActStr = "Watering";
        		break;
        	case PICK:
        		curActStr = "Harvesting";
        		break;
        	default:
        		curActStr = "FIX";
        		break;
        }
        CenterText.string(g, "Current Action: " + curActStr, 100, 115);
        
        if (time == 0) {
        	CenterText.string(g, "Out of Time!", 100, 485);
        	CenterText.string(g, "Click Next Week or", 100, 500);
        	CenterText.string(g, "Press Space to Continue", 100, 515);
        }
        if (stamina == 0) {
        	CenterText.string(g, "Out of Stamina!", 100, 545);
        	CenterText.string(g, "Click Next Week or", 100, 560);
        	CenterText.string(g, "Press Space to Continue", 100, 575);
        }
        quitGame.draw(g);
        nextWeek.draw(g);
                
        //---------------------
        // Bottom Bar
        //---------------------
        g.setColor(Color.DARK_GRAY);
        g.fillRect(200, HEIGHT - 46, WIDTH, 46);
        for (ActionBarButton a : actionBar) {
        	a.draw(g);
        }
        
        //---------------------
        // Map
        //---------------------
        g.setColor(Color.BLACK);
        for (int x = 0; x < map.length; x++) {
    		for (int y = 0; y < map[0].length; y++) {
    			map[x][y].draw(g);
    		}
    	}
        
        if (hoverPoint != null && MAP_BOUNDS.contains(hoverPoint)) {
        	Tile t = map[(hoverPoint.x - 200) / 32][hoverPoint.y / 32];
        	if ((curAct == Act.TILL && t instanceof GrassTile) ||
        	((curAct == Act.PCORN  || curAct == Act.PCARROT || curAct == Act.PTOMATO) && t instanceof TilledTile) ||
        	(curAct == Act.WATER && t instanceof CropTile && !((CropTile) t).isWatered()) ||
        	(curAct == Act.PICK && t instanceof CropTile) && ((CropTile) t).isGrown()) {
        		g.drawRect((((hoverPoint.x - 200) / 32) * 32) + 200, (hoverPoint.y / 32) * 32, 32, 32);
        	}
        }
        
        //--------------------
        // Tutorial
        //--------------------
        if (tutorial) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			final Font defaultFont = g.getFont(); 
			g.setFont(new Font("serif", Font.PLAIN, 25));
			g.drawString("How to Play:", (WIDTH / 2)
					- (g.getFontMetrics().stringWidth("How to Play:") / 2),
					(HEIGHT / 2) - 75);
			g.drawString("Read all White Text", (WIDTH / 2)
					- (g.getFontMetrics().stringWidth("Read all White Text") / 2),
					(HEIGHT / 2) - 25);
			g.drawString("When Ready, Click Anywhere to Play", (WIDTH / 2)
					- (g.getFontMetrics().stringWidth("When Ready, Click Anywhere to Play") / 2),
					(HEIGHT / 2));
			g.setFont(defaultFont);
			CenterText.string(g, "You only have a limited amount", 100, 30);
			CenterText.string(g, "of stamina and time each week.", 100, 87);
			CenterText.string(g, "Harvest the most plants", 100, 130);
			CenterText.string(g, "and get the highest score", 100, 145);
			CenterText.string(g, "in only 10 school weeks to win!", 100, 160);
			CenterText.string(g, "When you are done for the week", 100, 575);
			CenterText.string(g, "click Next Week or press Space.", 100, 590);
			g.drawString("Click an Action Button or press the corresponing number " + 
					"to till/plant/water/harvest.", 415, HEIGHT - 20);
        }
    }

    /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point p) {
    	if (tutorial) {
    		tutorial = false;
    		lastSecond = System.currentTimeMillis();
    	} else {
			if (MAP_BOUNDS.contains(p)) {
				Tile t = map[(p.x - 200) / 32][p.y / 32];
				if (stamina > 0 && time > 0 && t.checkForClick(p)) {
					if (curAct == Act.TILL && t instanceof GrassTile) {
						map[(p.x - 200) / 32][p.y / 32] = new TilledTile(t.x, t.y);
						stamina--;
					} else if (curAct == Act.PCORN && t instanceof TilledTile) {
						map[(p.x - 200) / 32][p.y / 32] = new CornTile(t.x, t.y);
						stamina--;
					} else if (curAct == Act.PCARROT && t instanceof TilledTile) {
						map[(p.x - 200) / 32][p.y / 32] = new CarrotTile(t.x, t.y);
						stamina--;
					} else if (curAct == Act.PTOMATO && t instanceof TilledTile) {
						map[(p.x - 200) / 32][p.y / 32] = new TomatoTile(t.x, t.y);
						stamina--;
					} else if (curAct == Act.WATER && t instanceof CropTile && !((CropTile) t).isWatered()) {
						((CropTile) t).water();
						stamina--;
					}else if (curAct == Act.PICK && t instanceof CropTile) {
						if (((CropTile) t).isGrown() && stamina >= ((CropTile) t).getStamina()) {
							score += ((CropTile) t).getScore();
							stamina -= ((CropTile) t).getStamina();
							map[(p.x - 200) / 32][p.y / 32] = new GrassTile(t.x, t.y);
						}
					}
				}
			} else if (MENU_BOUNDS.contains(p)) {
				for (ActionBarButton a : actionBar) {
					if (a.isClicked(p)) {
						if (curAct != a.getAct()) {
							curAct = a.getAct();
						} else {
							curAct = Act.IDLE;
						}
					}
				}
			} else if (nextWeek.isClicked(p)) {
				time = 300;
				week++;
				stamina = 25;
				
				if (week == 11) {
					Garden.PANEL.setActiveScreen(new GameEndScreen(score));
				}
				
				for (int x = 0; x < map.length; x++) {
		    		for (int y = 0; y < map[0].length; y++) {
		    			map[x][y].advance();
		    		}
		    	}
				
	    		lastSecond = System.currentTimeMillis();
			} else if (quitGame.isClicked(p)) {
				Garden.PANEL.setActiveScreen(new TitleScreen());
			}
    	}
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    	if (!tutorial) {
    		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
	    		time = 300;
				week++;
				stamina = 25;
				
				if (week == 11) {
					Garden.PANEL.setActiveScreen(new GameEndScreen(score));
				}
				
				for (int x = 0; x < map.length; x++) {
		    		for (int y = 0; y < map[0].length; y++) {
		    			map[x][y].advance();
		    		}
		    	}
				
	    		lastSecond = System.currentTimeMillis();
	    	} else {
		    	for (ActionBarButton a : actionBar) {
		    		if (a.checkButton(e.getKeyChar())) {
		    			if (curAct != a.getAct()) {
							curAct = a.getAct();
						} else {
							curAct = Act.IDLE;
						}
		    		}
				}
	    	}
    	}
    }
    
    @Override
    public void tick() {
		if (!tutorial && (System.currentTimeMillis() - lastSecond > 1000) && (time > 0)) {
			time--;
    		lastSecond = System.currentTimeMillis();
		}
    }

	@Override
	public void setHoverPoint(Point p) {
		this.hoverPoint = p;
		if (!tutorial) {
			nextWeek.setHovered(p);
			quitGame.setHovered(p);
		}
	}
}
