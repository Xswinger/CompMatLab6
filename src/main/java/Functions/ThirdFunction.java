package Functions;

public class ThirdFunction implements Function{
    @Override
    public double solution(double x, double y) {
        return Math.exp(x) + y;
    }

    @Override
    public double getCoefficientC(double x, double y) {
        return (y - x * Math.exp(x))/Math.exp(x);
    }

    @Override
    public double solutionAccurate(double x, double c) {
        return c * Math.exp(x) + x * Math.exp(x);
    }
}
