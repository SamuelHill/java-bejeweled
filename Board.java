/* Filename: Board.java
 * An interactive board for the game. Extends Canvas to create the Board
 * and Implements MouseListener to make the board interactive.
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Board extends java.awt.Canvas implements MouseListener {
  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  private Jewels[][] pieces;
  private static final int NO_CURRENT_PIECES = -1;
  private int x1 = NO_CURRENT_PIECES;
  private int y1 = NO_CURRENT_PIECES;
  private int x2 = NO_CURRENT_PIECES;
  private int y2 = NO_CURRENT_PIECES;
  private Color bgColor;
  private RndmJwls rj;
  public int scoreKeeper = 0;
  
//************************************************************************//
  public Board() {
    setSize(NUM_COLS * Jewels.PIECE_SIZE,
            NUM_ROWS * Jewels.PIECE_SIZE);
    pieces = new Jewels[NUM_COLS][NUM_ROWS];
    rj = new RndmJwls();
    for (int y = 0; y < NUM_ROWS; y++) {
      for (int x = 0; x < NUM_COLS; x++) {
        pieces[x][y] = rj.generateRndmJwl(x, y);
      }
    }
    this.addMouseListener(this);
    bgColor = Color.black;
    
    ArrayList<Jewels> jlist = findJewelsToRemove();
    while (jlist.isEmpty() != true ) {
      removeJewels(jlist);
      jlist = findJewelsToRemove();
    }
  }
  
//************************************************************************//
  public void paint(Graphics g) {
    g.setColor(bgColor);
    g.fillRect(0, 0, NUM_COLS * Jewels.PIECE_SIZE,
               NUM_ROWS * Jewels.PIECE_SIZE);
    for (int y = 0; y < NUM_ROWS; y++) {
      for (int x = 0; x < NUM_COLS; x++) {
        getPiece(x, y).drawPiece(g);
      }
    }
  }
  
  public Jewels getPiece(int x, int y) {
    return pieces[x][y];
  }
  
//************************************************************************//
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    int gridX = x/Jewels.PIECE_SIZE;
    int gridY = y/Jewels.PIECE_SIZE;
    System.out.println("x " + gridX + "y " + gridY);
    selectPiece(gridX, gridY);
  }
  
  public void mouseExited(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  
//************************************************************************//
  public void selectPiece(int x, int y) {
    if (x1 == NO_CURRENT_PIECES) {
      pieces[x][y].select();
      this.x1 = x;
      this.y1 = y;
      this.repaint();
    }
    
    else if (x == x1 && y == y1) {
      pieces[x][y].unselect();
      this.x1 = NO_CURRENT_PIECES;
      this.y1 = NO_CURRENT_PIECES;
      this.repaint();
    }
    
    else if (adjacent(x, y, x1, y1)) {
      pieces[x][y].select();
      this.x2 = x;
      this.y2 = y;
      swapPiece(x1, y1, x2, y2);
      
      ArrayList<Jewels> jlist = findJewelsToRemove();
      
      if (jlist.isEmpty() != true ) {
        while (jlist.isEmpty() != true ) {
          scoreKeeper(jlist);
          removeJewels(jlist);
          jlist = findJewelsToRemove();
        }
        selectReset(x1, y1, x2, y2);
        this.repaint();
      }
      else {
        swapPiece(x1, y1, x2, y2);
        pieces[x1][y1].unselect();
        this.repaint();
      }
    }
    
    else {
      pieces[x1][y1].unselect();
      pieces[x][y].select();
      this.x1 = x;
      this.y1 = y;
      this.repaint();
    }
  }
  
  public boolean adjacent(int x, int y, int x1, int y1) {
    if (x == x1 && (y == (y1 + 1) || y == (y1 - 1))) {
      return true;
    }
    if (y == y1 && (x == (x1 + 1) || x == (x1 - 1))) {
      return true;
    }
    return false;
  }
  
//************************************************************************//
  public void swapPiece(int x, int y, int xx, int yy) {
    int j = getPiece(x, y).getType();
    int jj = getPiece(xx, yy).getType();
    pieces[x][y] = rj.generateJwl(jj, x, y);
    pieces[xx][yy] = rj.generateJwl(j, xx, yy);
  }
  
  public void selectReset(int x, int y, int xx, int yy) {
    this.x1 = NO_CURRENT_PIECES;
    this.y1 = NO_CURRENT_PIECES;
    this.x2 = NO_CURRENT_PIECES;
    this.y2 = NO_CURRENT_PIECES;
  }
  
//************************************************************************//
  public ArrayList<Jewels> findJewelsToRemove() {
    ArrayList<Jewels> jewelsList = new ArrayList<Jewels>();
    for (int y = 0; y < NUM_ROWS; y++) {
      for (int x = 0; x < NUM_COLS; x++) {
        Jewels j = getPiece(x, y);
        if (inRowOfThree(j) || inColOfThree(j)) {
          jewelsList.add(j);
        }
      }
    }
    return jewelsList;
  }
  
  private boolean inRowOfThree(Jewels j) {
    int x = j.getX();
    int y = j.getY();
    int type = j.getType();
    int typeL1 = -1;
    int typeL2 = -1;
    int typeR1 = -1;
    int typeR2 = -1;
    
    if (x - 1 >= 0) {
      typeL1 = pieces[x-1][y].getType();
    }
    if (x - 2 >= 0) {
      typeL2 = pieces[x-2][y].getType();
    }
    if (x + 1 <= 7) {
      typeR1 = pieces[x+1][y].getType();
    }
    if (x + 2 <= 7) {
      typeR2 = pieces[x+2][y].getType();
    }
    
    if (typeL1 == typeL2 && typeL2 == type) {
      return true;
    }
    else if (typeR1 == typeR2 && typeR2 == type){
      return true;
    }
    else if (typeR1 == type && type == typeL1){
      return true;
    }
    return false;
  }
  
  private boolean inColOfThree(Jewels j) {
    int x = j.getX();
    int y = j.getY();
    int type = j.getType();
    int typeD1 = -1;
    int typeD2 = -1;
    int typeU1 = -1;
    int typeU2 = -1;
    
    if (y - 1 >= 0) {
      typeU1 = pieces[x][y-1].getType();
    }
    if (y - 2 >= 0) {
      typeU2 = pieces[x][y-2].getType();
    }
    if (y + 1 <= 7) {
      typeD1 = pieces[x][y+1].getType();
    }
    if (y + 2 <= 7) {
      typeD2 = pieces[x][y+2].getType();
    }
    
    if (typeU1 == typeU2 && typeU2 == type) {
      return true;
    }
    else if (typeD1 == typeD2 && typeD2 == type){
      return true;
    }
    else if (typeD1 == type && type == typeU1){
      return true;
    }
    return false;
  }
  
//************************************************************************//
  private void removeJewels(ArrayList<Jewels> jlist) {
    for (int i = 0; i < jlist.size(); i++) {
      Jewels j = jlist.get(i);
      int x = j.getX();
      int y = j.getY();
      while (y > 0) {
        replaceJewelWithAbove(x, y);
        y--;
      }
      addJewel(x, y);
    }
  }
  
  private void replaceJewelWithAbove(int x, int y) {
    Jewels above =  pieces[x][y-1];
    pieces[x][y] = above;
    above.setY(y);
  }
    
  private void addJewel(int x, int y) {
    pieces[x][y] = rj.generateRndmJwl(x, y);
  }
  
//************************************************************************//
  private void scoreKeeper(ArrayList<Jewels> jlist) {
    scoreKeeper = scoreKeeper + jlist.size();
    System.out.println(scoreKeeper);
  }
  
  public int score() {
    return this.scoreKeeper;
  }
}