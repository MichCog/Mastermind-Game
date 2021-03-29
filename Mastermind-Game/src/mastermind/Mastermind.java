package mastermind;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    public int[] randomizedNumbers;
    public int[] chosenNumbers;
    int n;
    int m;
    int c;

    public Mastermind(int columns, int rangeOfNumbers, int counter){
        this.n = columns;
        this.m = rangeOfNumbers;
        this.c = counter;
    }

    /*
    metoda toString generująca tekst powitalny
    */
    public String toString(){

        return "MASTERMIND"
                + "\nWelcome to the Mastermind! In this game you have to guess the correct positions of " + n + " numbers ranged from 1 to " + m + "."
                + "\nYou have "+ c + " chances to guess the correct number. The number of the remaining chances will be displayed for you after every wrong answer."
                + "\nGood luck and have fun!"
                + "\n======================================="
                + "\nTake a guess and choose the numbers you think will be correct!";
    }

    /*
    metoda sprawdzająca wejściowe parametry podawane w konstruktorze
    */
    public void checkStartParameters(){
        if(n<=2 || m<=3){
            throw new RuntimeException();
        }
        else{
            System.out.println(toString());
        }
    }

    /*
    metoda generująca tablicę z losowymi wartościami w zakresie do n
     */
    public void randomizeNumbers(int columns, int rangeOfNumbers) {
        randomizedNumbers = new int[columns];
        Random random = new Random();
        for (int i = 0; i < columns; i++) {
            randomizedNumbers[i] = random.nextInt(rangeOfNumbers);
            randomizedNumbers[i] += 1;
            //System.out.print(randomizedNumbers[i]);
        }
        //System.out.println("");

    }

    /*
    metoda sprawdzająca, czy elementy tablic są poprawne
    */
    public void getNumbers(int columns, int rangeOfNumbers) {
        System.out.println("");
        chosenNumbers = new int[columns];
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        if(string.length() == columns) {
            for (int i = 0; i < columns; i++) {
                if (Character.isDigit(string.charAt(i))) {
                    chosenNumbers[i] = Integer.parseInt(String.valueOf(string.charAt(i)));
                    if (chosenNumbers[i] > rangeOfNumbers) {
                        System.out.println("At least one entered number was out of range. Try again.");
                        getNumbers(columns, rangeOfNumbers);
                    }
                } else {
                    System.out.println("At least one entered character is not a number. Try again.");
                    getNumbers(columns, rangeOfNumbers);
                }
            }
        }
        else{
            System.out.println("The format of your answer was not correct. Try again.");
            getNumbers(columns, rangeOfNumbers);
        }
    }

    /*
    metoda porównująca wartości podane z wartościami prawidłowymi
    */
    public void checkNumbers(int columns) {
        int full = 0;
        int half = 0;
        int none;
        int[] randomizedNumbersCopy = new int[columns];
        if (columns >= 0) System.arraycopy(randomizedNumbers, 0, randomizedNumbersCopy, 0, columns);

        for (int i = 0; i < columns; i++) {
            if (randomizedNumbersCopy[i] == chosenNumbers[i]) {
                full++;
                chosenNumbers[i] = 0;
                randomizedNumbersCopy[i] = 0;

                for(int l = 0; l < columns;l++){
                    if(randomizedNumbersCopy[i] == chosenNumbers[l]){
                        randomizedNumbersCopy[l] = 0;
                    }
                }

                if (full == columns) {
                    System.out.println("Congratulations! You've won!");
                    System.exit(0);
                }
            }
        }
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < columns; j++) {
                if (chosenNumbers[i] != 0 && chosenNumbers[i] == randomizedNumbers[j]) {
                    half++;
                    chosenNumbers[i] = 0;
                    randomizedNumbersCopy[j] = 0;
                }
            }
        }
        none = (columns - half) - full;
            System.out.println("Right numbers in the right places: " + full);
            System.out.println("Right numbers in the wrong places: " + half);
            System.out.println("Wrong numbers in the wrong places: " + none);
    }

    /*
    metoda wywoływana w momencie, gdy podany ciąg liczb nie był prawidłowy
    metoda wyświetla aktualną liczbę prób i w przypadku porażki wyświetla prawidłową odpowiedź
    */
    public void getAndCheckNumbers(int columns) {
        do{
            getNumbers(n,m);
            checkNumbers(n);
            c--;
            System.out.println("Chances left: " + c);
            System.out.println("=======================================");
        }while(c !=0);

        System.out.print("You've lost! The answer was: ");

        for (int i = 0; i < columns; i++) {
            System.out.print(randomizedNumbers[i]);
        }
    }

}
