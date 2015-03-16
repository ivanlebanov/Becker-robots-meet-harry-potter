import becker.robots.*;
import java.awt.*;
import becker.robots.icons.*;
/** The Speed plus item which speeds up the player */
public class Potion extends Thing{
  
/** The SpeedPlus constructor
*  Item which speeds up he player
*  @param theCity The city where the robot will be created.
*  @param street The apple's initial street.
*  @param avenue The apple's initial avenue.
*  @author Ivan Lebanov */
public Potion(City aCity, int aStreet, int anAvenue) 
{ 
  super(aCity, aStreet, anAvenue);
  this.setIcon(new PotionIcon());
}

}