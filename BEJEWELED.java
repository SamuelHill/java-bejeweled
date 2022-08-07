/* Filename: BEJEWELED.java
 * Window that contains the game board, the new board button, and a score.
 * Implements MouseListener to detect clicks on the Board and button.
 * Author: Sam Hill
 * Assignment: Bejeweled
 * Date: April 20, 2013
 */

//For MouseListener
import java.awt.event.*;
//For the JComponent stuff
import javax.swing.*;

public class BEJEWELED implements MouseListener {
  //The window that we put everything inside of
  private JFrame window;
  //The panel that we add to the window
  private JPanel topPanel;
  //The board that we play the game on
  private Board board;
  //The button to create a new Board
  private JButton newBoardButton;
  //The label that has the score
  public JLabel scoreKeeper;
  
  /*Constructor that creates the window and all of its compontents.
   * inputs: none
   */
  public BEJEWELED() {
    //create the window
    window = new JFrame();
    //create the panel for the window
    topPanel = new JPanel();
    //create the board
    board = new Board();
    
    //add the board to the panel
    topPanel.add(board);
    //set the panel as the content for our window
    window.setContentPane(topPanel);
    //make the board object listen for Mouse Events
    board.addMouseListener(this);
    
    //create a button and name it New Board
    newBoardButton = new JButton("New Board");
    //add this button to the bottom of the panel
    topPanel.add(newBoardButton);
    //make the button object listen for Mouse Events
    newBoardButton.addMouseListener(this);
    
    //create a label and put Score: 0 in it to begin
    scoreKeeper = new JLabel("Score: " + 0);
    //add this label to the bottom of the panel
    topPanel.add(scoreKeeper);
    
    //sets the size of the window to that of the board with a small border
    //and just below the board with enough room for the button and label
    window.setSize((Jewels.PIECE_SIZE * Board.NUM_COLS) + 20,
                   (Jewels.PIECE_SIZE * Board.NUM_ROWS) + 75);
    //sets the tilte of the window to BEJEWELED
    window.setTitle("BEJEWELED");
    //exits the program when the window is closed
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //makes the window visible
    window.setVisible(true);
  }
  
  /*Takes a mouse event (clicked) and depending on where clicked,
   * either updates the score or creates a new board
   * inputs: MouseEvent e | output: none
   */
  public void mouseClicked(MouseEvent e) {
    //gets the source of where we clicked and sets it equal to an object
    Object source = e.getSource();
    //if the source is the button
    if (source == newBoardButton) {
      //close the last window
      window.dispose();
      //create a new game
      BEJEWELED b = new BEJEWELED();
    }
    //if the source is the board
    if (source == board) {
      //update the score
      scoreKeeper.setText("Score: " + board.score());
    }
  }
  
  /*Only needed because we implement MouseListener,
   * for when the mouse exits the screen.
   * inputs: none | output: none
   */
  public void mouseExited(MouseEvent e) {}
  /*Only needed because we implement MouseListener,
   * for when the mouse enters the screen.
   * inputs: none | output: none
   */
  public void mouseEntered(MouseEvent e) {}
  /*Only needed because we implement MouseListener,
   * for when the mouse is released.
   * inputs: none | output: none
   */
  public void mouseReleased(MouseEvent e) {}
  /*Only needed because we implement MouseListener,
   * for when the mouse is pressed.
   * inputs: none | output: none
   */
  public void mousePressed(MouseEvent e) {}
  
  /*Main method*/
  public static void main(String[] args) {
    //Makes a new game
    BEJEWELED game = new BEJEWELED();
  }
}