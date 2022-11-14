// org.junit.jupiter:junit-jupiter:5.8.2

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StdCSVTest {

    double[] b1 = { 1, 3, 5, 7, 9, 11 };
    double[] b2 = { 4, 6, 8, 10, 12, 14 };
    double[] b3 = { 7, 9, 11, 13, 15, 17 };
    double[] b4 = { 10, 12, 14, 16, 18, 20 };
    double[] b5 = { 13, 15, 17, 19, 21, 23 };
    double[] b6 = { 16, 18, 20, 22, 24, 26 };
    double[] b7 = { 19, 21, 23, 25, 27, 29 };
    double[] b8 = { 22, 24, 26, 28, 30, 32 };
    double[] b9 = { 25, 27, 29, 31, 33, 35 };
    double[] b10 = { 28, 30, 32, 34, 36, 38 };
    String[] bheaders = {
            "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8",
            "b9", "b10"
    };
    double[] a1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    double[] a2 = { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    double[] a3 = { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };
    double[] a4 = { 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 };
    String[] aheaders = { "a1", "a2", "a3", "a4" };


    StdCSV csva1c = new StdCSV("test/a1c.csv", true, false);
    StdCSV csva1r = new StdCSV("test/a1r.csv", false, false);
    StdCSV csvb2c = new StdCSV("test/b2c.csv", true, false);
    StdCSV csvb2r = new StdCSV("test/b2r.csv", false, false);

    @Test
    void getHeaders() {
        String[] ah1 = csva1c.getHeaders();
        String[] ah2 = csva1r.getHeaders();
        String[] bh1 = csvb2c.getHeaders();
        String[] bh2 = csvb2r.getHeaders();
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(aheaders, ah1),
                () -> Assertions.assertArrayEquals(aheaders, ah2),
                () -> Assertions.assertArrayEquals(bheaders, bh1),
                () -> Assertions.assertArrayEquals(bheaders, bh2)
        );
    }

    @Test
    void getData() {
        double[] b1dc = csvb2c.getData(bheaders[0]);
        double[] b2dc = csvb2c.getData(bheaders[1]);
        double[] b3dc = csvb2c.getData(bheaders[2]);
        double[] b4dc = csvb2c.getData(bheaders[3]);
        double[] b5dc = csvb2c.getData(bheaders[4]);
        double[] b6dc = csvb2c.getData(bheaders[5]);
        double[] b7dc = csvb2c.getData(bheaders[6]);
        double[] b8dc = csvb2c.getData(bheaders[7]);
        double[] b9dc = csvb2c.getData(bheaders[8]);
        double[] b10dc = csvb2c.getData(bheaders[9]);
        double[] b1dr = csvb2r.getData(bheaders[0]);
        double[] b2dr = csvb2r.getData(bheaders[1]);
        double[] b3dr = csvb2r.getData(bheaders[2]);
        double[] b4dr = csvb2r.getData(bheaders[3]);
        double[] b5dr = csvb2r.getData(bheaders[4]);
        double[] b6dr = csvb2r.getData(bheaders[5]);
        double[] b7dr = csvb2r.getData(bheaders[6]);
        double[] b8dr = csvb2r.getData(bheaders[7]);
        double[] b9dr = csvb2r.getData(bheaders[8]);
        double[] b10dr = csvb2r.getData(bheaders[9]);
        double[] a1dc = csva1c.getData(aheaders[0]);
        double[] a2dc = csva1c.getData(aheaders[1]);
        double[] a3dc = csva1c.getData(aheaders[2]);
        double[] a4dc = csva1c.getData(aheaders[3]);
        double[] a1dr = csva1r.getData(aheaders[0]);
        double[] a2dr = csva1r.getData(aheaders[1]);
        double[] a3dr = csva1r.getData(aheaders[2]);
        double[] a4dr = csva1r.getData(aheaders[3]);

        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(a1, a1dc),
                () -> Assertions.assertArrayEquals(a2, a2dc),
                () -> Assertions.assertArrayEquals(a3, a3dc),
                () -> Assertions.assertArrayEquals(a4, a4dc),
                () -> Assertions.assertArrayEquals(a1, a1dr),
                () -> Assertions.assertArrayEquals(a2, a2dr),
                () -> Assertions.assertArrayEquals(a3, a3dr),
                () -> Assertions.assertArrayEquals(a4, a4dr),
                () -> Assertions.assertArrayEquals(b1, b1dc),
                () -> Assertions.assertArrayEquals(b2, b2dc),
                () -> Assertions.assertArrayEquals(b3, b3dc),
                () -> Assertions.assertArrayEquals(b4, b4dc),
                () -> Assertions.assertArrayEquals(b5, b5dc),
                () -> Assertions.assertArrayEquals(b6, b6dc),
                () -> Assertions.assertArrayEquals(b7, b7dc),
                () -> Assertions.assertArrayEquals(b8, b8dc),
                () -> Assertions.assertArrayEquals(b9, b9dc),
                () -> Assertions.assertArrayEquals(b10, b10dc),
                () -> Assertions.assertArrayEquals(b1, b1dr),
                () -> Assertions.assertArrayEquals(b2, b2dr),
                () -> Assertions.assertArrayEquals(b3, b3dr),
                () -> Assertions.assertArrayEquals(b4, b4dr),
                () -> Assertions.assertArrayEquals(b5, b5dr),
                () -> Assertions.assertArrayEquals(b6, b6dr),
                () -> Assertions.assertArrayEquals(b7, b7dr),
                () -> Assertions.assertArrayEquals(b8, b8dr),
                () -> Assertions.assertArrayEquals(b9, b9dr),
                () -> Assertions.assertArrayEquals(b10, b10dr)
        );

    }

    @Test
    void getN() {
        int a = 10;
        int b = 6;
        int a1 = csva1c.getN();
        int a2 = csva1r.getN();
        int b1 = csvb2c.getN();
        int b2 = csvb2r.getN();

        Assertions.assertAll(
                () -> Assertions.assertEquals(a, a1),
                () -> Assertions.assertEquals(a, a2),
                () -> Assertions.assertEquals(b, b1),
                () -> Assertions.assertEquals(b, b2)
        );
    }
}