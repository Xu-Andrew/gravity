package gamestate;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.GamePanel;



class EndState extends GameState {
    int score;
    int highScore;
    
    EndState(GameStateManager gsm, int score, int highScore) {
        super(gsm);
        this.score = score;
        this.highScore = highScore;
    }

    
    public void init() {
        
    }

    
    public void tick() {
        
    }

    
    public void draw(Graphics g) {
        g.setFont(new Font("elephant", Font.PLAIN, 36));
        g.drawString("Score: " + Integer.toString(score), GamePanel.WIDTH / 3, GamePanel.HEIGHT / 3);
        g.drawString("Highscore: " + Integer.toString(highScore), GamePanel.WIDTH / 3, GamePanel.HEIGHT / 2);
        g.drawString("Press ENTER to return to menu", GamePanel.WIDTH / 3, 3 * (GamePanel.HEIGHT / 4));
    }

    
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            gsm.states.push(new MenuState(gsm, highScore));
        }
    }

    
    public void keyReleased(int k) {
        
        
    }
    
}