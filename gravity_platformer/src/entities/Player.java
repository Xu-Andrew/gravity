package entities;

import gamestate.GameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import objects.Block;
import physics.Collision;
import main.GamePanel;

public class Player {
    // color
    Color col1;
    Color col2;
    // movement booleans
    public boolean right = true, left = false;
    public boolean topJumping = false, topFalling = false;
    public boolean botJumping = false, botFalling = false;
    boolean topCollision = false;
    boolean botCollision = false;
    // bounds
    double x, y, y1, y2;
    int width, height;
    // move speed
    double moveSpeed = 2.5;
    // jump speed
    private double jumpSpeed = 5;
    private double topCurrentJumpSpeed = jumpSpeed;
    private double botCurrentJumpSpeed = jumpSpeed;
    // fall speed
    private double maxFallSpeed = 9.81;
    private double topCurrentFallSpeed = 0.1;
    private double botCurrentFallSpeed = 0.1;
    
    public Player(int width, int height, int y, Color col1, Color col2) {
        this.x = GamePanel.WIDTH / 2;
        this.width = width;
        this.height = height;
        this.y = y;
        this.y2 = y + (GamePanel.HEIGHT / 4);
        this.y1 = y - (GamePanel.HEIGHT / 4);
        this.col1 = col1;
        this.col2 = col2;
    }
    
    // top collision helper, returns true if point collides with block
    public boolean collideTop(Block b, double y, double speed) {
        int iX = (int) (x + GameState.xOffset + 1);
        int iY = (int) (y - speed);
        if (Collision.playerBlock(new Point(iX, iY), b)
                || Collision.playerBlock(new Point(iX + width - 3, iY), b)) {
            return true;
        }
        return false;
    }

    // bottom collision helper, returns true if point collides with block
    public boolean collideBottom(Block b, double y, double speed) {
        int iX = (int) (x + GameState.xOffset + 2);
        int iY = (int) (y + height + 1 + speed);
        if (Collision.playerBlock(new Point(iX, iY), b)
                || Collision.playerBlock(new Point(iX + width - 4, iY), b)) {
            return true;
        }
        return false;
    }
    
    // top player tick
    public void topPlayerCollision(Block[][] b) {
        int iX = (int) x;
        int iY = (int) y1;
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j].getID() != 0) {
                    // right collision
                    if (Collision.playerBlock(new Point(iX + width
                            + (int) GameState.xOffset, iY + 2), b[i][j])
                            || Collision.playerBlock(
                                    new Point(iX + width
                                            + (int) GameState.xOffset, iY
                                            + height - 1), b[i][j])) {
                        right = false;
                    }
                    // left collision
                    if (Collision.playerBlock(new Point(iX
                            + (int) GameState.xOffset - 1, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX
                                    + (int) GameState.xOffset - 1, iY + height
                                    - 1), b[i][j])) {
                        left = false;
                    }
                    // top collision
                    if (collideTop(b[i][j], y1, topCurrentJumpSpeed)) {

                        topJumping = false;
                    }
                    // bottom collision
                    if (collideBottom(b[i][j], y1, topCurrentFallSpeed)) {
                        y1 = b[i][j].getY() - height;
                        topFalling = false;
                        topCollision = true;
                    } 
                    else {
                        if (!topCollision && !topJumping) {
                            topFalling = true;
                        }
                    }
                }
            }
        }
        
        topCollision = false;
        
        if (topJumping) {
            y1 -= topCurrentJumpSpeed;
            topCurrentJumpSpeed -= .1;
            
            if (topCurrentJumpSpeed <= 0) {
                topCurrentJumpSpeed = jumpSpeed;
                topJumping = false;
                topFalling = true;
            }
        }
        if (topFalling && y1 + topCurrentFallSpeed >= y - height) {
            topFalling = false;
            y1 = y - height;
        }
        if (topFalling) {
            y1 += topCurrentFallSpeed;
            
            if (topCurrentFallSpeed < maxFallSpeed) {
                topCurrentFallSpeed += .1;
            }
        }
        if (!topFalling) {
            topCurrentFallSpeed = .1;
        }
    }
    
    // bottom player tick
    public void botPlayerCollision(Block[][] b) {
        int iX = (int) x;
        int iY = (int) y2;
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j].getID() != 0) {
                    // right collision
                    if (Collision.playerBlock(new Point(iX + width
                            + (int) GameState.xOffset, iY + 2), b[i][j])
                            || Collision.playerBlock(
                                    new Point(iX + width
                                            + (int) GameState.xOffset, iY
                                            + height - 1), b[i][j])) {
                        right = false;
                    }
                    // left collision
                    if (Collision.playerBlock(new Point(iX
                            + (int) GameState.xOffset - 1, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX
                                    + (int) GameState.xOffset - 1, iY + height
                                    - 1), b[i][j])) {
                        left = false;
                    }
                    // top collision
                    if (collideBottom(b[i][j], y2, botCurrentJumpSpeed)) {
                        botJumping = false;
                    }
                    // bottom collision
                    if (collideTop(b[i][j], y2, botCurrentFallSpeed)) {
                        y2 = b[i][j].getY() + height;
                        botFalling = false;
                        botCollision = true;
                    } 
                    else {
                        if (!botCollision && !botJumping) {
                            botFalling = true;
                        }
                    }
                }
            }
        }
        
        botCollision = false;
        
        if (botJumping) {
            y2 += botCurrentJumpSpeed;
            botCurrentJumpSpeed -= .1;
            
            if (botCurrentJumpSpeed <= 0) {
                botCurrentJumpSpeed = jumpSpeed;
                botJumping = false;
                botFalling = true;
            }
        }
        if (botFalling && y2 - botCurrentFallSpeed <= y) {
            botFalling = false;
            y2 = y;
        }
        if (botFalling) {
            y2 -= botCurrentFallSpeed;
            
            if (botCurrentFallSpeed < maxFallSpeed) {
                botCurrentFallSpeed += .1;
            }
        }
        if (!botFalling) {
            botCurrentFallSpeed = .1;
        }
    }
    
    public void tick(Block[][] b) {
        
        topPlayerCollision(b);
        botPlayerCollision(b);
        
        if (right) {
            GameState.xOffset += moveSpeed;
        }
        if (left) {
            GameState.xOffset -= moveSpeed;
        }
        
    }
    
    public void draw(Graphics g) {
        g.setColor(col1);
        g.fillRect((int) x, (int) y1, width, height);
        g.setColor(col2);
        g.fillRect((int) x, (int) y2, width, height);
    }
    
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_Z && !topJumping && !topFalling) {
            topJumping = true;
        }
        if (k == KeyEvent.VK_COMMA && !botJumping && !botFalling) {
            botJumping = true;
        }
    }
    
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_Z) {
            topFalling = true;
        }
        if (k == KeyEvent.VK_COMMA) {
            botFalling = true;
        }
    }
}