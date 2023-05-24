import java.util.*;

public class Main {
    private static final KNN KNN = new KNN();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;

    public static void main(String[] args) {
        setUsersK();
        System.out.println("Accuracy: " + KNN.compute(false));
        System.out.println("--------------------------------");
        setUsersData();
    }

    public static void setUsersK(){
        System.out.println("Enter int k:");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            if(k<1) throw new Exception("Incorrect k value");
            else
            KNN.setK(k);
        } catch (Exception ex){
            System.out.println("Incorrect value, try again");
            setUsersK();
        }
    }

    public static void setUsersData() {
        while (isRunning) {
            System.out.println("Enter values or 'exit': ");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                isRunning = false;
                break;
            }

            String[] valuesString = line.split(",");
            double[] values = new double[valuesString.length];

            try {
                for (int i = 0; i < values.length; i++)
                    values[i] = Double.parseDouble(valuesString[i].trim());
            } catch (Exception exception) {
                System.out.println("Entered data is incorrect!");
                System.out.println("--------------------------------");
                setUsersData();
                break;
            }

            ArrayList<Subject> subjects = new ArrayList<>();
            subjects.add(new Subject(null, values));
            KNN.setTestArray(subjects);
            KNN.compute(true);
            System.out.println("--------------------------------");
        }
    }
}