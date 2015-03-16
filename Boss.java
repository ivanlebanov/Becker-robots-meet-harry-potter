import becker.robots.*;

/**
 * Class for Lord Voldemort. Oops don't say his name.
 */
public class Boss extends RobotSE {
  public  Boss(City theCity, int street, int avenue, Direction dir) {
  super(theCity, avenue, street, dir);
  this.setIcon(new BossIcon());
 this.turnRight();
  }
}
