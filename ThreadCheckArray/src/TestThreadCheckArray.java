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
            
            
            Thread thread1, thread2;
            
            
            System.out.println("Enter array size");
            int num = input.nextInt();
            
            
            ArrayList<Integer> array = new ArrayList<>(num);
            System.out.println("Enter numbers for array");
            
            
            for (int index = 0; index < num; index++) 
                array.add(input.nextInt());
            
           
            System.out.println("Enter number");
            int b = input.nextInt();
            
            
            SharedData sd = new SharedData(array, b);
            
          
            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
            thread1.start();
            thread2.start();
            
        
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }
            
            
            System.out.println("Solution for b : " + sd.getB() + ", n = " + sd.getArray().size());
            System.out.print("I:    ");
            
          
            for (int index = 0; index < sd.getArray().size(); index++)
                System.out.print(index + "    ");
            System.out.println();
            
            System.out.print("A:    ");
            
   
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                
            
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0)
                        break;
                }
                
         
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }

            System.out.println();
            System.out.print("C:    ");
            
 
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");    
            }
        }
    }
}

