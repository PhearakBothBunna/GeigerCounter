import java.io.File;
import java.io.IOException;
import java.util.*;

    /*
     * Phearak Both Bunna
     * Programming Assignment : Radiation Sample
     *
     * This program reads the radiation data from a txt file and
     * get the date/time & find the max counts per minute then
     * print out the data that are within 5 counts of the max
     * to the console
     * It also prints out the histogram using * character
     * below the data
     *
     */
    public class RadiationSample {
        public static void main(String[]args) throws IOException {
            // Initialize the max value
            int max = 0;

            // Scan the text file and set the delimiter
            Scanner scan = new Scanner(new File("7_14_2019.txt"));
            String lineSeparator = System.getProperty("line.separator");
            scan.useDelimiter(lineSeparator);
            /*
             *  Create 2 array lists, 1 for String date/time and
             *  the other for Integer counts
             */
            ArrayList<String> dt = new ArrayList<String>();
            ArrayList<Integer> cpm = new ArrayList<Integer>();
            ArrayList<String> addition = new ArrayList<String>();
            // Create a while loop to scan for next line
            while (scan.hasNext()) {

                // Read through each line and split the content by comma
                String content = scan.next();
                String [] parsed = content.split(",");

                // Focus only on those lines with the valid data
                if (content.matches(".*Every Minute.*")) {

                    // Find the maximum counts by setting it equal to the highest counts
                    if(max < Integer.parseInt(parsed[2])) {
                        max = Integer.parseInt(parsed[2]);
                    }

                    /*
                     *  Parse the date/time and counts only if the counts
                     *  are within 5 counts of the max ( counts >= max - 5)
                     */
                    if(33 <=  Integer.parseInt(parsed[2])) {
                        dt.add(parsed[0]);
                        cpm.add(Integer.parseInt(parsed[2]));
                    }
                }
                else {
                    addition.remove(",");
                    addition.add(content);


                }
            }
            scan.close();
            // Display the maximum count and go to a new line
            System.out.println("The maximum counts per minute is " + max + "\n");

            // Display the heading of Date/time & counts
            System.out.println("Date & Time" + "\t " + "Counts per minute");

            // Display a straight line to separate between the heading and the data
            System.out.println("_____________________________________");

            // Print out an empty line
            System.out.println();


		/* for (String info : addition) {
			System.out.println(info);
		}
		*/

            // Iterate through the arryList of counts per minute
            ListIterator<Integer> counts = cpm.listIterator();

            // Separate the string date/time into different lines
            for (String date : dt) {
                // Format the string output to print in side by side
                String output = String.format("%-26s%-26s", date, counts.next());
                // Print out both the date/time and the counts per minute
                System.out.println(output);
            }
            // Print out the heading of the histogram graph
            System.out.println("\n\tHistogram graph (cpm)\n");

            for(int i = 0; i < cpm.size(); i++){
                // Label the counts per minute
                System.out.print(cpm.get(i) + ": ");

                // Print a * for each count per minute
                for(int j = 0; j < cpm.get(i); j++) {
                    System.out.print("*");
                }
                System.out.println();

            }
        }
    }
