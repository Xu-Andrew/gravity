package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import objects.Block;
import main.GamePanel;
import mapping.Map;
import entities.Player;

public class Level1State extends GameState {
    private int tick;
    private int score = 0;
    private int highScore;
    private Player player;
    private Map map;
    private boolean right = player.right, left = player.left;
    
    public Level1State(GameStateManager gsm, int highScore) {
        super(gsm);
        this.highScore = highScore;
    }

    public void init() {
        player = new Player(30, 30, GamePanel.HEIGHT / 2, Color.RED, Color.BLUE);
        map = new Map("/maps/map1.map");
    }

    public void tick() {
        if (right && GameState.xOffset > Block.BLOCK_SIZE * (map.width + 10)) {
            GameState.xOffset = 0;
        }
        if (left && GameState.xOffset < 0 - (Block.BLOCK_SIZE * 10)) {
            GameState.xOffset = Block.BLOCK_SIZE * map.width;
        }
        player.tick(map.getBlocks());
        right = player.right;
        left = player.left;
        
        if (!right && !left) {
            if (score > highScore) {
                gsm.states.push(new EndState(gsm, score, score));
            }
            else {
                gsm.states.push(new EndState(gsm, score, highScore));
            }
        }
        
        tick++;
        if (tick > 10) {
            score++;
            tick = 0;
        }
        
        if (score % 1000 == 0) {
            player.left = !player.left;
            player.right = !player.right;
        }
    }

    public void draw(Graphics g) {
        g.drawLine(0, GamePanel.HEIGHT / 2, GamePanel.WIDTH, GamePanel.HEIGHT / 2);
        player.draw(g);
        map.draw(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("elephant", Font.PLAIN, 36));
        g.drawString(Integer.toString(score), GamePanel.WIDTH / 2, GamePanel.HEIGHT / 7);
        g.drawString(Integer.toString(highScore), GamePanel.WIDTH / 2, GamePanel.HEIGHT / 12);
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public void keyReleased(int k) {
        player.keyReleased(k);
    }
    
}