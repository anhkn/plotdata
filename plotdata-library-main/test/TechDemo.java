public class TechDemo {
    public static void main(String[] args) {
        double[] x1 = { 0, 2, 5, 9, 14, 26, 39, 43, 52, 64 };
        double[] y1 = { 1, 13, 19, 26, 32, 41, 48, 56, 64, 73 };

        double[] linreg = StdStat.polyMatrix(x1, y1, 1);
        double[] quadreg = StdStat.polyMatrix(x1, y1, 2);
        double[] cubreg = StdStat.polyMatrix(x1, y1, 3);

        StdOut.println("correlation coefficient: " + StdStat.corCoef(x1, y1));
        StdOut.println("r^2: " + StdStat.rSqr(x1, y1));
        StdOut.println("Mean (y1): " + StdStat.mean(y1));
        StdOut.println("Mean (y1) from [3,8): " + StdStat.mean(y1, 3, 8));
        StdOut.println("Minimum (y1): " + StdStat.min(y1));
        StdOut.println("Minimum (y1) from [3,8): " + StdStat.min(y1, 3, 8));
        StdOut.println("Maximum (y1): " + StdStat.max(y1));
        StdOut.println("Maxmimum (y1) from [3,8): " + StdStat.max(y1, 3, 8));

        StdOut.println("Linear regression: " +
                               StdStat.round(StdStat.linRegSlope(x1, y1),
                                             3) + "x" + " + " +
                               StdStat.round(StdStat.linRegInt(x1, y1),
                                             3));
        StdOut.print("Prediction for x=52 : " + StdStat.predict(52, linreg) +
                             " -- Actual: " + "64");
        StdOut.println("\nPolynomial regression (degree 2): ");
        for (int i = 0; i < quadreg.length; i++) {
            if (i == 0) StdOut.print(StdStat.round(quadreg[i], 3) + " + ");
            if (i == (quadreg.length - 1))
                StdOut.print(StdStat.round(quadreg[i], 3) + "x^" + i);
            else if (i != 0)
                StdOut.print(StdStat.round(quadreg[i], 3) + "x^" + i + " + ");
        }
        StdOut.println("\nPrediction for x=52 (degree 2) : " + StdStat.predict(52, quadreg) +
                               " -- Actual: " + "64");
        StdOut.println("Polynomial regression (degree 3): ");
        for (int i = 0; i < cubreg.length; i++) {
            if (i == 0) StdOut.print(StdStat.round(cubreg[i], 3) + " + ");
            if (i == (cubreg.length - 1))
                StdOut.print(StdStat.round(cubreg[i], 5) + "x^" + i);
            else if (i != 0)
                StdOut.print(StdStat.round(cubreg[i], 3) + "x^" + i + " + ");
        }
        StdOut.println("\nPrediction for x=52 (degree 3) : " + StdStat.predict(52, cubreg) +
                               " -- Actual: " + "64");
        StdOut.println();
        StdOut.println();
        StdOut.println();
        StdOut.println("Stats Demo + CSV Demo: ");
        StdCSV csvTD = new StdCSV("test/techdemo.csv", true, false);
        StdOut.println("Headers:");
        String[] headers = csvTD.getHeaders();
        for (String s : headers) {
            StdOut.println(s + ", Mean: " + StdStat.round(StdStat.mean(csvTD.getData(s)), 3) +
                                   ", Standard Deviation (pop.): " +
                                   StdStat.round(StdStat.stdDevp(csvTD.getData(s)), 3)
                                   + ", Z-score of 1: " + StdStat.round(
                    StdStat.zScore(1, csvTD.getData(s)), 3));
        }
        /*StdPlot.setGraph(1000, "Title", "x-axis", "y-axis");
        StdPlot.plotRegression(x1, y1, 2, true);
        StdPlot.plotPoints(x1, y1);
*/
        String[] barheaders = {
                "header1", "header2", "header3",
                "header4", "header5"
        };
        double[] values = { 4, 3, 6, 3, 8 };
        StdPlot.plotBars("Example caption", "Title",
                         "X-axis", barheaders, values);
    }
}
