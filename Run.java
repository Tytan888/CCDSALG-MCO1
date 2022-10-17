package main;

/**
 * This program serves to sort all suffixes of a given string
 * in lexicographic order, with the choice of one of two algorithms,
 * namely Selection Sort and Merge Sort.
 */
public class Run {

    /**
     * This method takes in an input in the form of a String,
     * then proceeds to sort all suffixes within said String
     * using the selection sort algorithm.
     * 
     * @param input   the string to be cut down into its suffixes
     * @param doPrint whether or not to print the result for empirical testing
     *                purposes
     */
    public static void selectionSort(String input, boolean doPrint) {

        // Get the several suffixes within the main inputted String.
        int nLength = input.length();
        String[] suffixes = new String[nLength];

        // While also generating an initial suffix array to be altered.
        int[] suffixArray = new int[nLength];

        for (int i = 0; i < nLength; i++) {
            suffixes[i] = input.substring(i, nLength);
            suffixArray[i] = i;
        }

        // Sort the suffixes using insertion sort.
        for (int i = 0; i < nLength - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nLength; j++) {
                if (suffixes[minIndex].compareTo(suffixes[j]) > 0)
                    minIndex = j;
            }
            String temp1 = suffixes[i];
            int temp2 = suffixArray[i];
            suffixes[i] = suffixes[minIndex];
            suffixArray[i] = suffixArray[minIndex];
            suffixes[minIndex] = temp1;
            suffixArray[minIndex] = temp2;
        }

        if (doPrint) {
            // Printing the suffixes to verify the corrected order.
            for (int i = 0; i < nLength; i++) {
                System.out.println(suffixes[i]);
            }

            System.out.println();

            // Printing the suffix array to verify the corrected order.
            for (int i = 0; i < nLength; i++) {
                System.out.print(suffixArray[i] + " ");
            }
        }
    }

    /**
     * This method merges two sorted subarrays of strings—as indicated by
     * the parameters nStart, nMid, and nStart—into sorted order.
     * 
     * @param suffixes    the list of suffixes to be sorted
     * @param suffixArray the suffix array to be altered along with suffixes[]
     * @param nStart      the starting index within suffixes of the first half of
     *                    suffixes to be sorted
     * @param nMid        the ending index within suffixes of the first half of
     *                    suffixes to be sorted
     * @param nEnd        the ending index within suffixes of the second half of
     *                    suffixes to be sorted
     */
    public static void merge(String suffixes[], int suffixArray[], int nStart, int nMid, int nEnd) {

        // Initialize needed varibables.
        String[] temp1 = new String[nEnd - nStart + 1];
        int[] temp2 = new int[nEnd - nStart + 1];
        int index1 = nStart, index2 = nMid + 1;

        // Execute merging algorithm.
        for (int i = 0; i < nEnd - nStart + 1; i++) {

            // Inserting from first half...
            if (index2 == nEnd + 1 || (index1 < nMid + 1 && suffixes[index1].compareTo(suffixes[index2]) < 0)) {
                temp1[i] = suffixes[index1];
                temp2[i] = suffixArray[index1];
                index1++;
            }
            // Inserting from second half...
            else {
                temp1[i] = suffixes[index2];
                temp2[i] = suffixArray[index2];
                index2++;
            }
        }

        // Save changes to suffixes[].
        for (int i = nStart, j = 0; i < nEnd + 1; i++, j++) {
            suffixes[i] = temp1[j];
            suffixArray[i] = temp2[j];
        }
    }

    /**
     * This method takes in an input in the form of a String,
     * then proceeds to sort all suffixes within said String
     * using the merge sort algorithm.
     * 
     * @param input   the string to be cut down into its suffixes
     * @param doPrint whether or not to print the result for empirical testing
     *                purposes
     */
    public static void mergeSort(String input, boolean doPrint) {

        // Get the several suffixes within the main inputted String.
        int nLength = input.length();
        String[] suffixes = new String[nLength];

        // While also generating an initial suffix array to be altered.
        int[] suffixArray = new int[nLength];

        for (int i = 0; i < nLength; i++) {
            suffixes[i] = input.substring(i, nLength);
            suffixArray[i] = i;
        }

        // Divide the suffixes.
        int nMid = nLength / 2;

        // Conquer the suffixes
        mergeSort(suffixes, suffixArray, 0, nMid);
        mergeSort(suffixes, suffixArray, nMid + 1, nLength - 1);

        // Combine the suffixes.
        merge(suffixes, suffixArray, 0, nMid, nLength - 1);

        if (doPrint) {
            // Printing the suffixes to verify the corrected order.
            for (int i = 0; i < nLength; i++) {
                System.out.println(suffixes[i]);
            }

            System.out.println();

            // Printing the suffix array to verify the corrected order.
            for (int i = 0; i < nLength; i++) {
                System.out.print(suffixArray[i] + " ");
            }
        }
    }

    /**
     * This method is an auxiliary method to be used by the
     * main mergeSort() method, as seen above.
     * 
     * @param suffixes    the list of suffixes to be sorted
     * @param suffixArray the suffix array to be altered along with suffixes[]
     * @param nStart      the starting index within suffixes of the suffixes to be
     *                    sorted
     * @param nEnd        the ending index within suffixes of the suffixes to be
     *                    sorted
     */
    public static void mergeSort(String suffixes[], int suffixArray[], int nStart, int nEnd) {
        if (nStart != nEnd) {

            // Divide the suffixes.
            int nMid = (nEnd + nStart) / 2;

            // Conquer the suffixes.
            mergeSort(suffixes, suffixArray, nStart, nMid);
            mergeSort(suffixes, suffixArray, nMid + 1, nEnd);

            // Combine the suffixes.
            merge(suffixes, suffixArray, nStart, nMid, nEnd);
        }
    }

    /**
     * This method takes in n in the form of an int,
     * then proceeds to generate a random String with
     * length n with only the characters 'a', 'c',
     * 't', and 'g'.
     * 
     * @param n length for the String to be generated
     */
    public static String generateRandomString(int n) {
        // Choose a character randomly from this String.
        String alphaString = "acgt";

        // Create StringBuffer size of AlphaNumericString.
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // Generate a random number between
            // 0 to AlphaNumericString variable length.
            int index = (int) (alphaString.length() * Math.random());

            // Add characters one by one at end of sb.
            sb.append(alphaString.charAt(index));
        }

        return sb.toString();
    }

    public static void main(String args[]) {

        // Loops len times represeting number of trials
        int len = 5;
        for (int i = 1; i <= len; i++) {
            
            // This represents the input size of string.
            int n = 131072;
            // This indicates whether the result should be printed or not.
            boolean doPrint = false;
    
            // This stores the string to be tested on.
            String input = generateRandomString(n);
    
            System.out.println("\nTrial " + i);
            System.out.println("\nSelection Sort...");
    
            // Starts the timer.
            long startSelectionTime = System.nanoTime();
    
            selectionSort(input, doPrint);
    
            // Ends timer and subtracts it with the start time to get the total time.
            long totalSelectionTime = System.nanoTime() - startSelectionTime;
            // Prints out time in nanoseconds.
            System.out.println(totalSelectionTime + " nanoseconds");
    
            System.out.println("\nMerge Sort...");
    
            // Starts the timer.
            long startMergeTime = System.nanoTime();
    
            mergeSort(input, doPrint);
    
            // Ends timer and subtracts it with the start time to get the total time.
            long totalMergeTime = System.nanoTime() - startMergeTime;
            // Prints out time in nanoseconds.
            System.out.println(totalMergeTime + " Nanoseconds");

        }

    }
}