import java.util.ArrayList;

/**
 * The SharedData class is used to manage shared data, including an array of ArrayLists, 
 * a winArray to store boolean flags, and a flag variable to track a state.
 */
public class SharedData 
{
    /** An array of ArrayLists of integers. */
    private ArrayList<Integer>[] array;

    /** An array of boolean values indicating win conditions. */
    private boolean[] winArray;

    /** A flag variable to track a particular state. */
    private boolean flag;

    /** A constant integer used for specific purposes (e.g., size, threshold). */
    private final int b;

    /**
     * Constructor to initialize the SharedData object with an ArrayList and an integer.
     * 
     * @param array The ArrayList of integers to initialize the array.
     * @param b A threshold or constant value used in the object.
     */
    public SharedData(ArrayList<Integer> array, int b) {
        this.array = new ArrayList[] {array}; // Assuming a single ArrayList is passed in, wrapped in an array.
        this.b = b;
    }

    /**
     * Gets the win array.
     *
     * @return The array of boolean values indicating win conditions.
     */
    public boolean[] getWinArray() 
    {
        return winArray;
    }

    /**
     * Sets the win array.
     * 
     * @param winArray The array of boolean values indicating win conditions.
     */
    public void setWinArray(boolean[] winArray) 
    {
        this.winArray = winArray;
    }

    /**
     * Gets the ArrayList from the shared data.
     *
     * @return The first ArrayList of integers in the shared data.
     */
    public ArrayList<Integer> getArray() 
    {
        return array[0]; // Returning the first ArrayList from the array.
    }

    /**
     * Gets the value of the constant 'b'.
     * 
     * @return The value of the constant integer 'b'.
     */
    public int getB() 
    {
        return b;
    }

    /**
     * Gets the current state of the flag.
     *
     * @return The current state of the flag.
     */
    public boolean getFlag() 
    {
        return flag;
    }

    /**
     * Sets the flag to the given value.
     * 
     * @param flag The value to set the flag to.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

