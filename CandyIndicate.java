import becker.robots.IPredicate;
import becker.robots.*;
public class CandyIndicate implements IPredicate
  {  
    //return true if the Sim passed is a Candy, false otherwise
     public boolean isOK(Sim s)
     {  return s instanceof Candy;
     }
  }