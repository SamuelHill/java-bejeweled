/* Filename: RndmJwls.java
 * Randomly creates different types of Jewels
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.util.*;
import java.awt.*;

public class RndmJwls {
  public static final int DIAMOND = 0;
  public static final int CIRCLE = 1;
  public static final int RHOMBUS = 2;
  public static final int TRIANGLE = 3;
  public static final int OCTAGON = 4;
  public static final int SQUARE = 5;
  public static final int HEXAGON = 6;
  
  public RndmJwls() {}
  
  private int jewelNumber() {
    Random miner = new Random();
    int j = miner.nextInt(7);
    return j;
  }
  
  public Jewels generateJwl(int type, int gridX, int gridY) {
    Jewels result = null;
    if (type == DIAMOND) {
      result = new DiamondJewel(gridX, gridY);
    }
    if (type == CIRCLE) {
      result = new CircleJewel(gridX, gridY);
    }
    if (type == RHOMBUS) {
      result = new RhombusJewel(gridX, gridY);
    }
    if (type == TRIANGLE) {
      result = new TriangleJewel(gridX, gridY);
    }
    if (type == OCTAGON) {
      result = new OctagonJewel(gridX, gridY);
    }
    if (type == SQUARE) {
      result = new SquareJewel(gridX, gridY);
    }
    if (type == HEXAGON) {
      result = new HexagonJewel(gridX, gridY);
    }
    return result;
  }
  
  public Jewels generateRndmJwl(int gridX, int gridY) {
    int rand = jewelNumber();
    Jewels result = generateJwl(rand, gridX, gridY);
    return result;
  }
}