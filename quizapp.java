import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ar {
    String s;
    List<String> options;
    int correctOption;

    public ar(String s, List<String> options, int correctOption) {
        this.s = s;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class Quiz {
    private List<ar> arrlist;
    private int score;
    private ScheduledExecutorService timerExecutor;

    public Quiz(List<ar> arrlist) {
        this.arrlist = arrlist;
        this.score = 0;
        this.timerExecutor = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (ar s : arrlist) {
            displays(s);

            int selectedOption = getUserInput(scanner, s.options.size());
            checkAnswer(s, selectedOption);

            System.out.println("Current Score: " + score + "\n");
        }

        System.out.println("Quiz Finished!");
        System.out.println("Your Score: " + score + " out of " + arrlist.size());

        timerExecutor.shutdown();
    }

    private void displays(ar s) {
        System.out.println(s.s);
        for (String option : s.options) {
            System.out.println(option);
        }
        startTimer(s);
    }

    private void startTimer(ar s) {
        timerExecutor.schedule(() -> {
            System.out.println("\nTime's up! Moving to the next.");
            checkAnswer(s, -1);
        }, 15, TimeUnit.SECONDS);
    }

    private int getUserInput(Scanner scanner, int numOptions) {
        int selectedOption = -1;
        do {
            System.out.print("Enter your choice (1-" + numOptions + "): ");
            try {
                selectedOption = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                selectedOption = -1;
            }
        } while (selectedOption < 1 || selectedOption > numOptions);
        return selectedOption;
    }

    private void checkAnswer(ar s, int selectedOption) {
        if (selectedOption == s.correctOption) {
            System.out.println("Correct!");
            score++;
        } else if (selectedOption != -1) {
            System.out.println("Incorrect! The correct answer was: " + s.options.get(s.correctOption - 1));
        }
    }
}

public class quizapp {
    public static void main(String[] args) {
        List<ar> arrlist = initializearrlist();
        Quiz quiz = new Quiz(arrlist);
        quiz.start();
    }

    private static List<ar> initializearrlist() {
        List<ar> arrlist = new ArrayList<>();
        List<String> options1 = Arrays.asList("1) Ganymede", "2) Titan", "3) Europa");
        arrlist.add(new ar("1: What is the largest moon in the solar system?", options1, 1));

        List<String> options2 = Arrays.asList("1) Isaac Newton", "2) Johannes Kepler", "3) Galileo Galilei");
        arrlist.add(new ar("2: Which famous astronomer formulated the three laws of planetary motion", options2, 2));

        List<String> options3 = Arrays.asList("1) Voyager", "2) Juno", "3) Hubble");
        arrlist.add(new ar("3: What is the name of NASA's most famous space telescope?", options3, 3));

        List<String> options4 = Arrays.asList("1) Milky Way", "2) Andromeda", "3) Orion");
        arrlist.add(new ar("4: Which galaxy is the nearest spiral galaxy to the Milky Way?", options4, 2));

        List<String> options5 = Arrays.asList("1) Sally Ride", "2) Valentina Tereshkova", "3) Mae Jemison");
        arrlist.add(new ar(
                "5: Who became the first woman to travel into space, flying aboard the Vostok 6 spacecraft in 1963?",
                options5, 2));
        return arrlist;
    }
}
