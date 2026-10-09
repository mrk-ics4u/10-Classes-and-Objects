# ICS 4U0 — Lesson 10: Classes and Objects

## Exercise

You're writing the check-out report for a pet daycare. Two pets spend the day there: one gets fed, and the other gets played with. At the end of the day, the report shows how each pet is doing.

The `Pet` class is already written, in `src/Pet.java`. **Don't open it or change it.** Use the UML diagram and the method table below, the same way you'd use any class someone else wrote.

Everything you need is from Lesson 10:

- **creating objects** with `new` and the constructor,
- **calling methods** on an object, including one that returns a value,
- **reading attributes** with the dot operator, and
- keeping **two separate objects** of the same class.

```
┌────────────────────────────────┐
│ Pet                            │
├────────────────────────────────┤
│ + name : String                │
│ + species : String             │
│ + hunger : int                 │
│ + happiness : int              │
├────────────────────────────────┤
│ + Pet(name : String,           │
│       species : String)        │
│ + feed(amount : int) : void    │
│ + play() : void                │
│ + getMood() : String           │
└────────────────────────────────┘
```

| Method | What it does |
|---|---|
| `Pet(name, species)` | Creates a pet. Every new pet starts with `hunger` 50 and `happiness` 50. |
| `feed(amount)` | Lowers `hunger` by `amount`. Hunger never goes below 0. |
| `play()` | Raises `happiness` by 20 and `hunger` by 10. Neither goes above 100. |
| `getMood()` | Returns the pet's mood as one word: `"hungry"`, `"happy"`, or `"okay"`. |

- Read the six input lines below.
- Create one `Pet` for each name and species.
- Feed the **first** pet the food amount, once.
- Call `play()` on the **second** pet as many times as the play count says (0 means never).
- Print one line per pet, first pet first. Get every number from the object's attributes and the mood from `getMood()`. Don't work any of them out yourself.

---

## Input

Six lines from the keyboard:

| Line | Value | Type |
|------|-------|------|
| 1 | First pet's name | text |
| 2 | First pet's species | text |
| 3 | Second pet's name | text |
| 4 | Second pet's species | text |
| 5 | Food amount for the first pet | whole number |
| 6 | Number of times to play with the second pet | whole number |

Example input:

```
Biscuit
dog
Mochi
cat
30
2
```

---

## Output

Exactly two lines:

```
<name> the <species>: hunger <hunger>, happiness <happiness>, mood <mood>
<name> the <species>: hunger <hunger>, happiness <happiness>, mood <mood>
```

For the example input above, your program must print **exactly**:

```
Biscuit the dog: hunger 20, happiness 50, mood okay
Mochi the cat: hunger 70, happiness 90, mood happy
```

Every space, colon, and comma is compared. `Mochi the cat: hunger 70, happiness 90, mood happy` and `Mochi the cat: hunger 70,happiness 90, mood happy` are not the same answer.

---

## Testing

- Test your code yourself first.
- Open the **Testing** panel from the sidebar (flask icon) and click ▶ **Run Tests**.

A green check next to a test means it passed; a red X means it failed and will show you the expected vs. actual output.
