import becker.robots.icons.*;
import java.awt.*;
/**
 * An Enemy icon class.
 */
public class EnemyIcon extends Icon{
  public EnemyIcon(){
  super();
  }
   /**
   * method to change the icon
   * @param g takes a graphic as parameter.
   * */
  protected void paintIcon(Graphics g)
  {
    Image variable = Toolkit.getDefaultToolkit().getImage("images/dementor.png");
    g.drawImage(variable, 25, 25, 50,50, null);
  }
}
