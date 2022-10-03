package main;

/**
 * This program serves to sort all suffixes of a given a string in lexicographic
 * order,
 * with the choice of one of two algorithms, namely Selection Sort and Merge
 * Sort.
 */
public class Run {

    /**
     * This method takes in an input in the form of a String,
     * then proceeds to sort all suffixes within said String
     * using the selection sort algorithm.
     * 
     * @param input the string to be cut down into its suffixes
     */
    public static void selectionSort(String input) {

        // Get the several substrings within the main inputted String.
        int nLength = input.length();
        String[] substrings = new String[nLength];
        for (int i = 0; i < nLength; i++) {
            substrings[i] = input.substring(i, nLength);
        }

        // Sort the substrings using insertion sort.
        for (int i = 0; i < nLength - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nLength; j++) {
                if (substrings[minIndex].compareTo(substrings[j]) > 0)
                    minIndex = j;
            }
            String temp = substrings[i];
            substrings[i] = substrings[minIndex];
            substrings[minIndex] = temp;
        }

        // Printing the substrings to verify the corrected order.
        for (int i = 0; i < nLength; i++) {
            System.out.println(substrings[i]);
        }
    }

    /**
     * This method merges two sorted subarrays of strings—as indicated by
     * the parameters nStart, nMid, and nStart—into sorted order.
     * 
     * @param substrings the list of suffixes to be sorted
     * @param nStart     the starting index within substrings of the first half of
     *                   suffixes to be sorted
     * @param nMid       the ending index within substrings of the first half of
     *                   suffixes to be sorted
     * @param nEnd       the ending index within substrings of the second half of
     *                   suffixes to be sorted
     */
    public static void merge(String substrings[], int nStart, int nMid, int nEnd) {

        // Initialize needed varibables.
        String[] temp = new String[nEnd - nStart + 1];
        int index1 = nStart, index2 = nMid + 1;

        // Execute merging algorithm.
        for (int i = 0; i < nEnd - nStart + 1; i++) {

            // Inserting from first half...
            if (index2 == nEnd + 1 || (index1 < nMid + 1 && substrings[index1].compareTo(substrings[index2]) < 0)) {
                temp[i] = substrings[index1];
                index1++;
            }
            // Inserting from second half...
            else {
                temp[i] = substrings[index2];
                index2++;
            }
        }

        // Save changes to substrings[].
        for (int i = nStart, j = 0; i < nEnd + 1; i++, j++) {
            substrings[i] = temp[j];
        }
    }

    /**
     * This method takes in an input in the form of a String,
     * then proceeds to sort all suffixes within said String
     * using the merge sort algorithm.
     * 
     * @param input the string to be cut down into its suffixes
     */
    public static void mergeSort(String input) {

        // Get the several substrings within the main inputted String.
        int nLength = input.length();
        String[] substrings = new String[nLength];
        for (int i = 0; i < nLength; i++) {
            substrings[i] = input.substring(i, nLength);
        }

        // Divide the suffixes.
        int nMid = nLength / 2;

        // Conquer the suffixes
        mergeSort(substrings, 0, nMid);
        mergeSort(substrings, nMid + 1, nLength - 1);

        // Combine the suffixes.
        merge(substrings, 0, nMid, nLength - 1);

        // Printing the substrings to verify the corrected order.
        for (int i = 0; i < nLength; i++) {
            System.out.println(substrings[i]);
        }
    }

    /**
     * This method is an auxiliary method to be used by the
     * main mergeSort() method, as seen above.
     * 
     * @param substrings the list of suffixes to be sorted
     * @param nStart     the starting index within substrings of the suffixes to be
     *                   sorted
     * @param nEnd       the ending index within substrings of the suffixes to be
     *                   sorted
     */
    public static void mergeSort(String substrings[], int nStart, int nEnd) {
        if (nStart != nEnd) {

            // Divide the suffixes.
            int nMid = (nEnd + nStart) / 2;

            // Conquer the suffixes.
            mergeSort(substrings, nStart, nMid);
            mergeSort(substrings, nMid + 1, nEnd);

            // Combine the suffixes.
            merge(substrings, nStart, nMid, nEnd);
        }
    }

    public static void main(String args[]) {
        mergeSort("tgtgtgtgcaccg");
    }
}