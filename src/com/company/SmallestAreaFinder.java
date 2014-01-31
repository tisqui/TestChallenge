package com.company;

import java.io.*;
import java.util.NoSuchElementException;

/**
 * <p>This implementation is to support search of the minimum area which contains all the given words in the given
 * number in time proportional to the number of pairs given in the test case.
 * </p>
 *
 * <strong>Program either accepts input data from standard input stream or expects input file name as the first command
 * line argument</strong>
 */

public class SmallestAreaFinder {

    static private StreamTokenizer in;

    /**
     * Read next int from the File
     *
     * @return integer
     * @throws IOException
     */
    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    /**
     * Read the test file and find the smallest area for each case in the file.
     *
     * @throws IOException
     */
    public static void findSmallestArea(InputStream input) throws IOException {

        try {
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

                Words wordsArea = new Words(resCounters);
                int end = 0;

                for (int start = 0; start < numberOfPairs; start++) { //start - the beginning of the area

                    while (!wordsArea.isValid() && end < numberOfPairs) {
                /* if the area is not valid - move the end (add new words from the file) until the area becomes valid
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
                System.out.println("Case# " + (i + 1));
                System.out.println(minAreaSize);

            }
        } finally {
            input.close();
        }
    }

    public static void main(String[] args) {
        String fileName;
        InputStream input;

        if (args.length > 0) {
            fileName = args[0];
            try {
                input = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                System.err.println("File " + fileName + " not found");
                throw new IllegalStateException(e);
            }
        } else {
            input = System.in;
        }

        try {
            findSmallestArea(input);
        } catch (NoSuchElementException e) {
            System.err.println("Wrong input format");
        } catch (IOException e) {
            System.err.println("Input stream error");
            e.printStackTrace();
        }
    }
}

