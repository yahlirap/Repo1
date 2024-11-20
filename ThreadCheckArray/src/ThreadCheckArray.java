import java.util.ArrayList;

/**
 * The ThreadCheckArray class implements the Runnable interface to allow multi-threaded execution.
 * It checks whether there exists a subset of the ArrayList of integers that adds up to a target number.
 * Two threads work on the same SharedData object to perform this task concurrently.
 */
public class ThreadCheckArray implements Runnable {
    
    private boolean flag;
    private boolean[] winArray;
    private SharedData sd;
    private ArrayList<Integer> array;
    private int b;

    /**
     * Constructor that initializes the ThreadCheckArray object with shared data.
     * 
     * @param sd The SharedData object that contains the ArrayList of integers and the target number.
     */
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        synchronized (sd) {
            array = sd.getArray();  
            b = sd.getB();          
        }
        winArray = new boolean[array.size()];  
    }

    /**
     * A recursive method to check for subsets that add up to the target number (b).
     * 
     * @param n The index of the current element being considered.
     * @param b The remaining target sum to achieve.
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag())  
                return;
        }

        
        if (n == 1) {
            if (b == 0 || b == array.get(n - 1)) {  
                flag = true;
                synchronized (sd) {
                    sd.setFlag(true);  
                }
            }
            if (b == array.get(n - 1))  
                winArray[n - 1] = true;
            return;
        }

        
        rec(n - 1, b - array.get(n - 1));  
        if (flag)
            winArray[n - 1] = true;  
        synchronized (sd) {
            if (sd.getFlag())  
                return;
        }
        rec(n - 1, b);  
    }

    /**
     * The run method is called when the thread starts. It controls the flow of the algorithm.
     */
    public void run() {
        if (array.size() != 1) {  
            if (Thread.currentThread().getName().equals("thread1")) {
                rec(array.size() - 1, b - array.get(array.size() - 1)); 
            } else {
                rec(array.size() - 1, b);  
            }
        }

        
        if (array.size() == 1 && b == array.get(0) && !flag) {
            winArray[0] = true;  
            flag = true;
            synchronized (sd) {
                sd.setFlag(true);  
            }
        }

        
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.size() - 1] = true;  
            synchronized (sd) {
                sd.setWinArray(winArray);  
            }
        }
    }
}
