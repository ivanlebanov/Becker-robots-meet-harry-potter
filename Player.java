import becker.robots.*;

/** The palyer for the game
 *  
 *  @author Ivan Lebanov */

public class Player extends RobotSE
{
  private int health;
  private String name;
  private Room here;
  private Direction facing;
  private int power;
  private City pompey;
  private int theAve;
  private int theStreet;
  private int maxHold; // Maximum # of things this robot can hold.
  private int numHeld = 0; // Number of things currently held by this robot
   /** Construct a new Player robot.
    *  @param theCity The city where the robot will be created.
    *  @param street The robot's initial street.
    *  @param avenue The robot's initial avenue.
    * @param aDirection The robot's initial direction, one of {Direction.NORTH, SOUTH, EAST, WEST}.
    
    */
public Player(City theCity, int avenue, int street, Direction aDirection, int maxCanHold)
 { 
    super(theCity, avenue, street, aDirection);
    name = null;
    here = null;
    facing = aDirection;
    health = 100;
    power = 25;
    this.maxHold = maxCanHold;
     theStreet = street;
     theAve = avenue;   
    this.setIcon(new PlayerIcon());
  
  }

  /**
   *  return the direction thie player is facing
   * @return direction e.g west, east, south, north
   */
  public Direction getDirection()
  {
    return facing;
  }

    /**
   *   update health
   */
  public void updateHealth(){
  
      health = health - 5;
   
    if (health <= 0){
      breakRobot("You lost the game. Next time eat your apples.");
    }
  }
  /**
   *  return the room the player is currently in
   */
  public Room getRoom()
  {
    return here;
  }
    /**
   *  set the players current location
   */
  
  public void setRoom(Room r)
  {
    here = r;
  }
  
  /**
   *  set the direction the player is facing
   */
  
  public void setDirection(Direction d)
  {
    facing = d;
  }
  /**
   *  make the player turn left and update health
   */  
  public void turnLeftFacing()
  {
    this.turnLeft();
    this.updateHealth();
    if(facing == Direction.NORTH)
    {
      facing = Direction.WEST;
    } 
    else if(facing == Direction.WEST)
    {
      facing = Direction.SOUTH;
    }
    else if(facing == Direction.SOUTH)
    {
      facing = Direction.EAST;
    }
    else
    {
      facing = Direction.NORTH;
    }
  }
  public void turnAroundFacing()
  {
    this.turnAround();
    this.updateHealth();
    if(facing == Direction.NORTH)
    {
      facing = Direction.SOUTH;
    } 
    else if(facing == Direction.WEST)
    {
      facing = Direction.EAST;
    }
    else if(facing == Direction.SOUTH)
    {
      facing = Direction.NORTH;
    }
    else
    {
      facing = Direction.WEST;
    }
  }
  public void movePlayer(){
    this.move();
    this.updateHealth();
  }
  /**
   *  make the player turn right and update health
   */
  public void turnRightFacing()
  {
    this.turnRight();
    this.updateHealth();
    if(facing == Direction.NORTH)
    {
      facing = Direction.EAST;
    } 
    else if(facing == Direction.WEST)
    {
      facing = Direction.NORTH;
    }
    else if(facing == Direction.SOUTH)
    {
      facing = Direction.WEST;   
    }
    else
    {
      facing = Direction.SOUTH;   
    }
  }
  
  // Get player's health
   public int getHealth(){
     return health;
   } 
   //Get player's name
   public String getName(){
     return name;
   } 
   /**
   *  make the player turn right and update health
   * @param newName the new name of the player
   */
  public String setName(String newName){
     name = newName;
     this.setLabel(name);
     return name;
   } 
   /**
   *  make the player pick an apple
   * and update his health
   */
   public void pickApple()
   {
     
     this.pickThing();
     
     health = health + 20;
   }
   /**
    * Attack enemy
    * */
   public void attack(Enemy enemy){

   }
 public void pickThing(){
  if(this.numHeld == this.maxHold){
    this.breakRobot("Tried to pick up too many things.");
 }else
 {
   super.pickThing();
   this.numHeld = this.numHeld + 1;
 }
 }

 /** Put down one thing. */
 public void putThing()
{
 super.putThing();
 this.numHeld = this.numHeld - 1;
 }
 
}
   