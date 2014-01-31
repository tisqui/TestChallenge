package com.company;

import org.junit.Test;

/**
 * Created by squirrel on 1/31/14.
 */
public class SmallestAreaFinderTest {
    @Test
    public void testFindSmallestArea() throws Exception {
        String fileName = "resources/input2.txt";
        SmallestAreaFinder.findSmallestArea(fileName);

        fileName = "resources/input3.txt";
        SmallestAreaFinder.findSmallestArea(fileName);

        fileName = "resources/input4.txt";
        SmallestAreaFinder.findSmallestArea(fileName);


    }
}
