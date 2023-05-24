import java.util.*;

public class KNN {
    private ArrayList<Subject> testArray;
    private ArrayList<Subject> trainingArray;
    private int k;

    public KNN() {
        this.testArray = Parser.parse("C:\\NAI_1\\src\\irisTestData");
        this.trainingArray = Parser.parse("C:\\NAI_1\\src\\irisData");
//        this.testArray = Parser.parse("C:\\NAI_1\\src\\testData");
//        this.trainingArray = Parser.parse("C:\\NAI_1\\src\\testTrainingData");
//        this.testArray = Parser.parse("C:\\NAI_1\\src\\wdbcTestData");
//        this.trainingArray = Parser.parse("C:\\NAI_1\\src\\wdbcData");
//        17.3,23.0,11.3,90.3,1.0,1.0,1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2.0,1.10,1.11,1.12,1.13,1.14,1.15,1.16,1.17,1.18,1.19,1.20,1.21,1.22
    }

    private double calcDistance(Subject s1, Subject s2) {
        double distance = 0;

        Subject subject;

        if (s1.getValues().length > s2.getValues().length) subject = s2;
        else subject = s1;

        for (int i = 0; i < subject.getValues().length; i++)
            distance += Math.pow(s1.getValues()[i] - s2.getValues()[i], 2);

        return Math.sqrt(distance);
    }

    private String classify() {
        if (this.getK() > trainingArray.size())
            this.setK(trainingArray.size());

        Parser.occurrences.entrySet().forEach(entry -> entry.setValue(0));

        for (int i = 0; i < this.getK(); i++)
            for (Map.Entry<String, Integer> entry : Parser.occurrences.entrySet())
                if (trainingArray.get(i).getName().equals(entry.getKey()))
                    entry.setValue(entry.getValue() + 1);

        return Collections.max(Parser.occurrences.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }


    public double compute(boolean show) {
        double found = 0;

        for (Subject subjectTest : this.getTestArray()) {
            for (Subject subjectTraining : this.getTrainingArray())
                subjectTraining.setDistance(calcDistance(subjectTest, subjectTraining));

            Collections.sort(trainingArray);
            String name = classify();

            if (name.equals(subjectTest.getName())) found++;
            if (show) System.out.println("Subject " + Arrays.toString(subjectTest.getValues()) + " , Name: " + name);
        }
        return found/this.getTestArray().size();
    }

    public ArrayList<Subject> getTestArray() { return testArray; }

    public void setTestArray(ArrayList<Subject> testArray) { this.testArray = testArray; }

    public ArrayList<Subject> getTrainingArray() { return trainingArray; }

    public void setTrainingArray(ArrayList<Subject> trainingArray) { this.trainingArray = trainingArray; }

    public int getK() { return k; }

    public void setK(int k) { this.k = k; }
}

