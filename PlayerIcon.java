import becker.robots.icons.*;
import java.awt.*;
/**
 * Auto Generated Java Class.
 */
public class PlayerIcon extends Icon{
  
  /* ADD YOUR CODE HERE */
  public PlayerIcon(){
  super();
  this.setSize(1.0);
  }
  protected void paintIcon(Graphics g)
  {
    Image variable = Toolkit.getDefaultToolkit().getImage("images/rsz_3player.png");
    g.drawImage(variable, 25, 25, 50,50, null);
    
  }
}