/*
 * Name:        Lesson 10: Classes and Objects (Enemy.java)
 * Description: The class used in the lesson demo. You don't need to read
 *              this file yet. Use the UML diagram in the lesson to see its
 *              attributes and methods. Writing a class like this is the next
 *              lesson.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-10-06
 */
public class Enemy {

    // attributes: every Enemy object gets its own copy of these
    public String name;
    public int health;
    public int damage;

    // constructor: runs once, when an Enemy is created with new
    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    // method to return how much damage this enemy's attack does
    public int attack() {
        return damage;
    }

    // method to lower this enemy's health, stopping at 0
    public void takeDamage(int amount) {
        health = health - amount;
        if (health < 0) {
            health = 0;
        }
    }
}
