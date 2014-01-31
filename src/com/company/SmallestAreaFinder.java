package com.company;

import java.io.*;
import java.util.NoSuchElementException;

/**
 * <p>This implementation is to support search of the minimum area which contains all the given words in the given
 * quantities in time proportional to the number of pairs given in the test case.
 * </p>
 */

public class SmallestAreaFinder {

    static private StreamTokenizer in;

    /**
     * Read next int from the StreamTokenizer
     *
     * @return integer
     * @throws IOException
     */
    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    /**
     * Read the test file and find the smallest area for each case.
     *
     * @param fileName
     * @throws IOException
     */
    public static void findSmallestArea(String fileName) throws IOException {

        FileInputStream input = new FileInputStream(fileName);
        /**
         * Using StreamTokenizer as Scanner seems to be slower for the input
         * (see: {@link http://acm.timus.ru/help.aspx?topic=java}
         */
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(input)));

        int numberOfTests = nextInt();

        for (int i = 0; i < numberOfTests; i++) {
            int minAreaSize = -1;

            int numberOfPairs = nextInt();
            int numberOfWords = nextInt();

            int[] resCounters = new int[numberOfWords]; //expected number of each word in the area

            for (int j = 0; j < numberOfWords; j++) {
                resCounters[j] = nextInt();

            }

            Words wordsArea = new Words(resCounters, numberOfWords);
            int end = 0;

            for (int start = 0; start < numberOfPairs; start++) { //start - the beginning of the area

                while (!wordsArea.isValid() && end < numberOfPairs) {
                /* if the area is not valid - move the end (add new pairs) until the area becomes valid
                */
                    int nextId = nextInt(); //do not store all the input data in array to save space; read one by one
                    int nextPos = nextInt();
                    System.out.println("# " + nextId + " " + nextPos);
                    wordsArea.addWord(nextId, nextPos);
                    end++;
                }

                if (wordsArea.isValid()) { //if the area is valid - need to check if it the of the minimum size
                    if (minAreaSize == -1 || wordsArea.getSize() < minAreaSize) {
                        minAreaSize = wordsArea.getSize();
                    }
                } else {
                    break;
                }
                wordsArea.removeWord(); //as start pointer moves to the right - delete the corresponding element from area
            }
            System.out.println("Case# " + i);
            System.out.println(minAreaSize);

        }

        input.close();
    }

    public static void main(String[] args) {
        String fileName;

        if (args.length > 0) {
            fileName = args[0];

            try {
                findSmallestArea(fileName);
            } catch (FileNotFoundException e) {
                System.err.println("File " + fileName + " not found");
            } catch (NoSuchElementException e) {
                System.err.println("Wrong file format");
            } catch (IOException e) {
                System.err.println("File input stream error");
            }


        }

    }
}

