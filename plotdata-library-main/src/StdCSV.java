import java.util.ArrayList;
import java.util.List;

/**
 * This java file takes a CSV file as an input with a flag to say whether the data
 * is in columns, and whether the UTF (random characters) signature is in the data.
 * After which, it will store the data with the headers as a "key" in a Symbol
 * Table, and the values of each header in a double array as the data type.
 */
public class StdCSV {

    // Create private symbol table of data
    private ST<String, double[]> data = new ST<String, double[]>();
    private String[] headers; // Array of headers
    private int n; // Size of data

    /**
     * Takes CSV input and column header and UTF encoding and returns symbol
     * table of headers with their associated double arrays
     *
     * @param filename      String name of file, ex: "data.csv"
     * @param headersInCols True or false of whether the data is in columns
     * @param UTFFix        True or false of whether CSV is UTF8 encoded
     */
    public StdCSV(String filename, boolean headersInCols, boolean UTFFix) {
        In scanner = new In(filename);
        // Code for when headers are in the columns
        if (headersInCols) {
            if (UTFFix) {
                // Remove UTF signature
                for (int i = 0; i < 1; i++) scanner.readChar();
            }
            String headerRow = scanner.readLine();
            headers = headerRow.split(",");
            int colNums = headers.length;
            StringBuilder lineData = new StringBuilder(scanner.readLine());

            // While scanner not empty, create a giant line of all values listed
            while (!scanner.isEmpty()) {
                String line = scanner.readLine();
                lineData.append("," + line);
            }

            // Split the lines by commas
            String[] toConvert = lineData.toString().split(",");

            // Data to be put into ST
            double[] csv = new double[toConvert.length];

            // Convert all the string values to doubles
            for (int i = 0; i < toConvert.length; i++) {
                csv[i] = Double.parseDouble(toConvert[i]);
            }

            // Just in case the columns have different sized arrays
            if (csv.length % colNums != 0)
                StdOut.print("Data sizes don't match, "
                                     + "cutting off trailing data.");

            // Create temporary arrays to use later
            for (String s : headers) {
                if (data.contains(s)) throw new RuntimeException(
                        "Duplicate header name " + s);
                double[] temp = new double[csv.length / colNums];
                data.put(s, temp);
            }

            // Create array based on column major arrays
            for (int i = 0; i < headers.length; i++) {
                double[] values = data.get(headers[i]);
                for (int j = i; j < csv.length; j += colNums) {
                    values[j / colNums] = csv[j];
                }
                data.put(headers[i], values);
            }
        }

        // Code for when the headers are in rows instead
        else {
            if (UTFFix) {
                // Remove UTF signature
                for (int i = 0; i < 1; i++) scanner.readChar();
            }
            List<String> headerList = new ArrayList<String>();
            // Same process as above, but easier because there is a header value
            while (!scanner.isEmpty()) {

                String row = scanner.readLine();
                String[] csv = row.split(",");
                String header = csv[0];
                headerList.add(csv[0]);

                // Array length less one
                double[] values = new double[csv.length - 1];
                if (data.contains(header)) throw new RuntimeException(
                        "Duplicate header name: " + header);

                // Create values to put into csv
                for (int i = 1; i < csv.length; i++) {
                    values[i - 1] = Double.parseDouble(csv[i]);
                }
                data.put(header, values);
            }
            headers = headerList.toArray(new String[headerList.size()]);
        }

        // Set the value of n
        n = data.get(headers[0]).length;
    }


    /**
     * Get the headers of StdCSV object
     *
     * @return Returns string array of headers
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     * Gets the double array associated with a header
     *
     * @param key String header key to get the data
     * @return Double array of the column/row of header of StdCSV object
     */
    public double[] getData(String key) {
        return data.get(key);
    }

    /**
     * Gets the amount of points associated with a header
     *
     * @return Returns integer amount of points under any such header
     */
    public int getN() {
        return n;
    }
}
