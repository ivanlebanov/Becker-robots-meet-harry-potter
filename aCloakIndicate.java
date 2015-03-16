import becker.robots.IPredicate;
import becker.robots.*;
public class aCloakIndicate implements IPredicate
  {  
   //return true if the Sim passed is a Cloak, false otherwise
     public boolean isOK(Sim s)
     {  return s instanceof Cloak;
     }
  }