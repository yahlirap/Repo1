import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TestThreadCheckArray class demonstrates the use of multi-threading to perform operations
 * on an ArrayList of integers. The user provides an ArrayList of integers and a number, and two threads
 * check certain conditions on the ArrayList. The program outputs the results in a specific format.
 */
public class TestThreadCheckArray {
    
    /**
     * The main method runs the program, initializes shared data, starts two threads, and displays the results.
     * 
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            
            // Declare threads for the task
            Thread thread1, thread2;
            
            // Prompt user for ArrayList size
            System.out.println("Enter array size");
            int num = input.nextInt();
            
            // Initialize the ArrayList based on user input
            ArrayList<Integer> array = new ArrayList<>(num);
            System.out.println("Enter numbers for array");
            
            // Populate the ArrayList with user input
            for (int index = 0; index < num; index++) 
                array.add(input.nextInt());
            
            // Prompt for a number
            System.out.println("Enter number");
            int b = input.nextInt();
            
            // Initialize SharedData with the ArrayList and the number
            SharedData sd = new SharedData(array, b);
            
            // Create and start two threads that check the array
            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
            thread1.start();
            thread2.start();
            
            // Wait for both threads to finish
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // If the flag in SharedData is false, print "Sorry" and exit
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }
            
            // Print the solution and results in a formatted manner
            System.out.println("Solution for b : " + sd.getB() + ", n = " + sd.getArray().size());
            System.out.print("I:    ");
            
            // Print ArrayList indices
            for (int index = 0; index < sd.getArray().size(); index++)
                System.out.print(index + "    ");
            System.out.println();
            
            System.out.print("A:    ");
            
            // Print ArrayList elements with formatting based on number length
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                
                // Calculate spacing for formatting based on the number's length
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0)
                        break;
                }
                
                // Print spaces for formatting
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }

            System.out.println();
            System.out.print("C:    ");
            
            // Print the winArray (boolean values) with formatting
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");    
            }
        }
    }
}

