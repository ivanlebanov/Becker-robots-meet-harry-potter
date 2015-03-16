import becker.robots.icons.*;
import java.awt.*;
/**
 * Potion Icon class for Potion object.
 */
public class PotionIcon extends Icon{
  
  public PotionIcon(){
  super();
  }
   /**
   * method to change the icon
   * @param g takes a graphic as parameter.
   * */
  protected void paintIcon(Graphics g)
  {
    Image variable = Toolkit.getDefaultToolkit().getImage("images/powder.png");
    g.drawImage(variable, 25, 25, 50,50, null);
  }
}