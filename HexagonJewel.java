/* Filename: HexagonJewel.java
 * Creates a Hexagon Jewel
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.awt.*;

public class HexagonJewel extends Jewels {
  
  public HexagonJewel(int gridX, int gridY) {
    super(gridX, gridY, Color.gray);
  }
  
  protected void drawShape(Graphics g, int drawX, int drawY) {
    drawHexagon(g, drawX, drawY);
  }
  
  private void drawHexagon(Graphics g, int drawX, int drawY) {
    int numPoints = 6;
    int[] xs = new int[numPoints];
    int[] ys = new int[numPoints];
    xs[0] = drawX + (PIECE_SIZE/3);
    ys[0] = drawY + (PIECE_BUFFER/2);
    xs[1] = drawX + ((PIECE_SIZE/3)*2);
    ys[1] = drawY + (PIECE_BUFFER/2);
    xs[2] = drawX + (PIECE_SIZE - (PIECE_BUFFER/2));
    ys[2] = drawY + (PIECE_SIZE/2);
    xs[3] = drawX + ((PIECE_SIZE/3)*2);
    ys[3] = drawY + (PIECE_SIZE - (PIECE_BUFFER/2));
    xs[4] = drawX + (PIECE_SIZE/3);
    ys[4] = drawY + (PIECE_SIZE - (PIECE_BUFFER/2));
    xs[5] = drawX + (PIECE_BUFFER/2);
    ys[5] = drawY + (PIECE_SIZE/2);
    g.fillPolygon(xs, ys, numPoints);
  }
  
  public int getType() {
    return HEXAGON;
  }
}