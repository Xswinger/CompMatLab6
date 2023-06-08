package Methods.OneStepMethods;

import Functions.Function;

public class ImprovedEulerMethod extends AbstractOneStepMethod {

    public ImprovedEulerMethod(double[] x, double y, double rightBorder, double leftBorder, double step, double accuracy, Function selectedFunction) {
        super(x, y, rightBorder, leftBorder, step, accuracy, selectedFunction);
    }

    public final double[] solvedValues = new double[x.length];

    private final double[] returnErrors = new double[x.length];

    @Override
    public double[] solutionCycle() {

        double currentStep = step;
        int correctionCount = 0;
        int i = 0;
        errors[0] = 0;

        while (i < x.length - 1) {

            double firstTerm = countFunction(x[i], solutions[i]);
            double secondTerm = countFunction(x[i+1], (solutions[i] + currentStep * countFunction(x[i], solutions[i])));
            double secondTermSmaller = countFunction(x[i+1], (solutions[i] + (currentStep / 2) * countFunction(x[i], solutions[i])));


            solutions[i+1] = solutions[i] + (currentStep / 2) * (firstTerm + secondTerm);
            smallerSolutions[i+1] = smallerSolutions[i] + (currentStep / 4) * (firstTerm + secondTermSmaller);

            errors[i+1] = getRungeRuleError(solutions[i+1], smallerSolutions[i+1], 2);

            if (errors[i] > accuracy && correctionCount < 3) {
                currentStep /= 2;
                i = 0;
                recalculationNewX(currentStep);

                double tmp = solutions[0];

                solutions = new double[x.length];
                solutions[0] = tmp;
                smallerSolutions = new double[x.length];
                smallerSolutions[0] = tmp;
                errors = new double[x.length];

                correctionCount += 1;
            } else {
                i++;
            }
        }

        int k = 0;
        for (int j = 0; j < solutions.length; j += (step/currentStep)) {
            solvedValues[k] = solutions[j];
            returnErrors[k] = errors[j];
            k += 1;
        }

        return solvedValues;
    }

    @Override
    public double[] getErrors() {
        return returnErrors;
    }


}
