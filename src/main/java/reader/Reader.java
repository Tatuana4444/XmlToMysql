package reader;

import java.util.Scanner;

/**
 * Class that help to read from console
 */
public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    private Reader() {
    }

    /**
     * Read integer from console
     * @return integer value
     */
    public static int nextInt() {

        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return 0;

    }

    /**
     * Read string from console
     * @return string value
     */
    public static String nextString() {

        String value;
        if (scanner.hasNext()) {
            value = scanner.next();
            return value;
        }
        return null;
    }


}
