import becker.robots.*;
import java.util.*;
 import becker.robots.icons.*;     // CircleIcon
import java.awt.Color;                // Color
/**
 *  This class is the main class of the "SoC World" application. 
 *  "Soc World" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes, Robert Topp
 * @version 16.02.2015
 */

public class Game 
{
    private boolean wantToQuit;
    private boolean finished = false;
    private boolean won = false;
    private Parser parser;
    private City pompey = new City(); 
    private Player thePlayer = new Player(pompey, 1, 2, Direction.EAST, 10);
    private Scanner readerName;
    private ArrayList<Room> rooms;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
      createRooms();
      parser = new Parser();
      
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room langstone, library,office,main;
        rooms = new ArrayList<Room>();
        // create the rooms
      langstone = new Room(pompey, 0,0,3,4, "Langstone Campus",-1,2,3,-1);
      library = new Room(pompey, 3,4,2,2, "Library",1,2,1,0);
      office = new Room(pompey, 5,0,3,2, "Office",2,-1,-1,-1);
      langstone.addTeleport();
      langstone.setCloak();
      main = new Room(pompey, 7,7,4,4, "Main Building");
      // set exits
      langstone.setExit("east", library);
      langstone.setExit("south", office);
      library.setExit("west", langstone);
      thePlayer.setRoom(langstone);
      rooms.add(langstone);
      rooms.add(library);
      rooms.add(office);
      rooms.add(main);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        if(! won){
        System.out.println("Thank you for playing.  Good bye.");
        }
        else{
          
          System.out.println("You won. As a gift we are giving you your fee back!");
        }
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("What's your name student?");
        Scanner sc = new Scanner(System.in);
        thePlayer.setName(sc.next());
        System.out.println();
        System.out.println("Voldemort took over The university of Portsmouth with his Gang of dementors!");
        System.out.println("You, " + thePlayer.getName() + "have to find the magical powder passing trough  the demetors.");
        System.out.println("Type 'help' if you need help. However you are the one to save our uni!!");
        System.out.println();
        System.out.println(thePlayer.getRoom().getLongDescription());
        System.out.println(" You are facing " + thePlayer.getDirection());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case MOVE:
                move();
                break;
                
            case TURN:
                turn(command);
                break;
           case EAT:
                eat();
                break;
          case TELEPORT:
               teleport();
               break;
          case WEAR:
               wear();
               break;
          case SPELL:
               spell();
               break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void move() 
    {
      if(thePlayer.isBesideThing(new anEnemyIndicate()) &&  thePlayer.countThingsInBackpack(new aCloakIndicate()) == 0){
         System.out.println("You have to dispossess the dementor first! ");
      }
      else{
        thePlayer.movePlayer();  
        thePlayer.setLabel(thePlayer.getName() + "(" + thePlayer.getHealth() + ")" );
        // Try to leave current room.
        
        for(Room room : rooms){
          if (room.isIn(thePlayer.getIntersection())) 
          {
            thePlayer.setRoom(room);
            System.out.println(room.getShortDescription());
            System.out.println("You're at street " + thePlayer.getStreet() + ", avenue " + thePlayer.getAvenue());
            System.out.println("Your current health is " + thePlayer.getHealth());
          }
          
        }
      }

    }
      /** 
     * "Spell" was entered. attacking the enemy on the same intersection
     */  
    public void spell(){
      if(thePlayer.isBesideThing(new anEnemyIndicate()) &&  thePlayer.countThingsInBackpack(new aCloakIndicate()) == 0){
        Robot t = thePlayer.getIntersection().examineRobots(new anEnemyIndicate()).next();
        if(t.frontIsClear()){
        t.move();
        }
        else{
          t.turnLeft();
          t.turnLeft();
          t.move();
          t.move();
        }
        System.out.println("You scared him!");
        System.out.println("Your current health is "+ thePlayer.getHealth());
      }
      if(thePlayer.isBesideThing(new aBossIndicate())){
        Robot t2 = thePlayer.getIntersection().examineRobots(new aBossIndicate()).next();
        if(t2.frontIsClear()){
          t2.move();
        }
        else{
           Color onColor = new Color(255, 255, 200,0);
           CircleIcon onIcon = new CircleIcon(onColor);
           t2.setIcon(onIcon);
           System.out.println("You pushed voldemort off the balcony with your spell! He's dead now!");
           System.out.println("You won!!");
           won = true;
           finished = true;
           wantToQuit = true;
        }
           
      }
      else{
        System.out.println("Find another enemy!");
      }
    }
    /** 
     * "Turn" was entered. Check the rest of the command to see
     * whether we turn left or right.
     * @param command the second word of the command
     */
    private void turn(Command command)
    {
         if(!command.hasSecondWord()) 
         {
            // if there is no second word, we don't know which way to turn...
            System.out.println("turn which way?");
            return;
         }

        String direction = command.getSecondWord();
        if(direction.equals("left")) 
        { 
          thePlayer.turnLeftFacing();
          
        }
        else if(direction.equals("right")) 
        { 
          thePlayer.turnRightFacing();
        }
        else if(direction.equals("around")){
        thePlayer.turnAroundFacing();
        }
        else 
        { System.out.println("You can not turn that way");}
        System.out.println("You are currently facing " + thePlayer.getDirection());
        System.out.println("Your current health is "+ thePlayer.getHealth());
        thePlayer.setLabel(thePlayer.getName() + "(" + thePlayer.getHealth() + ")" );
        
    }
    /** 
     * "Eat" was entered. Eat the apple on the intersection
     * 
     */
    private void eat(){
       if(thePlayer.isBesideThing(new CandyIndicate())) {
        thePlayer.pickApple();
        System.out.println("Ymmy! You ate an frog candy. Your health is " + thePlayer.getHealth());
      }
      else{
         System.out.println("There is no candy, buddy. Buy yourself some glasses!");
      }
    }
    /** 
     * "teleport" was entered. Teleoport the player to the boss room
     * 
     */  
    public void teleport(){
      
        for(Room room : rooms){
            if(thePlayer.isBesideThing(new anPotionIndicate())) {
              thePlayer.pickThing();
              Color onColor = new Color(255, 255, 200,0);
              CircleIcon onIcon = new CircleIcon(onColor);
              thePlayer.setIcon(onIcon);
              thePlayer = new Player(pompey, 8, 8, Direction.EAST, 20);
             thePlayer.setRoom(room);
             System.out.println("Your successfully magiported. And refreshed your health " + thePlayer.getHealth());
             System.out.println(room.getShortDescription());
            System.out.println("You're at street " + thePlayer.getStreet() + ", avenue " + thePlayer.getAvenue());
            System.out.println("Your current health is " + thePlayer.getHealth());
            }
        }
        if(!thePlayer.isBesideThing(new anPotionIndicate())) {
          System.out.println("No travel powder here!");
        }

    }
     /** 
     * "wear" was entered. Wear a cloak protecting you from demenentors.
     * 
     */ 
    public void wear(){
      if(thePlayer.isBesideThing(new aCloakIndicate())) {
        thePlayer.pickThing();
        System.out.println("You are now imvisible");
      }
      else{
        System.out.println("No cloaks here!");
      }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        }

        {
            return true;  // signal that we want to quit
        }
    }
}
