/* Filename: TriangleJewel.java
 * Creates a Triangle Jewel
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.awt.*;

public class TriangleJewel extends Jewels {
  
  public TriangleJewel(int gridX, int gridY) {
    super(gridX, gridY, Color.pink);
  }
  
  protected void drawShape(Graphics g, int drawX, int drawY) {
    drawTriangle(g, drawX, drawY);
  }
  
  private void drawTriangle(Graphics g, int drawX, int drawY) {
    int numPoints = 3;
    int[] xs = new int[numPoints];
    int[] ys = new int[numPoints];
    xs[0] = drawX + (PIECE_SIZE/2);
    ys[0] = drawY + (PIECE_BUFFER/2);
    xs[1] = drawX + (PIECE_SIZE - (PIECE_BUFFER/2));
    ys[1] = drawY + (PIECE_SIZE - (PIECE_BUFFER/2));
    xs[2] = drawX + (PIECE_BUFFER/2);
    ys[2] = drawY + (PIECE_SIZE - (PIECE_BUFFER/2));
    g.fillPolygon(xs, ys, numPoints);
  }
  
  public int getType() {
    return TRIANGLE;
  }
}