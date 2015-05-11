package gamestate;

import main.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class MenuState extends GameState {
    private int score;
    private String[] options = {"Start", "Help", "Quit"};
    private int currentSelection = 0;
    
    MenuState(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
    }

    public void init() {
        // TODO Auto-generated method stub
        
    }

    public void tick() {
        // TODO Auto-generated method stub
        
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 95, 255));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                g.setColor(Color.GREEN);
            }
            else {
                g.setColor(Color.WHITE);
            }
            
            g.setFont(new Font("elephant", Font.PLAIN, 72));
            g.drawString(options[i], i * (GamePanel.WIDTH / options.length) + g.getFont().getSize(),
                    GamePanel.HEIGHT - GamePanel.HEIGHT / 4);
            g.setColor(Color.BLACK);
            g.drawString("Gravity", GamePanel.WIDTH / 3, GamePanel.HEIGHT / 3);
            if (score > 0) {
                g.setFont(new Font("elephant", Font.PLAIN, 36));
                g.drawString("Highscore: " + Integer.toString(score), 
                        GamePanel.WIDTH / 3, GamePanel.HEIGHT / 10);
            }
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = options.length - 1;
            }
        }
        else if (k == KeyEvent.VK_RIGHT) {
            currentSelection++;
            if (currentSelection >= options.length) {
                currentSelection = 0;
            }
        }
        if (k == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
                gsm.states.push(new Level1State(gsm, score));
            }
            else if (currentSelection == 1) {
                gsm.states.push(new HelpState(gsm, score));
            }
            else if (currentSelection == 2) {
                System.exit(0);
            }
        }
    }

    public void keyReleased(int k) {
        // TODO Auto-generated method stub
        
    }
    
}