package test;
import mastermind.Mastermind_2;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;


public class Mastermind_2_Test {

    @Test (expected = RuntimeException.class)
    public void testCheckParameters(){
        int n = 2;
        int m = 2;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testArrayIfNotANumber(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arraytest = {1,'F', Integer.parseInt("G"),4};
        m2.checkArrays(arraytest);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testArrayIfOutOfRange(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arraytest = {1,9,3,4};
        m2.checkArrays(arraytest);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testArrayIfTooManyElements(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arraytest = {1,2,3,4,5,4};
        m2.checkArrays(arraytest);
    }

    @Test
    public void testCompareArraysIfTrue(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arraytest = {1,3,3,5};
        int[] arraytest2 = {1,3,3,5};
        assertEquals(true, m2.checkNumbers(arraytest, arraytest2));
    }

    @Test
    public void testCheckArraysIfReturnsTheSameArray(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arrayAnswer = {1,3,3,5};
        assertEquals(arrayAnswer, m2.checkArrays(arrayAnswer));
    }

    @Test
    public void testCompareArraysIfFalse(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arrayAnswer = {1,3,3,5};
        int[] arrayGuess = {6,5,3,5};
        assertEquals(false, m2.checkNumbers(arrayAnswer, arrayGuess));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDoesCheckArraysThrowExceptionWhenUsedinCheckNumbersMethod(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arrayAnswer = {1,7,3,5};
        int[] arrayGuess = {7,5,3,5};
        assertEquals(false, m2.checkNumbers(m2.checkArrays(arrayAnswer), m2.checkArrays(arrayGuess)));
    }

    @Test
    public void testCompareArrayGuessWithRandomizedOne(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arrayAnswer = m2.randomizeNumbers(n);
        int[] arrayGuess = {1,3,3,5};
        assertEquals(false, m2.checkNumbers(arrayAnswer, arrayGuess));
    }
    @Test
    public void testRandomizedNumbersSize(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        m2.randomizeNumbers(n);
        assertEquals( 4, m2.randomizedNumbers.length);
    }

    //=====================================================================================

    @Test
    public void testRandomizedNumbersListForSize(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] random = m2.randomizeNumbers(n);
        List<Integer> randomList = Arrays.stream(random)
                .boxed()
                .collect(Collectors.toList());

        assertThat(randomList,hasSize(n));

    }
    @Test
    public void testRandomizedNumbersListForItemsGreaterThan0(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] random = m2.randomizeNumbers(n);
        List<Integer> randomList = Arrays.stream(random)
                .boxed()
                .collect(Collectors.toList());

        assertThat(randomList, hasItem(greaterThan(0)));
    }

    @Test
    public void testRandomizedNumbersListForItemsLessThan7(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] random = m2.randomizeNumbers(n);
        List<Integer> randomList = Arrays.stream(random)
                .boxed()
                .collect(Collectors.toList());

        assertThat(randomList, hasItem(lessThan(7)));

    }


    @Test
    public void testArraysToListsIfEqual(){
        int n = 4;
        int m = 6;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        m2.checkStartParameters();
        int[] arrayAnswer = {1,3,3,5};
        int[] arrayGuess = {1,3,3,5};
        List<Integer> listAnswer = Arrays.stream(arrayAnswer)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> listGuess = Arrays.stream(arrayGuess)
                .boxed()
                .collect(Collectors.toList());

        assertThat(listGuess, equalTo(listAnswer));

    }
    @Test

    public void testToString(){
        int n = 4;
        int m = 6;
        int c = 9;
        Mastermind_2 m2 = new Mastermind_2(n,m);
        assertThat(m2.toString(c), hasToString(equalTo("MASTERMIND"
                + "\nWelcome to the Mastermind! In this game you have to guess the correct positions of " + n + " numbers ranged from 1 to " + m + "."
                + "\nYou have "+ c + " chances to guess the correct number. The number of the remaining chances will be displayed for you after every wrong answer."
                + "\nGood luck and have fun!"
                + "\n======================================="
                + "\nTake a guess and choose the numbers you think will be correct!")));
    }
}
