/*
 * Name:        Lesson 10: Classes and Objects (Pet.java)
 * Description: The class the exercise uses. You don't need to read or change
 *              this file. Everything you need is in the UML diagram and the
 *              method table in README.md.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-10-06
 */
public class Pet {

    // attributes: every Pet object gets its own copy of these
    public String name;
    public String species;
    public int hunger;
    public int happiness;

    // constructor: every new pet starts at 50 hunger and 50 happiness
    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
        this.hunger = 50;
        this.happiness = 50;
    }

    // method to lower hunger by amount, stopping at 0
    public void feed(int amount) {
        hunger = hunger - amount;
        if (hunger < 0) {
            hunger = 0;
        }
    }

    // method to raise happiness by 20 and hunger by 10, each stopping at 100
    public void play() {
        happiness = happiness + 20;
        if (happiness > 100) {
            happiness = 100;
        }
        hunger = hunger + 10;
        if (hunger > 100) {
            hunger = 100;
        }
    }

    // method to return a one-word mood based on hunger and happiness
    public String getMood() {
        if (hunger >= 80) {
            return "hungry";
        } else if (happiness >= 70) {
            return "happy";
        } else {
            return "okay";
        }
    }
}
