import becker.robots.IPredicate;
import becker.robots.*;
public class anPotionIndicate implements IPredicate
  {  
     //return true if the Sim passed is a Potion, false otherwise
     public boolean isOK(Sim s)
     {  return s instanceof Potion;
     }
  }