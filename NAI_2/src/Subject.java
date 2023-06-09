public class Subject {
    private String name;
    private double[] values;

    public Subject(String name, double[] values) {
        this.name = name;
        this.values = values;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double[] getValues() { return values; }

    public void setValues(double[] values) { this.values = values; }
}
