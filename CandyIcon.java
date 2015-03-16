import becker.robots.icons.*;
import java.awt.*;
/**
 * The Icon for a frog candy.
 */
public class CandyIcon extends Icon{ 
  public CandyIcon(){
  super();
  }
  
   /**
   * method to change the icon
   * @param g takes a graphic as parameter.
   * */
  protected void paintIcon(Graphics g)
  {
    Image variable = Toolkit.getDefaultToolkit().getImage("images/candy.png");
    g.drawImage(variable, 25, 25, 50,50, null);
  }
}