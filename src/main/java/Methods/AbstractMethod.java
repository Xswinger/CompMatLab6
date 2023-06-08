package Methods;

import Functions.Function;

import java.util.Arrays;

public abstract class AbstractMethod implements Method {

    public Function solvableFunction;
    public double[] x;
    public double yZero;
    public double rightBorder;
    public double leftBorder;
    public double step;
    public double accuracy;
    public double[] solutions;
    public AbstractMethod(double[] x, double yZero, double rightBorder, double leftBorder, double step, double accuracy, Function selectedFunction) {
        this.x = x;
        this.yZero = yZero;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.step = step;
        this.accuracy = accuracy;
        this.solvableFunction = selectedFunction;
        this.solutions = new double[x.length];
        this.solutions[0] = yZero;
    }

    @Override
    public double countFunction(double x, double y) {
        return solvableFunction.solution(x, y);
    }

    @Override
    public double[] getErrors() {
        return solutions;
    }

}
