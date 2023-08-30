import java.util.Scanner;
import java.util.Random;

public class numbergame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lb = 1;
        int ub = 100;
        int maxAttempts = 10;
        int s = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Number is between " + lb + " and " + ub);

        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {
            int sn = random.nextInt(ub - lb + 1) + lb;
            int attempts = 0;

            System.out.println("Round " + (s + 1));

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ": Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess < sn) {
                    System.out.println("Too low! Try again.");
                } else if (guess > sn) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the number " + sn + " correctly in "
                            + (attempts + 1) + " attempts.");
                    s++;
                    break;
                }

                attempts++;
            }

            if (attempts == maxAttempts) {
                System.out.println("You've reached the maximum number of attempts. The correct number was " + sn + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next();
        }
        scanner.close();
        System.out.println("Game over! You played " + s + " rounds.");
    }
}
