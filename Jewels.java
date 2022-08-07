/* Filename: Jewels.java
 * Super class to all 7 of the types of Jewels, defines what the Jewels can be.
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.awt.*;

public abstract class Jewels {
  public static final int DIAMOND = 0;
  public static final int CIRCLE = 1;
  public static final int RHOMBUS = 2;
  public static final int TRIANGLE = 3;
  public static final int OCTAGON = 4;
  public static final int SQUARE = 5;
  public static final int HEXAGON = 6;
  public static final int PIECE_SIZE = 40;
  public static final int PIECE_BUFFER = 4;
  public static final Color BORDER_COLOR = Color.white;
  protected int gridX;
  protected int gridY;
  protected Color color;
  protected boolean selected;
  
  public Jewels(int gridX, int gridY, Color color) {
    this.gridX = gridX;
    this.gridY = gridY;
    this.color = color;
  }
  
  public void drawPiece(Graphics g) {
    int drawX = gridX * Jewels.PIECE_SIZE;
    int drawY = gridY * Jewels.PIECE_SIZE;
    if (selected == true) {
      g.setColor(BORDER_COLOR);
      g.fillRect(drawX, drawY, Jewels.PIECE_SIZE, Jewels.PIECE_SIZE);
    }
    g.setColor(color);
    drawShape(g, drawX, drawY);
  }
  
  protected abstract void drawShape(Graphics g, int drawX, int drawY);
  public abstract int getType();
  
  public void select() {
    this.selected = true;
  }
  
  public void unselect() {
    this.selected = false;
  }
  
  public int getX() {
    return this.gridX;
  }
  
  public int getY() {
    return this.gridY;
  }
  
  public void setY(int y) {
    this.gridY = y;
  }
}