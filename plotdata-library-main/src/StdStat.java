import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This file is meant to go above the already developed StdStats by introducing
 * polynomial regression, linear regression, numpy inspired array creation, and
 * various statistical functions like z-scores, variance, standard deviation,
 * correlation coefficients, etc.
 */
public class StdStat {

    /**
     * Round double to {@code places} decimal places
     *
     * @param value  value to round
     * @param places places to round to
     * @return rounded double
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Returns the maximum value in the specified array
     *
     * @param a array of values
     * @return maximum in array {@code a}
     */
    public static double max(double[] a) {
        double max = Double.NEGATIVE_INFINITY;
        for (double val : a)
            if (val > max) max = val;
        return max;
    }

    /**
     * Returns the maximum value in the specified subarray
     *
     * @param x  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return maximum value between [lo, hi)
     */
    public static double max(double[] x, int lo, int hi) {
        if (x.length == 0) throw new IllegalArgumentException("Array is null.");
        if (!(0 <= lo && lo < hi && hi <= x.length))
            throw new IllegalArgumentException("");
        double max = Double.NEGATIVE_INFINITY;
        for (int i = lo; i < hi; i++)
            if (x[i] > max) max = x[i];
        return max;
    }


    /**
     * Returns the minimum value in the specified array
     *
     * @param a array of values
     * @return minimum value in array {@code a}
     */
    public static double min(double[] a) {
        double min = Double.POSITIVE_INFINITY;
        for (double value : a) {
            if (value < min) min = value;
        }
        return min;
    }

    /**
     * Returns the minimum value in the specified subarray
     *
     * @param a  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return minimum value between [lo, hi)
     */
    public static double min(double[] a, int lo, int hi) {
        if (a.length == 0) throw new IllegalArgumentException("Array is null.");
        if (!(0 <= lo && lo < hi && hi <= a.length))
            throw new IllegalArgumentException();
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            if (a[i] < min) min = a[i];
        }
        return min;
    }

    /**
     * Find standard deviation of population bounded by lo and hi subarray
     *
     * @param x  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return population standard deviation between [lo, hi)
     */
    public static double stdDevp(double[] x, int lo, int hi) {
        return Math.sqrt(variancep(x, lo, hi));
    }

    /**
     * Find variance of population bounded by lo and hi subarray
     *
     * @param x  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return variance of population between [lo, hi)
     */
    public static double variancep(double[] x, int lo, int hi) {
        if (x.length == 0) throw new IllegalArgumentException("Array is null.");
        if (!(0 <= lo && lo <= hi && hi <= x.length))
            throw new IllegalArgumentException();
        double variance = 0;
        double mean = mean(x, lo, hi);
        for (int i = lo; i < hi; i++) {
            variance += Math.pow(x[i] - mean, 2);
        }
        return variance / (hi - lo);
    }

    /**
     * Returns the average value in the specified subarray
     *
     * @param x  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return average between [lo, hi)
     */
    public static double mean(double[] x, int lo, int hi) {
        if (x.length == 0) throw new IllegalArgumentException("Array is null.");
        if (!(0 <= lo && lo < hi && hi <= x.length))
            throw new IllegalArgumentException();
        double mean = 0;
        for (int i = lo; i < hi; i++)
            mean += x[i];
        return mean / (hi - lo);
    }

    /**
     * Find standard deviation of sample bounded by lo and hi subarray
     *
     * @param x  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return standard deviation of sample between [lo, hi)
     */
    public static double stdDev(double[] x, int lo, int hi) {
        return Math.sqrt(variance(x, lo, hi));
    }

    /**
     * Returns the sample variance in the specified subarray
     *
     * @param x  array of values
     * @param lo low bound of array
     * @param hi high bound of array
     * @return sample variance between [lo, hi)
     */
    public static double variance(double[] x, int lo, int hi) {
        if (x.length == 0) throw new IllegalArgumentException("Array is null.");
        if (!(0 <= lo && lo < hi && hi <= x.length))
            throw new IllegalArgumentException();
        double variance = 0;
        double mean = mean(x, lo, hi);
        for (int i = lo; i < hi; i++) {
            variance += Math.pow(x[i] - mean, 2);
        }
        return variance / ((hi - lo) - 1);
    }

    /**
     * Returns standard score of value given data set
     *
     * @param value value to find standard score of
     * @param x     array of values
     * @return standard score of value
     */
    public static double zScore(double value, double[] x) {
        double mean = mean(x);
        double stddev = stdDevp(x);
        return (value - mean) / stddev;
    }

    /**
     * Returns the average value in the specified array
     *
     * @param a array of values
     * @return average of values
     */
    public static double mean(double[] a) {
        double mean = 0;
        for (double value : a)
            mean += value;
        return mean / a.length;
    }

    /**
     * Find standard deviation of population
     *
     * @param x array of values
     * @return standard deviation of population
     */
    public static double stdDevp(double[] x) {
        return Math.sqrt(variancep(x));
    }

    /**
     * Find variance of population
     *
     * @param x array of values
     * @return variance of population
     */
    public static double variancep(double[] x) {
        double variance = 0;
        for (double value : x) {
            variance += Math.pow(value - mean(x), 2);
        }
        return variance / x.length;
    }

    /**
     * Returns standard score for mean and stddev
     *
     * @param value  value to find standard score of
     * @param mean   average of data set
     * @param stddev population standard deviation of data set
     * @return standard score of value
     */
    public static double zScore(double value, double mean, double stddev) {
        return (value - mean) / stddev;
    }

    /**
     * Find standard deviation of sample
     *
     * @param x array of values
     * @return sample standard deviation
     */
    public static double stdDev(double[] x) {
        return Math.sqrt(variance(x));
    }

    /**
     * Find variance of sample
     *
     * @param x array of values
     * @return variance of sample
     */
    public static double variance(double[] x) {
        double variance = 0;
        for (double value : x) {
            variance += Math.pow(value - mean(x), 2);
        }
        return variance / (x.length - 1);
    }

    /**
     * Return r^2 coefficient of determination
     *
     * @param x array of known x-values
     * @param y array of known y-values
     * @return coefficient of determination
     */
    public static double rSqr(double[] x, double[] y) {
        return Math.pow(corCoef(x, y), 2);
    }

    /**
     * Returns correlation coefficient "r"
     *
     * @param x array of known x-values
     * @param y array of known y-values
     * @return correlation coefficient
     */
    public static double corCoef(double[] x, double[] y) {
        if (x.length != y.length) throw new
                IllegalArgumentException("Arrays are of different lengths.");
        double xbar = mean(x);
        double ybar = mean(y);
        double top = 0;
        double bottomx = 0;
        double bottomy = 0;
        for (int i = 0; i < x.length; i++) {
            top += (x[i] - xbar) * (y[i] - ybar);
            bottomx += Math.pow((x[i] - xbar), 2);
            bottomy += Math.pow((y[i] - ybar), 2);
        }
        return top / Math.sqrt(bottomy * bottomx);
    }

    /**
     * Return r^2 given r
     *
     * @param r correlation coefficient
     * @return r^2 coefficient of determination
     */
    public static double rSqr(double r) {
        return Math.pow(r, 2);
    }

    /**
     * Return intercept for linear regression
     *
     * @param x array of known x-values
     * @param y array of known y-values
     * @return y-intercept of linear regression
     */
    public static double linRegInt(double[] x, double[] y) {
        double intercept = mean(y) - linRegSlope(x, y) * mean(x);
        return intercept;
    }

    /**
     * Return slope for linear regression
     *
     * @param x array of known x-values
     * @param y array of known y-values
     * @return slope of linear regression
     */
    public static double linRegSlope(double[] x, double[] y) {
        double slope;
        int n = x.length;
        double xbar = mean(x);
        double ybar = mean(y);
        double xxbar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += Math.pow((x[i] - xbar), 2);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        slope = xybar / xxbar;
        return slope;
    }


    /**
     * Returns expected value given probability array p[]
     *
     * @param x array of values
     * @param p array of probabilities
     * @return expected value
     */
    public static double expVal(double[] x, double[] p) {
        double ux = 0;
        for (int i = 0; i < x.length; i++)
            ux += x[i] * p[i];
        return ux;
    }

    /**
     * Returns array of coefficients for polynomial regression in form
     * c_0 + c_1 * x + c_2 * x^2 ...
     *
     * @param x      array of known x-values
     * @param y      array of known y-values
     * @param degree degree of polynomial regression
     * @return coefficients of polynomial regression c_0, c_1, etc.
     */
    public static double[] polyMatrix(double[] x, double[] y, int degree) {
        int n = x.length;
        int m = degree + 1;
        double[][] xs = new double[m][m];
        double[] ys = new double[m];
        double[] xsums = new double[(degree * 2) + 1];
        xsums[0] = n;
        for (int i = 1; i < xsums.length; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) sum += Math.pow(x[j], i);
            xsums[i] = sum;
        }
        for (int rows = 0; rows < xs.length; rows++) {
            for (int cols = 0; cols < xs[rows].length; cols++) {
                xs[rows][cols] = xsums[(rows + cols)];
            }
        }

        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.pow(x[j], i) * y[j];
            }
            ys[i] = sum;
        }
        GaussJordanElimination gj = new GaussJordanElimination(xs, ys);
        return gj.primal();
    }

    /**
     * Provided x points and coefficient values from polyMatrix,
     * return predicted line
     *
     * @param x      array of x-values
     * @param coeffs array of coefficients of regression
     * @return array of expected y-values
     */
    public static double[] predict(double[] x, double[] coeffs) {
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] += predict(x[i], coeffs);
        }
        return y;
    }

    /**
     * Predict y-value for given single x-value
     *
     * @param x      x-value
     * @param coeffs array of coefficients of regression
     * @return expected y-value
     */
    public static double predict(double x, double[] coeffs) {
        double y = 0;
        for (int i = 0; i < coeffs.length; i++) y += coeffs[i] * Math.pow(x, i);
        return y;
    }

    /**
     * Provided start and end and number of points and coefficients
     * Return predicted line
     *
     * @param start  desired start of x-values
     * @param end    desired end of x-values
     * @param num    number of x-points
     * @param coeffs array of coefficients of regression
     * @return predicted y-values
     */
    public static double[] predict(double start, double end, int num,
                                   double[] coeffs) {
        double[] x = linSpace(start, end, num);
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] += predict(x[i], coeffs);
        }
        return y;
    }

    /**
     * Predict for endpoints disabled, if not then refer to regular
     *
     * @param start     deired start of x-values
     * @param end       desired end of x-values
     * @param num       number of x-points
     * @param coeffs    array of coefficients of regression
     * @param endpoints whether or not to include the "end" as an endpoint
     * @return predicted y-values
     */
    public static double[] predict(double start, double end, int num,
                                   double[] coeffs, boolean endpoints) {
        if (endpoints) return predict(start, end, num, coeffs);
        double[] x = linSpace(start, end, num, false);
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] += predict(x[i], coeffs);
        }
        return y;
    }

    /**
     * Inspired by numpy.linspace, create array of length num, starting with
     * start, and ending with end
     *
     * @param start desired start of array
     * @param end   desired end of array
     * @param num   number of points in array
     * @return array of [start, end] with "num" points
     */
    public static double[] linSpace(double start, double end, int num) {
        if (num <= 1) throw new
                IllegalArgumentException("num must be <= 1");
        double step = (end - start) / (num - 1);
        double[] x = new double[num];
        for (int i = 0; i < num; i++) {
            x[i] = start + (step * i);
        }
        return x;
    }

    /**
     * Create array of length num, starting with start, and ending one less step
     * than end
     *
     * @param start     desired start of array
     * @param end       desired end of array
     * @param num       number of points in array
     * @param endpoints whether to include "end" as endpoint
     * @return array of [start, end] or [start, end) with "num" points
     */
    public static double[] linSpace(double start, double end, int num,
                                    boolean endpoints) {
        if (num <= 1) throw new
                IllegalArgumentException("num must be <= 1");
        if (endpoints) return linSpace(start, end, num);
        double step = (end - start) / (num);
        double[] x = new double[num];
        for (int i = 0; i < num; i++) {
            x[i] = start + (step * i);
        }
        return x;
    }
}

