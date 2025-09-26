import java.util.*;

// 🟢 User Class (represents player)
class User {
    String username;
    int score;

    public User(String username) {
        this.username = username;
        this.score = 0;
    }
}

public class QuizQuest{
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    // Questions, Options, Correct Answer Index
    static String[] questions = {
        "Q1. What is the capital of France?",
        "Q2. Which language is used for Android development?",
        "Q3. Who is the father of Computers?",
        "Q4. What is the national animal of India?",
        "Q5. Which planet is known as the Red Planet?",
        "Q6. Who invented Java Programming?",
        "Q7. What is the square root of 144?",
        "Q8. Which data structure uses LIFO?",
        "Q9. What is 25 * 4?",
        "Q10. Which gas do plants release during photosynthesis?",
        "Q11. Who is known as the Missile Man of India?",
        "Q12. What is the boiling point of water (in Celsius)?",
        "Q13. Which ocean is the largest?",
        "Q14. Which year did World War II end?",
        "Q15. Who wrote 'Romeo and Juliet'?",
        "Q16. Which device is used to measure temperature?",
        "Q17. What is the chemical symbol for Gold?",
        "Q18. Who discovered gravity?",
        "Q19. Which is the fastest land animal?",
        "Q20. Who painted the Mona Lisa?"
    };

    static String[][] options = {
        {"Berlin", "Madrid", "Paris", "Rome"},
        {"Kotlin", "Java", "Python", "C++"},
        {"Charles Babbage", "Alan Turing", "Bill Gates", "Steve Jobs"},
        {"Lion", "Tiger", "Elephant", "Peacock"},
        {"Jupiter", "Venus", "Earth", "Mars"},
        {"Dennis Ritchie", "James Gosling", "Guido van Rossum", "Bjarne Stroustrup"},
        {"10", "11", "12", "14"},
        {"Queue", "Stack", "Array", "Tree"},
        {"50", "75", "100", "125"},
        {"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"},
        {"Vikram Sarabhai", "APJ Abdul Kalam", "Homi Bhabha", "C. V. Raman"},
        {"0", "50", "100", "212"},
        {"Atlantic", "Pacific", "Indian", "Arctic"},
        {"1918", "1939", "1945", "1965"},
        {"William Wordsworth", "William Shakespeare", "Charles Dickens", "John Milton"},
        {"Barometer", "Thermometer", "Speedometer", "Altimeter"},
        {"Ag", "Au", "Pb", "Pt"},
        {"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla"},
        {"Horse", "Leopard", "Cheetah", "Tiger"},
        {"Leonardo da Vinci", "Pablo Picasso", "Vincent Van Gogh", "Michelangelo"}
    };

    // Correct option index (1-based)
    static int[] correctOptions = {
        3, 2, 1, 2, 4,
        2, 3, 2, 3, 1,
        2, 3, 2, 3, 2,
        2, 2, 2, 3, 1
    };

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("      🔍 Welcome to Quiz Portal 🔍   ");
        System.out.println("=====================================");

        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        User user = new User(username);

        // Menu Loop
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Start Quiz");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                startQuiz(user);
            } else if (choice.equals("2")) {
                System.out.println("👋 Goodbye, " + user.username + "! Thanks for playing!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Start Quiz Logic
    public static void startQuiz(User user) {
        user.score = 0; // reset score
        System.out.println("\n🔹 Starting Quiz for " + user.username + " 🔹\n");

        int attempted = 0;
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ". " + options[i][j]);
            }
            System.out.println("Enter your choice (1-4) or 0 to exit quiz:");

            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if (choice >= 0 && choice <= 4) break;
                } else {
                    sc.nextLine(); // clear invalid input
                }
                System.out.println("Invalid input. Please enter 0-4.");
            }

            if (choice == 0) {
                System.out.println("\n🚪 Exiting quiz early...");
                break;
            }

            attempted++;
            if (choice == correctOptions[i]) {
                System.out.println("✅ Correct!\n");
                user.score++;
            } else {
                System.out.println("❌ Wrong!");
                boolean bonus = playRockPaperScissors(options[i][correctOptions[i] - 1]);
                if (bonus) user.score++;
            }
        }

        System.out.println("\n🎓 Quiz Over! " + user.username +
                           ", Your Score: " + user.score + "/" + attempted);
    }

    // Rock-Paper-Scissors Penalty Game
    public static boolean playRockPaperScissors(String correctAnswer) {
        System.out.println("\n🎮 Rock-Paper-Scissors Time!");
        System.out.println("Choose: 1. Rock (R) | 2. Paper (P) | 3. Scissors (S)");

        int user = 0;
        while (true) {
            System.out.print("Your choice: ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("1") || input.equals("rock") || input.equals("r")) {
                user = 1; break;
            } else if (input.equals("2") || input.equals("paper") || input.equals("p")) {
                user = 2; break;
            } else if (input.equals("3") || input.equals("scissors") || input.equals("s")) {
                user = 3; break;
            } else {
                System.out.println("Invalid input. Enter 1/2/3 or R/P/S.");
            }
        }

        int computer = rand.nextInt(3) + 1;
        String[] choices = {"Rock", "Paper", "Scissors"};

        System.out.println("You chose: " + choices[user - 1]);
        System.out.println("Computer chose: " + choices[computer - 1]);

        if (user == computer) {
            System.out.println("🤝 It's a draw! No bonus point.\n");
            return false;
        } else if ((user == 1 && computer == 3) ||
                   (user == 2 && computer == 1) ||
                   (user == 3 && computer == 2)) {
            System.out.println("🎉 You won Rock-Paper-Scissors! Correct answer was: " + correctAnswer + "\n");
            return true;
        } else {
            System.out.println("😢 You lost Rock-Paper-Scissors. Correct answer was: " + correctAnswer + "\n");
            return false;
        }
    }
}
