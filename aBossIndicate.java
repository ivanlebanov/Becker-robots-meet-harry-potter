import becker.robots.IPredicate;
import becker.robots.*;
public class aBossIndicate implements IPredicate
  {  //return true if the Sim passed is a Boss, false otherwise
     public boolean isOK(Sim s)
     {  return s instanceof Boss;
     }
  }