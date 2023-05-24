import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static HashMap<String, Integer> subjectClasses = new HashMap<>();
    public static int length;

    public static ArrayList<Subject> parse(String path) {
        ArrayList<Subject> subjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] strings = line.split(",");
                length = strings.length - 1;
                double[] values = new double[length];
                for (int i = 0; i < values.length; i++)
                    values[i] = Double.parseDouble(strings[i].trim());
                subjectClasses.put(strings[length], null);
                subjects.add(new Subject(strings[length], values));
            }
        } catch (Exception exception) { System.out.println(exception.getMessage()); }

        int identifier = 0;
        for (Map.Entry<String, Integer> entry : subjectClasses.entrySet())
            entry.setValue(identifier++);

        return subjects;
    }
}
