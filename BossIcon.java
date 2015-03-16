import becker.robots.icons.*;
import java.awt.*;
/**
 * The Icon for Boss.
 */
public class BossIcon extends Icon{
  public BossIcon(){
  super();
  }
  /**
   * method to change the icon
   * @param g takes a graphic as parameter.
   * */
  protected void paintIcon(Graphics g)
  {
    Image variable = Toolkit.getDefaultToolkit().getImage("images/voldemort.png");
    g.drawImage(variable, 25, 25, 50,50, null);
  }
}