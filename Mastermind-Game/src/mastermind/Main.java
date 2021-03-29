package mastermind;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int n = 4;
        int m = 6;
        int c = 9;


        //zawartość maina dla klasy Mastermind
        /*
        Mastermind m1 = new Mastermind(n,m,c);
        m1.checkStartParameters();
        m1.randomizeNumbers(n,m);
        m1.getAndCheckNumbers(n);
        */


        //zawartość maina dla klasy Mastermind_2
        Mastermind_2 m2 = new Mastermind_2(n, m);
        m2.checkStartParameters();
        System.out.println(m2.toString(c));
        int[] arrayAnswer = m2.randomizeNumbers(n);
        int[] arrayGuess = new int[n];

        /*for (int i = 0; i < n; i++) {
            System.out.print(m2.randomizedNumbers[i]);
        }*/ //pętla wyświetlająca poprawną odpowiedź

        do {
            System.out.println("");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < n; i++) {
                arrayGuess[i] = scanner.nextInt();
            }
            arrayGuess = m2.checkArrays(arrayGuess);
            if(m2.checkNumbers(arrayAnswer, arrayGuess)){
                System.exit(0);
            }

            c--;
            System.out.println("Chances left: " + c);
            System.out.println("=======================================");
        } while (c != 0);

        System.out.print("You've lost! The answer was: ");

        for (int i = 0; i < n; i++) {
            System.out.print(m2.randomizedNumbers[i]);
        }

    }

}
