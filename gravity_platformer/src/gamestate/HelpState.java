package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.GamePanel;

class HelpState extends GameState {
    int score;
    int page = 0;
    String[] text = {"Press left and right arrow keys to scroll through help. ",
            "Press enter to return to menu.", "Press z or , to jump. Avoid hitting blocks."};
    
    HelpState(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
    }

    
    public void init() {
        
    }

    
    public void tick() {
        // TODO Auto-generated method stub
        
    }

    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("elephant", Font.PLAIN, 36));
        g.drawString(text[page], 0, GamePanel.HEIGHT / 2);
    }

    
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            page--;
            if (page < 0) {
                page = text.length - 1;
            }
        }
        if (k == KeyEvent.VK_RIGHT) {
            page++;
            if (page >= text.length) {
                page = 0;
            }
        }
        if (k == KeyEvent.VK_ENTER) {
            gsm.states.push(new MenuState(gsm, score));
        }
        
    }

    
    public void keyReleased(int k) {
        // TODO Auto-generated method stub
        
    }
    
    
}