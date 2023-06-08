import Functions.FirstFunction;
import Functions.Function;
import Functions.SecondFunction;
import Functions.ThirdFunction;
import Methods.Method;
import Methods.MultiStepsMethods.MilnaMethod;
import Methods.MultiStepsMethods.MilnaPreliminaryValues;
import Methods.OneStepMethods.ImprovedEulerMethod;
import Methods.OneStepMethods.RungeKutta4DegreeMethod;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "HandlerServlet", value = "/handler")
public class Handler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter writer = resp.getWriter();

        resp.setContentType("text/html");

        Response response = new Response();
        double[] xData = Arrays.stream(req.getParameterValues("xData[]")).mapToDouble(Double::parseDouble).toArray();
        double yZero = Double.parseDouble(req.getParameter("yZero"));
        double rightBorder = Double.parseDouble(req.getParameter("rightBorder"));
        double leftBorder = Double.parseDouble(req.getParameter("leftBorder"));
        double step = Double.parseDouble(req.getParameter("step"));
        double accuracy = Double.parseDouble(req.getParameter("accuracy"));
        int selectedFunction = Integer.parseInt(req.getParameter("selectedFunction"));

        Function function = initializeFunctions()[selectedFunction];

        MilnaPreliminaryValues holder = new MilnaPreliminaryValues();

        Method eulerMethod = new ImprovedEulerMethod(xData, yZero, rightBorder, leftBorder, step, accuracy, function);
        Method rungeMethod = new RungeKutta4DegreeMethod(xData, yZero, rightBorder, leftBorder, step, accuracy, function, holder);

        response.setImprovedEulerMethodValues(eulerMethod.solutionCycle());
        response.setImprovedEulerMethodAccuracies(eulerMethod.getErrors());

        response.setRungeKutta4DegreeMethodValues(rungeMethod.solutionCycle());
        response.setRungeKutta4DegreeMethodAccuracies(rungeMethod.getErrors());

            MilnaMethod milnaMethod = new MilnaMethod(xData, yZero, rightBorder, leftBorder, step, accuracy, function, holder.getValues());

        response.setMilnaMethodValues(milnaMethod.solutionCycle());
        response.setMilnaMethodAccuracies(milnaMethod.getError());
        response.setAccurateValues(milnaMethod.getAccurateY());

        writer.println(gson.toJson(response));

        resp.setContentType("text/html");

    }


    private Function[] initializeFunctions() {
        return new Function[] {
            new FirstFunction(), new SecondFunction(), new ThirdFunction()
        };
    }
}
