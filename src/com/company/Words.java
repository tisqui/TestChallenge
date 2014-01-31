package com.company;

import java.util.LinkedList;

/**
 * Class contains the area of the document which is observed at the current moment.
 * This implementation keeps the area of the words in {@code area} and the status of the area (if it contains all
 * required words and these words appear in the area given number of times) is defined by {@code numberOfValidCounters}
 * - number of the counters which are equal to the given quantities.
 *
 * <p>This implementation should support {@code addWord} and {@code removeWord} in time proportional to the O(1).
 * </p>
 * Created by squirrel on 1/30/14.
 */
public class Words {
    private int[] currentCounters; //The counters of the quantity of the each word we are watching in the current area
    private int[] expCounters; //The expected number of all the watched words in the area
    private int numberOfValidCounters; //The number of the currentCounters which are >= the corresponding counters in expCounters
    private int wordsNum; //number of watched words

    private LinkedList<Pair> area; //"area" of words which is currently observed
    private int areaSize; //size of the observed area

    /**
     * Helper class - each element is the info we get for the word: id - the identifier of the word; location - the
     * location of the word in the origin text
     */
    private static class Pair {
        public int id;
        public int location;

        public Pair(int i, int l) {
            id = i;
            location = l;
        }

    }

    /**
     *
     * @param counters
     * @param num
     */
    public Words(int[] counters, int num) {
        wordsNum = num;
        numberOfValidCounters = 0;
        currentCounters = new int[wordsNum];
        expCounters = new int[wordsNum];
        System.arraycopy(counters, 0, expCounters, 0, wordsNum);

        area = new LinkedList<Pair>();
    }

    /**
     *
     * @param id
     * @param pos
     */
    public void addWord(int id, int pos) {
        Pair word = new Pair(id, pos);
        area.add(word);
        areaSize++;
        if (word.id <= wordsNum) {
            currentCounters[word.id - 1]++;
            if (currentCounters[word.id - 1] == expCounters[word.id - 1]) {
                numberOfValidCounters++;
            }
        }
    }

    /**
     * Remove the word from head. As we move the start we always remove the item from the head of the list.
     * When the item is removed the corresponding counter is decreased. If the counter value becomes smaller than the
     * expected counter value - the number of valid counters is decreased.
     */
    public void removeWord() {
        if (areaSize > 0) {
            Pair deleting = area.poll();
            areaSize--;
            if (deleting.id <= wordsNum) {
                currentCounters[deleting.id - 1]--;
                if (currentCounters[deleting.id - 1] == expCounters[deleting.id - 1] - 1) {
                    numberOfValidCounters--;
                }
            }
        }
    }

    /**
     * Check is the area is satisfying all the conditions - contains all the words in the quantities mentioned in resCounters
     * @return true if the area is valid (the number of valid counters = number of counters)
     */
    public boolean isValid() {
        return (numberOfValidCounters == expCounters.length);
    }

    /**
     *
     * @return
     */
    public int getSize() {
        if (areaSize > 0) {
            return (area.getLast().location - area.getFirst().location + 1);
        } else {
            return 0;
        }
    }

}
