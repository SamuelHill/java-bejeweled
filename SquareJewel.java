/* Filename: SquareJewel.java
 * Creates a Square Jewel
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.awt.*;

public class SquareJewel extends Jewels {
  
  public SquareJewel(int gridX, int gridY) {
    super(gridX, gridY, Color.cyan);
  }
  
  protected void drawShape(Graphics g, int drawX, int drawY) {
    drawSquare(g, drawX, drawY);
  }
  
  private void drawSquare(Graphics g, int drawX, int drawY) {
    g.fillRect((drawX + (PIECE_BUFFER/2)), (drawY + (PIECE_BUFFER/2)), 
               (PIECE_SIZE - PIECE_BUFFER), (PIECE_SIZE - PIECE_BUFFER));
  }
  
  public int getType() {
    return SQUARE;
  }
}