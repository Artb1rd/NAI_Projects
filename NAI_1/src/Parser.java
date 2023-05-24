import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, Integer> occurrences = new HashMap<>();

    public static ArrayList<Subject> parse(String path) {
        ArrayList<Subject> subjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
                while ((line = br.readLine())!=null) {
                String[] strings = line.split(",");
                double[] values = new double[strings.length-1];
                for (int i = 0; i < values.length; i++)
                    values[i] = Double.parseDouble(strings[i].trim());
                occurrences.put(strings[strings.length-1], 0);
                subjects.add(new Subject(strings[strings.length-1], values));
            }
        } catch (Exception exception) { System.out.println(exception.getMessage()); }
        return subjects;
    }
}