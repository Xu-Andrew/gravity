package objects;

import gamestate.GameState;

import java.awt.Graphics;
import java.awt.Rectangle;

import resources.Images;

public class Block extends Rectangle {
    private static final long serialVersionUID = 1L;
    public static final int BLOCK_SIZE = 30;
    int id;
    
    public Block(int x, int y, int id) {
        setBounds(x, y, BLOCK_SIZE, BLOCK_SIZE);
        this.id = id;
    }
    
    public void tick() {
        
    }
    
    public void draw(Graphics g) {
        if (id != 0) {
            g.drawImage(Images.blocks[id - 1], x - (int) GameState.xOffset, y, width, height, null);
        }
        
    }
    
    // get set go
    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
}