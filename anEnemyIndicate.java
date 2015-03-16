import becker.robots.IPredicate;
import becker.robots.*;
public class anEnemyIndicate implements IPredicate
  {  
    //return true if the Sim passed is a Enemy, false otherwise
     public boolean isOK(Sim s)
     {  return s instanceof Enemy;
     }
  }