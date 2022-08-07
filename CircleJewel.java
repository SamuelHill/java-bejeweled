/* Filename: CircleJewel.java
 * Creates a Circle Jewel.
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.awt.*;

public class CircleJewel extends Jewels {
  
  public CircleJewel(int gridX, int gridY) {
    super(gridX, gridY, Color.blue);
  }
  
  protected void drawShape(Graphics g, int drawX, int drawY) {
    drawCircle(g, drawX, drawY);
  }
  
  private void drawCircle(Graphics g, int drawX, int drawY) {
    g.fillOval((drawX + (PIECE_BUFFER/2)), (drawY + (PIECE_BUFFER/2)),
               (PIECE_SIZE - PIECE_BUFFER), (PIECE_SIZE - PIECE_BUFFER));
  }
  
  public int getType() {
    return CIRCLE;
  }
}