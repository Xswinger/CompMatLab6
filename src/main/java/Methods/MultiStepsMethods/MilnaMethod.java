package Methods.MultiStepsMethods;

import Functions.Function;
import Methods.AbstractMethod;

public class MilnaMethod extends AbstractMethod {

    private double error;

    private final double[] accurateY = new double[x.length];

    public MilnaMethod(double[] x, double y, double rightBorder, double leftBorder, double step, double accuracy, Function selectedFunction, double[] pastSolutions) {
        super(x, y, rightBorder, leftBorder, step, accuracy, selectedFunction);
        this.solutions = new double[x.length];
        this.solutions[0] = pastSolutions[0];
        this.solutions[1] = pastSolutions[1];
        this.solutions[2] = pastSolutions[2];
        this.solutions[3] = pastSolutions[3];
    }

    @Override
    public double[] solutionCycle() {

        calculateAccurateY();

        int count = 0;

        for (int i = 4; i < x.length; i++) {

            double forecastY = forecast(i);
            double correctionY = correction(i, forecastY);

            while (Math.abs(correctionY - accurateY[i]) > accuracy) {
                if (count < 5) {
                    correctionY = correction(i, correctionY);
                    count += 1;
                } else {
                    break;
                }
            }
            count = 0;
            solutions[i] = correctionY;
        }

        double max_error = -100000;

        for (int i = 0; i < accurateY.length; i++) {
            if (max_error < Math.abs(accurateY[i] - solutions[i])) {
                max_error = Math.abs(accurateY[i] - solutions[i]);
            }
        }

        error = max_error;

        return solutions;
    }

    private double forecast(int index) {

        double firstTerm = countFunction(x[index - 3], solutions[index - 3]);
        double secondTerm = countFunction(x[index - 2], solutions[index - 2]);
        double thirdTerm = countFunction(x[index - 1], solutions[index - 1]);

        return (solutions[index - 4] + ((4 * step)/(3)) * (2 * firstTerm - secondTerm + 2 * thirdTerm));
    }

    private double correction(int index, double forecastY) {

        double firstTerm = countFunction(x[index - 2], solutions[index - 2]);
        double secondTerm = countFunction(x[index - 1], solutions[index - 1]);
        double thirdTerm = countFunction(x[index], forecastY);

        return (solutions[index - 2] + (step/3) * (firstTerm + 4 * secondTerm + thirdTerm));
    }

    private void calculateAccurateY() {

        double coefficientC = solvableFunction.getCoefficientC(x[0], yZero);
        accurateY[0] = yZero;

        for (int i = 1; i < x.length; i++) {
            accurateY[i] = solvableFunction.solutionAccurate(x[i], coefficientC);
        }
    }

    public double getError() {
        return error;
    }

    public double[] getAccurateY() {
        return accurateY;
    }
}
