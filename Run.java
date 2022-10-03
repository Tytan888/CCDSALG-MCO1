package main;

public class Run {

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

    public static void merge(String substrings[], int nStart, int nMid, int nEnd) {
        String[] temp = new String[nEnd - nStart + 1];
        int index1 = nStart, index2 = nMid + 1;
        for (int i = 0; i < nEnd - nStart + 1; i++) {
            if (index2 == nEnd + 1 || (index1 < nMid + 1 && substrings[index1].compareTo(substrings[index2]) < 0)) {
                temp[i] = substrings[index1];
                index1++;
            } else {
                temp[i] = substrings[index2];
                index2++;
            }
        }

        for (int i = nStart, j = 0; i < nEnd + 1; i++, j++) {
            substrings[i] = temp[j];
        }
    }

    public static void mergeSort(String input) {

        // Get the several substrings within the main inputted String.
        int nLength = input.length();
        String[] substrings = new String[nLength];
        for (int i = 0; i < nLength; i++) {
            substrings[i] = input.substring(i, nLength);
        }

        // Divide
        int nMid = nLength / 2;

        // Conquer
        mergeSort(substrings, 0, nMid);
        mergeSort(substrings, nMid + 1, nLength - 1);

        // Combine
        merge(substrings, 0, nMid, nLength - 1);

        // Printing the substrings to verify the corrected order.
        for (int i = 0; i < nLength; i++) {
            System.out.println(substrings[i]);
        }
    }

    public static void mergeSort(String substrings[], int nStart, int nEnd) {
        if (nStart != nEnd) {
            // Divide
            int nMid = (nEnd + nStart) / 2;

            // Conquer
            mergeSort(substrings, nStart, nMid);
            mergeSort(substrings, nMid + 1, nEnd);

            // Combine
            merge(substrings, nStart, nMid, nEnd);
        }
    }

    public static void main(String args[]) {
        mergeSort("tgtgtgtgcaccg");
    }
}