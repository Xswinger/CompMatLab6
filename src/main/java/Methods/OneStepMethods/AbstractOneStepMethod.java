package Methods.OneStepMethods;

import Functions.Function;
import Methods.AbstractMethod;

public abstract class AbstractOneStepMethod extends AbstractMethod {
    public AbstractOneStepMethod(double[] x, double y, double rightBorder, double leftBorder, double step, double accuracy, Function selectedFunction) {
        super(x, y, rightBorder, leftBorder, step, accuracy, selectedFunction);
        this.smallerSolutions[0] = yZero;
    }

    public double[] smallerSolutions = new double[x.length];

    public double[] errors = new double[x.length];

    public void recalculationNewX(double newStep) {

        x = new double[2 * x.length - 1];

        for (int i = 0; i <= (rightBorder - leftBorder)/newStep; i++) {
            x[i] = leftBorder + newStep * i;
        }

    }

    public double getRungeRuleError(double yCurrentStep, double ySmallerStep, int p) {
        return (Math.abs(yCurrentStep - ySmallerStep)/(Math.pow(2, p) - 1));
    }

}
