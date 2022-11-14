/**
 * Code stolen from COS226, credit to Kevin Wayne
 */
public class GaussJordanElimination {
    private static final double EPSILON = 1e-8;

    private final int n;      // n-by-n system
    private double[][] a;     // n-by-(n+1) augmented matrix

    // Gauss-Jordan elimination with partial pivoting

    public GaussJordanElimination(double[][] A, double[] b) {
        n = b.length;

        // build augmented matrix
        a = new double[n][n + n + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = A[i][j];

        // only needed if you want to find certificate of infeasibility (or compute inverse)
        for (int i = 0; i < n; i++)
            a[i][n + i] = 1.0;

        for (int i = 0; i < n; i++)
            a[i][n + n] = b[i];

        solve();
    }

    private void solve() {

        // Gauss-Jordan elimination
        for (int p = 0; p < n; p++) {
            // show();

            // find pivot row using partial pivoting
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }

            // exchange row p with row max
            swap(p, max);

            // singular or nearly singular
            if (Math.abs(a[p][p]) <= EPSILON) {
                continue;
                // throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            // pivot
            pivot(p, p);
        }
        // show();
    }

    // swap row1 and row2
    private void swap(int row1, int row2) {
        double[] temp = a[row1];
        a[row1] = a[row2];
        a[row2] = temp;
    }


    // pivot on entry (p, q) using Gauss-Jordan elimination
    private void pivot(int p, int q) {

        // everything but row p and column q
        for (int i = 0; i < n; i++) {
            double alpha = a[i][q] / a[p][q];
            for (int j = 0; j <= n + n; j++) {
                if (i != p && j != q) a[i][j] -= alpha * a[p][j];
            }
        }

        // zero out column q
        for (int i = 0; i < n; i++)
            if (i != p) a[i][q] = 0.0;

        // scale row p (ok to go from q+1 to n, but do this for consistency with simplex pivot)
        for (int j = 0; j <= n + n; j++)
            if (j != q) a[p][j] /= a[p][q];
        a[p][q] = 1.0;
    }

    /**
     * Returns a solution to the linear system of equations <em>Ax</em> = <em>b</em>.
     *
     * @return a solution <em>x</em> to the linear system of equations
     * <em>Ax</em> = <em>b</em>; {@code null} if no such solution
     */
    public double[] primal() {
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            if (Math.abs(a[i][i]) > EPSILON)
                x[i] = a[i][n + n] / a[i][i];
            else if (Math.abs(a[i][n + n]) > EPSILON)
                return null;
        }
        return x;
    }

    public double[] dual() {
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            if ((Math.abs(a[i][i]) <= EPSILON) && (Math.abs(a[i][n + n]) > EPSILON)) {
                for (int j = 0; j < n; j++)
                    y[j] = a[i][n + j];
                return y;
            }
        }
        return null;
    }

    public boolean isFeasible() {
        return primal() != null;
    }

    // print the tableaux
    private void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                StdOut.printf("%8.3f ", a[i][j]);
            }
            StdOut.printf("| ");
            for (int j = n; j < n + n; j++) {
                StdOut.printf("%8.3f ", a[i][j]);
            }
            StdOut.printf("| %8.3f\n", a[i][n + n]);
        }
        StdOut.println();
    }


    // check that Ax = b or yA = 0, yb != 0
    private boolean certifySolution(double[][] A, double[] b) {

        // check that Ax = b
        if (isFeasible()) {
            double[] x = primal();
            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int j = 0; j < n; j++) {
                    sum += A[i][j] * x[j];
                }
                if (Math.abs(sum - b[i]) > EPSILON) {
                    StdOut.println("not feasible");
                    StdOut.printf("b[%d] = %8.3f, sum = %8.3f\n", i, b[i], sum);
                    return false;
                }
            }
            return true;
        }

        // or that yA = 0, yb != 0
        else {
            double[] y = dual();
            for (int j = 0; j < n; j++) {
                double sum = 0.0;
                for (int i = 0; i < n; i++) {
                    sum += A[i][j] * y[i];
                }
                if (Math.abs(sum) > EPSILON) {
                    StdOut.println("invalid certificate of infeasibility");
                    StdOut.printf("sum = %8.3f\n", sum);
                    return false;
                }
            }
            double sum = 0.0;
            for (int i = 0; i < n; i++) {
                sum += y[i] * b[i];
            }
            if (Math.abs(sum) < EPSILON) {
                StdOut.println("invalid certificate of infeasibility");
                StdOut.printf("yb  = %8.3f\n", sum);
                return false;
            }
            return true;
        }
    }
}
