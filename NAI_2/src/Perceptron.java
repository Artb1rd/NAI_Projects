import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Perceptron {
    private ArrayList<Subject> testArray, trainingArray;
    private double[] weights;
    private double theta, alpha;

    public Perceptron(double alpha) {
        testArray = Parser.parse("C:\\NAI_2\\src\\testData");
        trainingArray = Parser.parse("C:\\NAI_2\\src\\trainingData");
        randomizeValues();
        this.alpha = alpha;
    }

    private void randomizeValues() {
        theta = (Math.random());

        weights = new double[Parser.length];
        for (int i = 0; i < weights.length; i++)
            weights[i] = (Math.random());
    }

    private int determine(Subject subject) {
        double net = 0;

        for (int i = 0; i < subject.getValues().length; i++)
            net += subject.getValues()[i] * weights[i];


        net-=theta;

        return (net >= 0? 1:0);
    }

    public void train() {
        for (int i = 0; i < trainingArray.size()*5; i++){
            int summaryError = 0;
            for (Subject subject : trainingArray) {
                int y = determine(subject);
                int d = 0;

                for (Map.Entry<String, Integer> entry : Parser.subjectClasses.entrySet())
                    if (entry.getKey().equals(subject.getName()))
                        d = entry.getValue();

                int error = (d - y);



                for (int j = 0; j < Parser.length; j++)
                    weights[j] += alpha * subject.getValues()[j] * error;

                theta -= error * alpha;

                error = error*error;
                summaryError += error;
            }
            if (summaryError <= 1) break;
        }
    }

    public void compute(boolean show) {
        int y;
        double total = 0, found = 0;
        for (Subject subject : testArray) {
            y = determine(subject);

            String probableName = null;
            for (Map.Entry<String, Integer> entry : Parser.subjectClasses.entrySet())
                if (y == entry.getValue())
                    probableName = entry.getKey();

            if (show && subject.getName().equals(probableName)) found++;
            total++;

            if(!show){
                System.out.println(Arrays.toString(subject.getValues()) + ", Name:" + probableName);
            }
        }
        if (show) {
            System.out.println("Accuracy: " + found / total);
        }

    }

    public ArrayList<Subject> getTestArray() { return testArray; }

    public void setTestArray(ArrayList<Subject> testArray) { this.testArray = testArray; }

    public ArrayList<Subject> getTrainingArray() { return trainingArray; }

    public void setTrainingArray(ArrayList<Subject> trainingArray) { this.trainingArray = trainingArray; }

    public double[] getWeights() { return weights; }

    public void setWeights(double[] weights) { this.weights = weights; }

    public double getTheta() { return theta; }

    public void setTheta(double theta) { this.theta = theta; }

    public double getAlpha() { return alpha; }

    public void setAlpha(double alpha) { this.alpha = alpha; }
}