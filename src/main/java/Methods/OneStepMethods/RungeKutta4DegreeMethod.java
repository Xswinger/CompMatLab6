package Methods.OneStepMethods;

import Functions.Function;
import Methods.MultiStepsMethods.MilnaPreliminaryValues;

public class RungeKutta4DegreeMethod extends AbstractOneStepMethod {

    MilnaPreliminaryValues holder;

    public RungeKutta4DegreeMethod(double[] x, double y, double rightBorder, double leftBorder, double step, double accuracy, Function selectedFunction, MilnaPreliminaryValues holder) {
        super(x, y, rightBorder, leftBorder, step, accuracy, selectedFunction);
        this.holder = holder;
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

            double firstPart = currentStep * countFunction(x[i], solutions[i]);
            double secondPart = currentStep * countFunction(x[i] + (currentStep/2), solutions[i] + (firstPart/2));
            double thirdPart = currentStep * countFunction(x[i] + (currentStep/2), solutions[i] + (secondPart/2));
            double fourthPart = currentStep * countFunction(x[i] + currentStep, solutions[i] + thirdPart);

            double firstPartSmaller = (currentStep/2) * countFunction(x[i], solutions[i]);
            double secondPartSmaller = (currentStep/2) * countFunction(x[i] + (currentStep/4), solutions[i] + (firstPart/2));
            double thirdPartSmaller = (currentStep/2) * countFunction(x[i] + (currentStep/4), solutions[i] + (secondPartSmaller/2));
            double fourthPartSmaller = (currentStep/2) * countFunction(x[i] + currentStep, solutions[i] + thirdPartSmaller);

            solutions[i+1] = solutions[i] + ((double) 1/6) * (firstPart + 2 * secondPart + 2 * thirdPart + fourthPart);
            smallerSolutions[i+1] = smallerSolutions[i] + ((double) 1/6) * (firstPartSmaller + 2 * secondPartSmaller + 2 * thirdPartSmaller + fourthPartSmaller);

            errors[i+1] = getRungeRuleError(solutions[i], smallerSolutions[i], 4);

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

        holder.setValues(solvedValues);

        return solvedValues;
    }

    @Override
    public double[] getErrors() {
        return returnErrors;
    }


}
