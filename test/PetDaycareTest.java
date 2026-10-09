/*
 * PetDaycareTest.java -- unit tests for the Lesson 10 exercise
 * ICS 4U0 - Lesson 10 Exercise: Pet Daycare
 *
 * These tests run PetDaycare.main() exactly as it will be graded: they type
 * the six input lines on System.in and check the lines it prints to
 * System.out. You don't need to change this file -- just run the tests (see
 * README.md) and fix PetDaycare.java until they all pass.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetDaycareTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private Locale originalLocale;

    @BeforeEach
    public void redirectIoAndPinLocale() {
        originalIn = System.in;
        originalOut = System.out;
        originalLocale = Locale.getDefault();
        // printf follows the default locale -- this exercise doesn't print
        // decimals, but every test pins the locale before calling main() so
        // the harness behaves the same on every machine.
        Locale.setDefault(Locale.CANADA);
    }

    @AfterEach
    public void restoreIoAndLocale() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        Locale.setDefault(originalLocale);
    }

    /**
     * Feeds {@code input} to PetDaycare.main() on System.in and returns
     * everything it printed to System.out.
     */
    private String run(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));

        PetDaycare.main(new String[0]);

        return captured.toString(StandardCharsets.UTF_8);
    }

    /**
     * Compares output line by line, trimming trailing whitespace on each
     * line and ignoring one blank line at the very end (println's final
     * newline). Everything else -- including every space, comma, and colon -- is
     * compared exactly.
     */
    private void assertOutputEquals(String expected, String actual) {
        String[] expectedLines = expected.stripTrailing().split("\n", -1);
        String[] actualLines = actual.stripTrailing().split("\n", -1);

        assertEquals(expectedLines.length, actualLines.length,
                "Expected " + expectedLines.length + " line(s) of output, got " + actualLines.length
                        + ".\n--- expected ---\n" + expected + "--- actual ---\n" + actual);

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i].stripTrailing(), actualLines[i].stripTrailing(),
                    "Line " + (i + 1) + " didn't match.\n--- expected ---\n" + expected
                            + "--- actual ---\n" + actual);
        }
    }

    @Test
    public void exampleFromReadme() {
        String input = "Biscuit\ndog\nMochi\ncat\n30\n2\n";
        String expected = "Biscuit the dog: hunger 20, happiness 50, mood okay\n"
                + "Mochi the cat: hunger 70, happiness 90, mood happy\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void eachPetKeepsItsOwnState() {
        // Pet second = first; (or reusing one object for both pets) makes
        // both lines describe the same pet. Each pet needs its own new Pet(...).
        String input = "Rex\ndog\nNibbles\nhamster\n10\n1\n";
        String expected = "Rex the dog: hunger 40, happiness 50, mood okay\n"
                + "Nibbles the hamster: hunger 60, happiness 70, mood happy\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void feedAndPlayGoToTheRightPet() {
        // Only the FIRST pet is fed and only the SECOND pet plays.
        String input = "Pickles\nturtle\nZiggy\nparrot\n45\n1\n";
        String expected = "Pickles the turtle: hunger 5, happiness 50, mood okay\n"
                + "Ziggy the parrot: hunger 60, happiness 70, mood happy\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void hungerIsReadFromTheObjectAfterFeeding() {
        // feed() stops hunger at 0. Working out 50 - 80 yourself prints -30;
        // read first.hunger from the object instead.
        String input = "Tank\nfish\nBubbles\nfish\n80\n0\n";
        String expected = "Tank the fish: hunger 0, happiness 50, mood okay\n"
                + "Bubbles the fish: hunger 50, happiness 50, mood okay\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void playsExactlyTheRequestedNumberOfTimes() {
        // A play count of 0 means play() is never called. A loop that uses
        // <= instead of < plays once too often.
        String input = "Luna\ncat\nSprout\nrabbit\n0\n0\n";
        String expected = "Luna the cat: hunger 50, happiness 50, mood okay\n"
                + "Sprout the rabbit: hunger 50, happiness 50, mood okay\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void moodIsAskedForAfterPlaying() {
        // After three plays the second pet is very happy but also hungry, and
        // getMood() puts hunger first. Ask the object for its mood AFTER the
        // play loop, instead of working it out yourself or asking too early.
        String input = "Bean\ndog\nWaffles\ndog\n20\n3\n";
        String expected = "Bean the dog: hunger 30, happiness 50, mood okay\n"
                + "Waffles the dog: hunger 80, happiness 100, mood hungry\n";
        assertOutputEquals(expected, run(input));
    }
}
