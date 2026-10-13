/*
 * Name:        Lesson 10: Classes and Objects (ObjectsDemo.java)
 * Description: A runnable tour of using objects from an existing class:
 *              creating them with new, reading and changing attributes,
 *              calling methods, and how variables hold references.
 *              Uses the Enemy class in Enemy.java.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-10-06
 */

// The Enemy class is in a separate file, Enemy.java, in the same folder

// Take a look at it if you like, but we will not be creating our own classes until the next lesson

public class ObjectsDemo {

    public static void main(String[] args) {

        

        // // creating Enemy objects
        Enemy dragon = new Enemy("Dragon", 500, 60);
        Enemy goblin = new Enemy("Goblin", 30, 4);

        // accessing attributes with dot notation
        System.out.println("made " + goblin.name + " and " + dragon.name);
        System.out.println("goblin.name   = " + goblin.name);
        System.out.println("goblin.health = " + goblin.health);
        System.out.println("goblin.damage = " + goblin.damage);

        // Nothing stops this (yet), because the attributes are public
        // goblin.health = -9999;
        // System.out.println("goblin.health = " + goblin.health + "   <- should this be allowed?");
        dragon.health -= 100;
        System.out.println("dragon.health = " + dragon.health);

        // calling methods with dot notation
        goblin.takeDamage(25);
        System.out.println("after takeDamage(25): health = " + goblin.health);

        // the method protects the object: health stops at 0
        goblin.takeDamage(500);
        System.out.println("after takeDamage(500): health = " + goblin.health);

        // int method: returns a value you store or use
        int hit = goblin.attack();
        System.out.println("goblin.attack() returned " + hit);
        System.out.println("two attacks: " + (goblin.attack() + goblin.attack()));

        // calling it on its own line throws the returned value away
        goblin.attack();
     
       
      
    }

}
