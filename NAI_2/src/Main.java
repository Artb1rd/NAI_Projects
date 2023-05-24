import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Perceptron PERCEPTRON = new Perceptron(0.01);
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;

    public static void main(String[] args) {
        PERCEPTRON.train();
        PERCEPTRON.compute(true);

        System.out.println("--------------------------------");

        setUsersData();
    }

    public static void setUsersData() {
        while (isRunning) {
            System.out.println("Enter values or 'exit': ");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                isRunning = false;
                break;
            }

            String[] attrsString = line.split(",");
            double[] attributes = new double[attrsString.length];

            try {
                for (int i = 0; i < attributes.length; i++)
                    attributes[i] = Double.parseDouble(attrsString[i].trim());
            } catch (Exception exception) {
                System.out.println("Entered data is incorrect!");
                System.out.println("------------------------------------");
                setUsersData();
                break;
            }

            ArrayList<Subject> subjects = new ArrayList<>();
            subjects.add(new Subject(null, attributes));
            PERCEPTRON.setTestArray(subjects);
            PERCEPTRON.compute(false);

            System.out.println("---------------------------------------");
        }
    }
}
