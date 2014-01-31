package com.company;

import org.junit.Test;

import java.io.FileInputStream;

/**
 * Created by squirrel on 1/31/14.
 */
public class SmallestAreaFinderTest {
    @Test
    public void testFindSmallestArea() throws Exception {
        String fileName = "resources/input2.txt";

        SmallestAreaFinder.findSmallestArea(new FileInputStream(fileName));
    }
}
