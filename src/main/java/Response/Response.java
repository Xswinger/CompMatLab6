package Response;

public class Response {

    private double[] milnaMethodValues;
    private double milnaMethodAccuracies;

    private double[] improvedEulerMethodValues;

    private double[] improvedEulerMethodAccuracies;

    private double[] rungeKutta4DegreeMethodValues;

    private double[] rungeKutta4DegreeMethodAccuracies;

    private double[] accurateValues;

    public double[] getMilnaMethodValues() {
        return milnaMethodValues;
    }

    public void setMilnaMethodValues(double[] milnaMethodValues) {
        this.milnaMethodValues = milnaMethodValues;
    }

    public double[] getImprovedEulerMethodValues() {
        return improvedEulerMethodValues;
    }

    public void setImprovedEulerMethodValues(double[] improvedEulerMethodValues) {
        this.improvedEulerMethodValues = improvedEulerMethodValues;
    }

    public double[] getRungeKutta4DegreeMethodValues() {
        return rungeKutta4DegreeMethodValues;
    }

    public void setRungeKutta4DegreeMethodValues(double[] rungeKutta4DegreeMethodValues) {
        this.rungeKutta4DegreeMethodValues = rungeKutta4DegreeMethodValues;
    }

    public double getMilnaMethodAccuracies() {
        return milnaMethodAccuracies;
    }

    public void setMilnaMethodAccuracies(double milnaMethodAccuracies) {
        this.milnaMethodAccuracies = milnaMethodAccuracies;
    }

    public double[] getImprovedEulerMethodAccuracies() {
        return improvedEulerMethodAccuracies;
    }

    public void setImprovedEulerMethodAccuracies(double[] improvedEulerMethodAccuracies) {
        this.improvedEulerMethodAccuracies = improvedEulerMethodAccuracies;
    }

    public double[] getRungeKutta4DegreeMethodAccuracies() {
        return rungeKutta4DegreeMethodAccuracies;
    }

    public void setRungeKutta4DegreeMethodAccuracies(double[] rungeKutta4DegreeMethodAccuracies) {
        this.rungeKutta4DegreeMethodAccuracies = rungeKutta4DegreeMethodAccuracies;
    }

    public double[] getAccurateValues() {
        return accurateValues;
    }

    public void setAccurateValues(double[] accurateValues) {
        this.accurateValues = accurateValues;
    }
}
