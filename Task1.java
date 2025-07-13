import java.util.Scanner;
import java.util.Random;

public class Task1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int maxAttempts = 7;
        int round = 0;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain;
        do {
            int numberToGuess = rand.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;
            round++;

            System.out.println("\n Round " + round + " - Guess the number between 1 and 100!");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess;

                // Validate input
                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    sc.next(); // consume invalid input
                    continue;
                }

                userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    score += (maxAttempts - attempts + 1); // more points for fewer attempts
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println(" Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all attempts! The correct number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = sc.next().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");

        } while (playAgain);

        System.out.println("\nGame Over!");
        System.out.println("Total Rounds Played: " + round);
        System.out.println("Final Score: " + score);
        System.out.println("Thanks for playing!");

        sc.close();
    }
}

OUTPUT:

Welcome to the Number Guessing Game!

Round 1 - Guess the number between 1 and 100!
You have 7 attempts.
Enter your guess: 30
Too high! Try a lower number.
Enter your guess: 20
Too high! Try a lower number.
Enter your guess: 10
Too low! Try a higher number.
Enter your guess: 15
Too high! Try a lower number.
Enter your guess: 12
Too low! Try a higher number.
Enter your guess: 13
Too low! Try a higher number.
Enter your guess: 14
Correct! You guessed the number in 7 attempts.

Do you want to play another round? (yes/no): n

Game Over!
Total Rounds Played: 1
Final Score: 1
Thanks for playing!
