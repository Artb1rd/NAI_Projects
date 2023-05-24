
public class Subject implements Comparable<Subject> {
    private String name;
    private double[] values;

    private double distance;

    public Subject(String name, double[] values) {
        this.name = name;
        this.values = values;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double[] getValues() { return values; }

    public void setValues(double[] values) { this.values = values; }

    public double getDistance() { return distance; }

    public void setDistance(double distance) { this.distance = distance; }

    @Override
    public int compareTo(Subject subject) {
        double difference = this.getDistance() - subject.getDistance();
        if (difference > 0) return 1;
        else if (difference < 0) return -1;
        else return 0;
    }
}