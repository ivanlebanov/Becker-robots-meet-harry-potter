import becker.robots.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/** The player for the game
 *  
 *  @author Ivan Lebanov */

public class Room 
{
  private int width;
  private int height;
  private String description;
  private ArrayList<Wall> walls = new ArrayList<Wall>();
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Boss> bosses = new ArrayList<Boss>();
  private HashMap<String, Room> exits; 
  private City pompey;
  private int theStreet;
  private int theAve;
  private int northWall;
  private int southWall;
  private int eastWall;
  private int westWall;
   /** Construct a new Room.
    *  @param aCity The city where the Room will be created.
    *  @param str The Room's initial top left street.
    *  @param ave The Room's initial top left avenue.
    *  @param dir The Room's initial direction, one of {Direction.NORTH, SOUTH, EAST, WEST}. */
   public Room(City city, int street, int avenue, int aWidth, int aHeight, String description, int aNnorthWall,
               int aSouthWall, int anEastWall, int aWestWall) 
   { 
     northWall = aNnorthWall;
     southWall = aSouthWall;
     eastWall = anEastWall;
     westWall = aWestWall; 
     width = aWidth;
     height = aHeight;
     this.description = description;
     pompey = city;
     theStreet = street;
     theAve = avenue;
     exits = new HashMap<String, Room>();
     createWalls();
     setCandy();
   }
   public Room(City city, int street, int avenue, int aWidth, int aHeight, String description){
     northWall = -1;
     southWall = -1;
     eastWall = -1;
     westWall = -1; 
     width = aWidth;
     height = aHeight;
     this.description = description;
     pompey = city;
     theStreet = street;
     theAve = avenue;
     exits = new HashMap<String, Room>();
     createWalls();
     addBoss();
   }
     /** 
   * Create walls and dementors of every exit
   */
   public void createWall( int street, int avenue, int aWidth, int aHeight, Direction d, int wallPosition){  
     if(d == Direction.WEST || d == Direction.EAST){
       for(int i = 0; i <= aHeight; i = i + 1){
         if(i != wallPosition){
           walls.add(new Wall( pompey, street + i , avenue , d)); 
         }
         else{
           enemies.add(new Enemy(pompey,street + i, avenue, d));
         }
       }
     }
     else{
       for(int i = 0; i <= aWidth; i = i + 1){
         if(i != wallPosition){
         walls.add(new Wall( pompey, street, avenue + i, d)); 
         }
         else{
         enemies.add(new Enemy(pompey,street, avenue + i, d));

         }
       }     
     }
   }
   public void createWalls(){
     createWall( theStreet, theAve , width, height, Direction.WEST, westWall);
     createWall( theStreet, theAve + width, width, height, Direction.EAST, eastWall);
     createWall( theStreet + height, theAve , width, height, Direction.SOUTH, southWall);
     createWall( theStreet, theAve , width, height, Direction.NORTH, northWall );
   } 

     /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
       /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are at " + description + ".\n" + getExitString();
    }
     /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) 
        {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    
   
    public boolean isIn(Intersection here)
    {  
      boolean isin = false;
      
      if(here.getStreet() >= theStreet && here.getAvenue() >= theAve 
           && here.getStreet() <= theStreet + height && here.getAvenue() <= theAve + width){
        isin = true;
      }

      return isin;
    }
    public void setCandy(){
      Candy t1 = new Candy(pompey, theStreet + height/2, theAve + width/2);
    }
    public void setCloak(){
      Cloak t2 = new Cloak(pompey, theStreet + 1, theAve + 1);
    }
    public void addBoss(){
      bosses.add(new Boss( pompey, theAve + height/2, theStreet + width/2, Direction.WEST));
    }
    public void addTeleport(){
      Potion t1 = new Potion(pompey, theStreet + height/2 + 1, theAve + width/2);
    }
} 