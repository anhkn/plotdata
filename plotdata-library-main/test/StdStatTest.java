// org.junit.jupiter:junit-jupiter:5.8.2

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class StdStatTest {

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    double y1[] = {
            10, 19, 35, 44, 46, 49, 50,
            55, 64, 70, 84, 86, 88, 92, 97
    };
    double y2[] = {
            126, 335, 416, 669, 860, 1853, 2075, 2316,
            2832, 2924, 2936, 3072, 3281, 3490, 3543
    };

    double y3[] = {
            202, 472, 615, 786, 1110, 2236, 2475, 2665, 2986,
            3041, 3170, 3964, 4272, 5231, 5576,
            5944, 6026, 6138, 6330, 6511, 6904, 7252, 7316, 7361,
            7537, 7539, 8058, 8757, 11338,
            11472, 12173, 12458, 12483,
            13112, 13625, 14190, 14416, 14694,
            15433, 16285, 16365, 17520,
            17528, 17659, 17786, 18054, 18241,
            18420, 18670, 18913
    };

    double[] x1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
    double[] x2 = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49
    };


    // mean = 5, stddev = 2.5
    double[] stats1 = {
            14.11, 2.9, 5.4, 9.11, 1.77, 1.15, 8.74, 4.29, 9.06, 2.76, 4.52,
            7.75, 6.79, 4.83, 4.16, 11.66, 9.28, 6.85, 6.71, 3.85, 2.33,
            4.69, 5.33, 5.36, 4.59
    };

    // mean = 1, stddev = 0.5
    double[] stats2 = {
            0.58, 0.07, 0.6, 1.5, 0.92, 1.75, 1.28, 0.94, 0.36, 1.29, 0.96,
            1.61, 0.43, 1.24, 1.35, 0.35, 0.83, 1.62, 0.11, 1.49, 1.39, 0.96,
            0.65, 1.34, -0.09
    };

    double[] stats3 = { 0, 0, 0, 0, 0, 0, 0, 0, 1.0, -999 };

    @Test
    void max() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(14.11,
                                              StdStat.max(stats1)),
                () -> Assertions.assertEquals(1.75, StdStat.max(stats2)),
                () -> Assertions.assertEquals(1.0, StdStat.max(stats3))
        );
    }

    @Test
    void testMax() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(9.11, StdStat.max(stats1,
                                                                1, 7)),
                () -> Assertions.assertEquals(1.50, StdStat.max(stats2,
                                                                0, 5)),
                () -> Assertions.assertEquals(1.0, StdStat.max(stats3,
                                                               3, 9))
        );
    }


    @Test
    void min() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(1.15,
                                              StdStat.min(stats1)),
                () -> Assertions.assertEquals(-0.09,
                                              StdStat.min(stats2)),
                () -> Assertions.assertEquals(-999, StdStat.min(stats3))
        );
    }

    @Test
    void testMin() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(4.59, StdStat.min(stats1,
                                                                21, 25)),
                () -> Assertions.assertEquals(0.07, StdStat.min(stats2,
                                                                0, 3)),
                () -> Assertions.assertEquals(0, StdStat.min(stats3,
                                                             0, 5))
        );
    }

    @Test
    void mean() {
        double mean1 = round(StdStat.mean(stats1), 4);
        double mean2 = round(StdStat.mean(stats2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        5.9196, mean1),
                () -> Assertions.assertEquals(0.9412, mean2)
        );
    }

    @Test
    void stdDevp() {
        double s1 = round(StdStat.stdDevp(stats1), 4);
        double s2 = round(StdStat.stdDevp(stats2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(3.0612, s1),
                () -> Assertions.assertEquals(0.5229, s2)
        );
    }

    @Test
    void variancep() {
        double v1 = round(StdStat.variancep(stats1), 4);
        double v2 = round(StdStat.variancep(stats2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(9.3707, v1),
                () -> Assertions.assertEquals(0.2734, v2)
        );
    }

    @Test
    void testMean() {
        double mean1 = round(StdStat.mean(stats1, 0, 5), 3);
        double mean2 = round(StdStat.mean(stats2, 0, 5), 3);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6.6580, mean1),
                () -> Assertions.assertEquals(0.7340, mean2)
        );
    }

    @Test
    void testStdDevp() {
        double s1 = round(StdStat.stdDevp(stats1, 0, 5), 4);
        double s2 = round(StdStat.stdDevp(stats2, 0, 5), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(4.4976, s1),
                () -> Assertions.assertEquals(0.4699, s2)
        );
    }

    @Test
    void testVariancep() {
        double v1 = round(StdStat.variancep(stats1, 0, 5), 4);
        double v2 = round(StdStat.variancep(stats2, 0, 5), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(20.2285, v1),
                () -> Assertions.assertEquals(0.2208, v2)
        );
    }

    @Test
    void stdDev() {
        double s1 = round(StdStat.stdDev(stats1), 4);
        double s2 = round(StdStat.stdDev(stats2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(3.1243, s1),
                () -> Assertions.assertEquals(0.5337, s2)
        );
    }

    @Test
    void variance() {
        double v1 = round(StdStat.variance(stats1), 4);
        double v2 = round(StdStat.variance(stats2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(9.7611, v1),
                () -> Assertions.assertEquals(0.2848, v2)
        );
    }

    @Test
    void zScore() {
        double z1_1 = round(StdStat.zScore(3, stats1), 4);
        double z1_2 = round(StdStat.zScore(3, 5.9196,
                                           3.061157), 4);
        double z2_1 = round(StdStat.zScore(1, stats2), 4);
        double z2_2 = round(StdStat.zScore(1, 0.9412,
                                           0.522917), 4);

        Assertions.assertAll(
                () -> Assertions.assertEquals(-0.9538, z1_1),
                () -> Assertions.assertEquals(-0.9538, z1_2),
                () -> Assertions.assertEquals(0.1124, z2_1),
                () -> Assertions.assertEquals(0.1124, z2_2)
        );
    }

    @Test
    void testStdDev() {
        double s1 = round(StdStat.stdDev(stats1, 0, 5), 4);
        double s2 = round(StdStat.stdDev(stats2, 0, 5), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(5.0285, s1),
                () -> Assertions.assertEquals(0.5253, s2)
        );
    }

    @Test
    void testVariance() {
        double v1 = round(StdStat.variance(stats1, 0, 5), 4);
        double v2 = round(StdStat.variance(stats2, 0, 5), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(25.2856, v1),
                () -> Assertions.assertEquals(0.2760, v2)
        );
    }

    @Test
    void rSqr() {
        double r1 = round(StdStat.rSqr(x1, y1), 4);
        double r2 = round(StdStat.rSqr(x1, y2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0.9684, r1),
                () -> Assertions.assertEquals(0.9454, r2)
        );
    }

    @Test
    void corCoef() {
        double r1 = round(StdStat.corCoef(x1, y1), 4);
        double r2 = round(StdStat.corCoef(x1, y2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0.9841, r1),
                () -> Assertions.assertEquals(0.9723, r2)
        );
    }


    @Test
    void linRegInt() {
        double i1 = round(StdStat.linRegInt(x1, y1), 4);
        double i2 = round(StdStat.linRegInt(x1, y2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(18.0167, i1),
                () -> Assertions.assertEquals(150.7083, i2)
        );
    }

    @Test
    void linRegSlope() {
        double s1 = round(StdStat.linRegSlope(x1, y1), 4);
        double s2 = round(StdStat.linRegSlope(x1, y2), 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(5.8929, s1),
                () -> Assertions.assertEquals(271.1179, s2)
        );
    }


    @Test
    void expVal() {
        double[] x1 = { 2, 4, 6, 8 };
        double[] x2 = { 12, 15, 19, 23 };
        double[] p = { 0.1, 0.2, 0.3, 0.4 };
        double e1 = StdStat.expVal(x1, p);
        double e2 = StdStat.expVal(x2, p);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, e1),
                () -> Assertions.assertEquals(19.1, e2)
        );
    }

    @Test
    void polyMatrix() {
        double[] ys1 = {
                5.5, 8.32, 11.78, 15.88, 20.62, 26, 32.02, 38.68, 45.98,
                53.92, 62.5, 71.72, 81.58, 92.08, 103.22
        };
        double[] ys2 = {
                1, 3.22, 11.88, 32.98, 72.52, 136.5, 230.92, 361.78, 535.08, 756.82,
                1033, 1369.62,
                1772.68, 2248.18, 2802.12
        };
        double[] s1 = StdStat.polyMatrix(x1, ys1, 2);
        double[] s2 = StdStat.polyMatrix(x1, ys2, 3);
        double[] sn1 = new double[s1.length];
        double[] sn2 = new double[s2.length];
        for (int i = 0; i < s1.length; i++) {
            sn1[i] = round(s1[i], 2);
        }
        for (int i = 0; i < s2.length; i++) {
            sn2[i] = round(s2[i], 2);
        }
        double[] b1 = { 5.5, 2.5, 0.32 };
        double[] b2 = { 1, 1, 0.22, 1 };

        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(b1, sn1),
                () -> Assertions.assertArrayEquals(b2, sn2)
        );
    }

    @Test
    void predict() {
        double[] ys1 = {
                5.5, 8.32, 11.78, 15.88, 20.62, 26, 32.02, 38.68, 45.98,
                53.92, 62.5, 71.72, 81.58, 92.08, 103.22
        };
        double[] ys2 = {
                1, 3.22, 11.88, 32.98, 72.52, 136.5, 230.92, 361.78, 535.08, 756.82,
                1033, 1369.62,
                1772.68, 2248.18, 2802.12
        };
        double[] s1 = StdStat.polyMatrix(x1, ys1, 2);
        double[] s2 = StdStat.polyMatrix(x1, ys2, 3);
        double[] sn1 = new double[s1.length];
        double[] sn2 = new double[s2.length];
        for (int i = 0; i < s1.length; i++) {
            sn1[i] = round(s1[i], 2);
        }
        for (int i = 0; i < s2.length; i++) {
            sn2[i] = round(s2[i], 2);
        }

        double p1_1 = round(StdStat.predict(2, sn1), 2);
        double p1_2 = round(StdStat.predict(3, sn1), 2);
        double p1_3 = round(StdStat.predict(4, sn1), 2);
        double p2_1 = round(StdStat.predict(2, sn2), 2);
        double p2_2 = round(StdStat.predict(3, sn2), 2);
        double p2_3 = round(StdStat.predict(4, sn2), 2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(11.78, p1_1),
                () -> Assertions.assertEquals(15.88, p1_2),
                () -> Assertions.assertEquals(20.62, p1_3),
                () -> Assertions.assertEquals(11.88, p2_1),
                () -> Assertions.assertEquals(32.98, p2_2),
                () -> Assertions.assertEquals(72.52, p2_3)
        );
    }

    @Test
    void linSpace() {
        double[] a1 = StdStat.linSpace(0, 9, 10);
        double[] b1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        double[] a2 = StdStat.linSpace(0, 5, 5);
        double[] b2 = { 0, 1.25, 2.5, 3.75, 5 };
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(a1, b1),
                () -> Assertions.assertArrayEquals(a2, b2)
        );
    }

    @Test
    void testLinSpace() {
        double[] a1 = StdStat.linSpace(0, 5, 10, false);
        double[] b1 = { 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5 };
        double[] a2 = StdStat.linSpace(5, 11, 5, false);
        double[] b2 = { 5, 6.2, 7.4, 8.6, 9.8 };
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(a1, b1),
                () -> Assertions.assertArrayEquals(a2, b2)
        );
    }
}