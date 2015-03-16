import becker.robots.*;

import becker.robots.icons.BrokenIcon;
import becker.robots.icons.Icon;
import becker.robots.icons.RobotIcon;
import becker.util.IObserver;
import becker.util.Utilities;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
/**
 * Auto Generated Java Class.
 */
public class Enemy extends RobotSE {
  private int power;
  private int health;
  public Enemy(City theCity, int avenue, int street, Direction aDirection){
    super(theCity, avenue, street, aDirection);
    this.setIcon(new EnemyIcon());
    health = 20;
  }
  public void updateHealth(){
  
      health = health - 10;
   
    if (health <= 0){
      breakRobot("You lost the game. Next time eat your apples.");
    }
  }
   // Get enemy's health
   public int getHealth(){
     return health;
   }
   public void kill(){
   this.breakRobot("Evala.");
   }
}