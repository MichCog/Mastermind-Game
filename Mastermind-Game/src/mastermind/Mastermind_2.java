package mastermind;

import java.util.Random;

public class Mastermind_2 {
    public int[] randomizedNumbers;
    int n;
    int m;

    public Mastermind_2(int columns, int rangeOfNumbers){
        this.n = columns;
        this.m = rangeOfNumbers;

    }

    /*
    metoda sprawdzająca wejściowe parametry podawane w konstruktorze
    */
    public void checkStartParameters(){
        if(n<=2 || m<=4){
            throw new RuntimeException();
        }
    }


    /*
    metoda generująca tablicę z losowymi wartościami w zakresie do n
     */
    public int[] randomizeNumbers(int n) {
        randomizedNumbers = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            randomizedNumbers[i] = random.nextInt(n);
            randomizedNumbers[i] += 1;
            //System.out.print(randomizedNumbers[i]);
        }
        //System.out.println("");
        return randomizedNumbers;

    }

    /*
    metoda sprawdzająca, czy elementy tablic są poprawne
     */
    public int[] checkArrays(int []arrayGuess) {
        /*Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();*/
        StringBuilder string = new StringBuilder();

        if(arrayGuess.length == n) {
        for(int i = 0;i < n;i++){
            string.append(arrayGuess[i]);
        }
            for (int i = 0; i < n; i++) {
                if (Character.isDigit(string.charAt(i))) {
                    if (arrayGuess[i] > m) {
                        //System.out.println("At least one entered number was out of range. Try again.");
                        //getNumbers(columns, rangeOfNumbers);
                        throw new IllegalArgumentException();
                    }
                }
                else {
                    //System.out.println("At least one entered character is not a number. Try again.");
                    //getNumbers(columns, rangeOfNumbers);
                    throw new IllegalArgumentException();
                }
            }
        }
        else{
            //System.out.println("The format of your answer was not correct. Try again.");
            //getNumbers(columns, rangeOfNumbers);
            throw new IndexOutOfBoundsException();
        }
        return arrayGuess;
    }

    /*
    metoda porównująca wartości podane z wartościami prawidłowymi
    */
    public boolean checkNumbers(int []arrayAnswer, int []arrayGuess) {
        int full = 0;
        int half = 0;
        int none;
        int[] randomizedNumbersCopy = new int[n];
        if (n >= 0) System.arraycopy(arrayAnswer, 0, randomizedNumbersCopy, 0, n);

        for (int i = 0; i < n; i++) {
            if (randomizedNumbersCopy[i] == arrayGuess[i]) {
                full++;
                arrayGuess[i] = 0;
                randomizedNumbersCopy[i] = 0;

                for(int l = 0; l < n;l++){
                    if(randomizedNumbersCopy[i] == arrayGuess[l]){
                        randomizedNumbersCopy[l] = 0;
                    }
                }

                if (full == n) {
                    System.out.println("You've won! Congratulations!");
                    return true;

                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrayGuess[i] != 0 && arrayGuess[i] == randomizedNumbersCopy[j]) {
                    half++;
                    arrayGuess[i] = 0;
                    randomizedNumbersCopy[j] = 0;
                }
            }
        }
        none = (n - half) - full;
        System.out.println("Right numbers in the right places: " + full);
        System.out.println("Right numbers in the wrong places: " + half);
        System.out.println("Wrong numbers in the wrong places: " + none);

        return false;
    }

    /*
    metoda toString generująca tekst powitalny
    */
    public String toString(int c){

        return "MASTERMIND"
                + "\nWelcome to the Mastermind! In this game you have to guess the correct positions of " + n + " numbers ranged from 1 to " + m + "."
                + "\nYou have "+ c + " chances to guess the correct number. The number of the remaining chances will be displayed for you after every wrong answer."
                + "\nGood luck and have fun!"
                + "\n======================================="
                + "\nTake a guess and choose the numbers you think will be correct!";
    }
}
