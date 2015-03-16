import becker.robots.icons.*;
import java.awt.*;
/**
 * Cloak icon for item Cloak
 */
public class CloakIcon extends Icon{
 
  public CloakIcon(){
  super();
  }
    /**
   * method to change the icon
   * @param g takes a graphic as parameter.
   * */
  protected void paintIcon(Graphics g)
  {
    Image variable = Toolkit.getDefaultToolkit().getImage("images/cloak.png");
    g.drawImage(variable, 25, 25, 50,50, null);
  }
}