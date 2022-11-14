import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

/**
 * This java file is supposed to easily create fun looking bar charts,
 * line plots with possibility to create regression lines that go along with it
 * and the labelling of axes.
 */
public class StdPlot {

    /**
     * Scale of the line drawing graphs
     */
    private static double SCALE;

    /**
     * Offset at which certain lines are drawn
     */
    private static double OFFSET;

    /**
     * Array of colors to use (BarChart.java, Kevin Wayne)
     */
    private static String[] colors = {
            "#aec7e8", "#c5b0d5", "#c49c94", "#dbdb8d", "#17becf",
            "#9edae5", "#f7b6d2", "#c7c7c7", "#1f77b4", "#ff7f0e",
            "#ffbb78", "#98df8a", "#d64c4c", "#2ca02c", "#9467bd",
            "#8c564b", "#ff9896", "#e377c2", "#7f7f7f", "#bcbd22",
            };

    /**
     * Are the axes are labeled?
     */
    private static boolean isLabeled = false;

    /**
     * x-points for setting up of label axes to not have it be influenced by
     * regression
     */
    private static double[] xPoints;

    /**
     * y-points for setting up of label axes to not have it be influenced by
     * regression
     */
    private static double[] yPoints;

    /**
     * Number of regression lines displayed
     */
    private static int regLineCount = 0;

    /**
     * Set up the graph for line drawings WITH text
     *
     * @param scale Arbitrary double value to scale the graph
     * @param title String title of the graph
     * @param xaxis String x-axis title
     * @param yaxis String y-axis title
     */
    public static void setGraph(double scale,
                                String title, String xaxis, String yaxis) {
        double truescale = 1.2 * scale;
        StdDraw.setScale(0, truescale);
        double offset = 0.1 * scale;
        double offsetRight = 1.1 * scale;
        StdDraw.line(offset, offset, offsetRight, offset);
        StdDraw.line(offset, offset, offset, offsetRight);
        SCALE = scale;
        OFFSET = offset;
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        double midpoint = (truescale) / 2;
        StdDraw.text(midpoint, OFFSET * 0.25, xaxis);
        StdDraw.text(OFFSET * 0.25, midpoint, yaxis, 90);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
        StdDraw.text(midpoint * 1.1, SCALE * 1.1, title);
    }

    /**
     * Set up the graph for line drawing WITHOUT text
     *
     * @param scale Arbitrary double value to scale the graph
     */
    public static void setGraph(double scale) {
        double truescale = 1.2 * scale;
        StdDraw.setScale(0, truescale);
        double offset = 0.1 * scale;
        double offsetRight = 1.1 * scale;
        StdDraw.line(offset, offset, offsetRight, offset);
        StdDraw.line(offset, offset, offset, offsetRight);
        SCALE = scale;
        OFFSET = offset;
    }

    /**
     * Normalize values on scale of (min, max]
     *
     * @param a Double array of values
     * @return double array of values normalized to (min, max]
     */
    public static double[] normalize(double[] a) {
        double min = StdStat.min(a);
        double max = StdStat.max(a);
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = min + (((SCALE - min) * (a[i] - min)) / (max - min));
        }
        return b;
    }

    /**
     * Plot points of x's and y's on graph
     *
     * @param x array of x-values
     * @param y array of y-values
     */
    public static void plotPoints(double[] x, double[] y) {
        double[] yn = normalize(y);
        double[] xn = normalize(x);
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < x.length; i++) {
            StdDraw.point(xn[i] + OFFSET, yn[i] + OFFSET);
        }
        if (!isLabeled) {
            xPoints = x;
            yPoints = y;
            StdDraw.setPenColor(Color.black);
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 10));
            double[] xLabels =
                    StdStat.linSpace(0, StdStat.max(xPoints), 10);
            double[] yLabels =
                    StdStat.linSpace(0, StdStat.max(yPoints), 10);
            double[] offsets = StdStat.linSpace(OFFSET, SCALE * 1.1, 10);
            for (int i = 0; i < xLabels.length; i++) {
                String xpos = Double.toString(StdStat.round(xLabels[i], 1));
                String ypos = Double.toString(StdStat.round(yLabels[i], 1));
                StdDraw.text(offsets[i], 0.75 * OFFSET, xpos);
                StdDraw.text(0.75 * OFFSET, offsets[i], ypos);
            }
            isLabeled = true;
        }
    }

    /**
     * Plot lines connecting array of x's and y's
     *
     * @param x array of x-values
     * @param y array of y-values
     */
    public static void plotLines(double[] x, double[] y) {
        double[] yn = normalize(y);
        double[] xn = normalize(x);
        StdDraw.setPenRadius(0.003);
        for (int i = 0; i < xn.length - 1; i++) {
            StdDraw.line(xn[i] + OFFSET, yn[i] + OFFSET,
                         xn[i + 1] + OFFSET, yn[i + 1] + OFFSET);
        }
        if (!isLabeled) {
            StdDraw.setPenColor(Color.black);
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 10));
            double[] xLabels =
                    StdStat.linSpace(0, StdStat.max(xPoints), 10);
            double[] yLabels =
                    StdStat.linSpace(0, StdStat.max(yPoints), 10);
            double[] offsets = StdStat.linSpace(OFFSET, SCALE * 1.1, 10);
            for (int i = 0; i < xLabels.length; i++) {
                String xpos = Double.toString(StdStat.round(xLabels[i], 1));
                String ypos = Double.toString(StdStat.round(yLabels[i], 1));
                StdDraw.text(offsets[i], 0.75 * OFFSET, xpos);
                StdDraw.text(0.75 * OFFSET, offsets[i], ypos);
            }
            isLabeled = true;
        }
    }

    /**
     * Plots regression line of specified x's and y's and specified degree
     *
     * @param x      array of x-values
     * @param y      array of y-values
     * @param degree degree of polynomial regression
     */
    public static void plotRegression(double[] x, double[] y, int degree) {
        xPoints = x;
        yPoints = y;
        double[] coeffs = StdStat.polyMatrix(x, y, degree);
        double[] yreg = StdStat.predict(x, coeffs);
        StdDraw.setPenColor(Color.decode(colors[regLineCount]));
        plotLines(x, yreg);
        regLineCount++;
    }

    /**
     * Plot regression line of specified x's and y's and specified degrees with
     * a lot of small points, aka smoothing
     *
     * @param x         array of x-values
     * @param y         array of y-values
     * @param degree    degree of polynomial regression
     * @param smoothing smooth or not
     */
    public static void plotRegression(double[] x, double[] y, int degree,
                                      boolean smoothing) {
        xPoints = x;
        yPoints = y;
        if (!smoothing) {
            plotRegression(x, y, degree);
        }
        else {
            double start = StdStat.min(x);
            double end = StdStat.max(x);
            double[] coeffs = StdStat.polyMatrix(x, y, degree);
            double[] xreg = StdStat.linSpace(start, end, 100);
            double[] yreg = StdStat.predict(xreg, coeffs);
            StdDraw.setPenColor(Color.decode(colors[regLineCount]));
            plotLines(xreg, yreg);
            regLineCount++;
        }
    }

    /**
     * Draws bar charts for provided header array and values associated with
     * array (BarChart.java), Kevin Wayne
     *
     * @param caption    caption string
     * @param title      title string
     * @param xAxisLabel x-axis label string
     * @param headers    headers string array
     * @param x          array of x-values correlating to headers
     * @author Kevin Wayne
     */
    public static void plotBars(String caption, String title, String xAxisLabel,
                                String[] headers, double[] x) {
        // nothing to draw
        if (headers.length == 0) return;

        // leave room for at least 8 bars
        int numberOfBars = Math.max(8, headers.length);

        // set the scale of the coordinate axes
        double xmax = Double.NEGATIVE_INFINITY;
        for (double value : x) {
            if (value > xmax) xmax = value;
        }

        StdDraw.setXscale(-0.01 * xmax, 1.2 * xmax);
        StdDraw.setYscale(-0.01 * numberOfBars, numberOfBars * 1.25);

        // draw title
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        StdDraw.text(0.6 * xmax, numberOfBars * 1.2, title);

        // draw x-axis label
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
        StdDraw.textLeft(0, numberOfBars * 1.10, xAxisLabel);

        // draw axes
        int units = getUnits(xmax);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int unit = 0; unit <= xmax; unit += units) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.text(unit, numberOfBars * 1.02,
                         String.format("%,d", unit));
            StdDraw.setPenColor(new Color(230, 230, 230));
            StdDraw.line(unit, 0.1, unit, numberOfBars * 1.0);
        }

        // draw caption
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        if (caption.length() <= 4) StdDraw.setFont(
                new Font("SansSerif", Font.BOLD, 100));
        else if (caption.length() <= 8) StdDraw.setFont(
                new Font("SansSerif", Font.BOLD, 60));
        else StdDraw.setFont(new Font("SansSerif", Font.BOLD, 40));
        StdDraw.textRight(1.15 * xmax, 0.2 * numberOfBars, caption);

        // draw bars
        int n = headers.length;
        for (int i = 0; i < n; i++) {
            String name = headers[i];
            double value = x[i];
            Color color = Color.decode(colors[i]);
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle(0.5 * value, numberOfBars - i - 0.5,
                                    0.5 * value, 0.4);
            StdDraw.setPenColor(StdDraw.BLACK);
            int fontSize = (int) Math.ceil(14 * 10.0 / numberOfBars);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, fontSize));
            StdDraw.textRight(value - 0.01 * xmax, numberOfBars - i - 0.5,
                              name);
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            DecimalFormat format = new DecimalFormat("0.#");
            StdDraw.textLeft(value + 0.01 * xmax, numberOfBars - i - 0.5,
                             format.format(value));
        }
    }

    /**
     * Returns the units of values in the bar chart (BarChart.java), Kevin Wayne
     *
     * @param xmax Maxmimum x-value
     * @return integer units of values in the bar chart
     * @author Kevin Wayne
     */
    private static int getUnits(double xmax) {
        int units = 1;
        while (Math.floor(xmax / units) >= 8) {
            // hack to identify 20, 200, 2000, ...
            if (units % 9 == 2) units = units * 5 / 2;
            else units = units * 2;
        }
        return units;
    }
}
