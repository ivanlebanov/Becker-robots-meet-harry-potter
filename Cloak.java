import becker.robots.*;
import javax.swing.ImageIcon;

public class Cloak extends Thing{
/** The candy constructor
*  @param theCity The city where the apple will be created.
*  @param street The apple's initial street.
*  @param avenue The apple's initial avenue.
*  Source of energy for the player
*  @author Ivan Lebanov */
public Cloak(City aCity, int aStreet, int anAvenue) 
{ 
  super(aCity, aStreet, anAvenue);

  this.setIcon(new CloakIcon());
}

}