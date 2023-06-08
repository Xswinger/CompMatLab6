package Functions;

public class FirstFunction implements Function {
    @Override
    public double solution(double x, double y) {
        return ((2/x)*y + 2 * Math.pow(x, 3));
    }

    @Override
    public double getCoefficientC(double x, double y) {
        return ((Math.pow(x, 4) - y)/(Math.pow(x, 2)));
    }

    @Override
    public double solutionAccurate(double x, double c) {
        return (Math.pow(x, 4) + c * Math.pow(x, 2));
    }
}
