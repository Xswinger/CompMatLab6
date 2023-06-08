package Functions;

public class SecondFunction implements Function {
    @Override
    public double solution(double x, double y) {
        return (x)/(y);
    }

    @Override
    public double getCoefficientC(double x, double y) {
        return Math.pow(y, 2) - Math.pow(x, 2);
    }

    @Override
    public double solutionAccurate(double x, double c) {
        return Math.sqrt(Math.pow(x, 2) + c);
    }
}
