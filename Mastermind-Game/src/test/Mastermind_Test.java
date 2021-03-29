package test;
import mastermind.Mastermind;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class Mastermind_Test {

    @Test
    public void testRandomizedNumbersSize(){
        int n = 4;
        int m = 6;
        int c = 9;
        Mastermind master = new Mastermind(n,m,c);
        master.randomizeNumbers(n,m);
        assertEquals( 4, master.randomizedNumbers.length);
    }

    @Test
    public void testRandomizedNumbersContents(){

        int n = 4;
        int m = 6;
        int c = 9;
        Mastermind master = new Mastermind(n,m,c);

        master.randomizeNumbers(n,m);
        for(int i = 0; i <n; i++) {
            assertTrue(master.randomizedNumbers[i] <= m && master.randomizedNumbers[i] >= 1);
        }
    }

    @Test
    public void testIsMasterAnInstanceOfMastermind(){
        int n = 4;
        int m = 6;
        int c = 9;
        Mastermind master = new Mastermind(n,m,c);
        assertThat(master, instanceOf(Mastermind.class));

    }

    @Test(expected = AssertionError.class)
    public void testAreTwoInstancesOfMastermindEqual(){
        Mastermind m = new Mastermind(4,6, 9);
        Mastermind mm = new Mastermind(4,6, 9);
        assertThat(m, is(equalTo(mm)));
    }

    @Test
    public void testIsAnInstanceOfMastermindEqualToItself(){
        Mastermind m = new Mastermind(4,6, 9);
        assertThat(m, equalTo(m));
    }

    @Test (expected = RuntimeException.class)
    public void testCheckStartParameters(){
        int n = 1;
        int m = 1;
        int c = 9;
        Mastermind master = new Mastermind(n,m,c);
        master.checkStartParameters();
    }

    @Test
    public void testToString(){
        int n = 4;
        int m = 6;
        int c = 9;
        Mastermind master = new Mastermind(n,m,c);
        assertThat(master, hasToString(equalTo("MASTERMIND"
                + "\nWelcome to the Mastermind! In this game you have to guess the correct positions of " + n + " numbers ranged from 1 to " + m + "."
                + "\nYou have "+ c + " chances to guess the correct number. The number of the remaining chances will be displayed for you after every wrong answer."
                + "\nGood luck and have fun!"
                + "\n======================================="
                + "\nTake a guess and choose the numbers you think will be correct!")));
    }
}
