# Lesson 10 - Classes and Objects

A method lets you write a behaviour once and reuse it. An **object** goes one step further: it keeps the behaviour together with the data it works on. A video game might have hundreds of enemies, each with its own health, all able to attack and take damage without affecting each other.

This lesson uses a class that has already been written. You'll create objects from it, read and change their data, and call their methods. Writing your own class is the next lesson.

---

## 1. Attributes and Methods

An object has two parts:

- **Attributes** are the data the object holds (an enemy's name, health, and damage). They're variables that belong to the object.
- **Methods** are the behaviours the object can do (attack, take damage). They usually read or change the object's attributes.

A **class** is the blueprint. It says which attributes and methods every object of that kind has. An **object** (also called an **instance**) is one thing built from the class, with its own values for those attributes.

You've already used objects. In Python, `my_list.append(5)` called the `append` method on one list object. In Java, `name.length()` asks one `String` object for its length.

---

## 2. Reading a UML Class Diagram

You don't need to see a class's code to use it. A **UML class diagram** lists everything you need:

```
┌────────────────────────────────────┐
│ Enemy                              │
├────────────────────────────────────┤
│ + name : String                    │
│ + health : int                     │
│ + damage : int                     │
├────────────────────────────────────┤           
│ + Enemy(name : String,             │
│         health : int,              │
│         damage : int)              |
| + attack() : int                   │
│ + takeDamage(amount : int) : void  │
└────────────────────────────────────┘
```

| Part | Example | Meaning |
|---|---|---|
| Top box | `Enemy` | The class name, starting with a capital letter |
| Middle box | `+ health : int` | An attribute: name, then type |
| Bottom box | `+ attack() : int` | A method: name, parameters in brackets, then the type it returns |
| Same name as the class | `+ Enemy(...)` | The **constructor**, which creates new objects. No return type. |
| `+` | | Public: code outside the class can use it |

The first method `Enemy(name, health, damage)` is called the *constructor*.  It normally isn't listed in the methods, but we're listing it today so we know how to create an object.

---

## 3. Creating Objects

Create an object with `new` and the constructor:

```java
Enemy goblin = new Enemy("Goblin", 30, 4);
Enemy dragon = new Enemy("Dragon", 500, 60);
```

- The variable's type is the class name: `Enemy`.
- The arguments must match the attributes in the *constructor*, **in the same order and with the same types**: a `String` (name), then two `int`s (first health then damage). `new Enemy(30, "Goblin", 4)` doesn't compile.


---

## 4. Accessing Attributes

The **dot operator** reaches inside an object. `goblin.health` means "the `health` that belongs to `goblin`":

```java
System.out.println(goblin.name);       // Goblin
System.out.println(goblin.health);     // 30

goblin.name = "Goblin King";           // public attributes can be changed
goblin.damage = goblin.damage * 2;     // 8
```

Attributes have **no brackets**. Methods always do. That's the difference between `scores.length` (an array's length attribute) and `name.length()` (a `String` method).

Nothing stops other code from writing `goblin.health = -9999`. We will discuss in a later lesson about how we can protect our attributes..

---

## 5. Calling Methods

Call a method with the dot operator too, and pass any arguments the UML lists:

```java
Enemy orc = new Enemy("Orc", 80, 12);

orc.takeDamage(25);          // void: changes the orc, returns nothing
System.out.println(orc.health);       // 55

int hit = orc.attack();      // int: returns a value
System.out.println(hit);              // 12
```

The return type in the UML tells you what to do with the call:

- **`void`**: call it on its own line. It does something to the object.
- **Any other type**: store the result in a variable or use it in an expression. `orc.attack();` on its own line compiles, but the returned value is thrown away.

Methods can protect the object. `takeDamage` stops health at 0, so `orc.takeDamage(500)` leaves the orc at 0, not -445. Ask the object for its new state (`orc.health`) instead of working it out yourself.

---

## 6. Each Object Has Its Own State

Two objects from the same class are completely separate:

```java
Enemy left = new Enemy("Goblin", 30, 4);
Enemy right = new Enemy("Goblin", 30, 4);

left.takeDamage(10);
System.out.println(left.health);       // 20
System.out.println(right.health);      // 30
```

Every `new` creates another object with its own copy of every attribute. That's what lets a game have a hundred goblins that each take damage on their own.

---

## 7. Variables Hold References

A variable of type `Enemy` doesn't hold the enemy itself. It holds a **reference**: the location of the object in memory. Copying the variable copies the reference, not the object:

```java
Enemy a = new Enemy("Slime", 20, 2);
Enemy b = a;              // b points at the SAME slime

b.takeDamage(15);
System.out.println(a.health);          // 5
```

```
a ──┐
    ├──► [ Slime | health 5 | damage 2 ]
b ──┘
```

`a == b` is `true` because both variables point at the same object. Two separate objects with the same values are not `==`. (This is why you compare `String`s with `.equals`.)

A reference variable can also hold `null`, meaning no object at all. Calling a method on `null` throws a `NullPointerException`:

```java
Enemy boss = null;
boss.attack();            // NullPointerException
```

---

## 8. Common Object Bugs

**Calling a method on `null`.** `NullPointerException`. The variable was never given an object with `new`.

**Arguments in the wrong order.** `new Enemy(30, "Goblin", 4)` doesn't compile. Check the constructor in the UML.

**Brackets on an attribute, or none on a method.** `goblin.health()` and `goblin.attack` are both compile errors.

**Ignoring a return value.** `orc.attack();` on its own line runs the method and throws the result away.

**Copying a variable when you wanted a new object.** `Enemy b = a;` gives you one enemy with two names, not two enemies.

**Working out the state yourself.** After `orc.takeDamage(500)`, `80 - 500` is wrong. Read `orc.health`.

---

## Try It Yourself

Run the companion file from the `src` folder:

```bash
javac ObjectsDemo.java
java ObjectsDemo
```

`ObjectsDemo` uses the `Enemy` class in `Enemy.java`. You don't need to open that file. Everything you need is in the UML diagram in section 2.

Then work on the exercise in `PetDaycare.java`.

---

## Course Expectations

### ICS 4U

"I can create objects from an existing class and use their attributes and methods"
is a requirement of the course and will be expected in your programming project.

Specific *Look Fors* will be added to the Programming Test Document once this concept has been covered fully.

You should also start thinking about how this concept can be added to your project.  What sort of "object" makes sense with the data you've collected?

### AP Expectations

The following are expectations of the AP exam and will show up on the final exam:

- An existing class can be used to create objects; attributes are the data stored in variables, and behaviours are what instances can do, defined by methods (Topic 1.7, 1.7.A.1, 1.7.A.2)
- A method can be used by knowing what it does, without knowing how it was written (procedural abstraction, 1.9.A.1)
- A `void` method is not called as part of an expression; a non-void method's return value must be stored or used in an expression to be used (1.9.B.1, 1.9.B.2)
- Arguments must match the parameter list in number, order, and type (1.9.B.3, 1.13.C.3)
- An object is a specific instance of a class; a class is the blueprint for the attributes and behaviours of its objects (1.12.A.1)
- A reference variable holds an object reference (its memory address), or `null` if there is no object (1.12.B.1, 1.13.B.1)
- Constructors have the same name as the class; an object is created with `new` followed by a constructor call, and the right constructor is identified by its signature (1.13.A.1, 1.13.A.2, 1.13.C.1)
- Instance methods are called on objects with the dot operator; calling a method on `null` causes a `NullPointerException` (1.14.A.1, 1.14.A.2)
- A `String` can be created with a literal or the `String` constructor, and `String` methods never change the original object (1.15.A.1, 1.15.A.3)
