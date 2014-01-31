package com.company;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by squirrel on 1/31/14.
 */
public class WordsTest {
    @org.junit.Test
    public void testAddWord() throws Exception {
        int num = 2;
        int[] ctrs = {1, 1};

        Words wordsTest = new Words(ctrs, num);
        wordsTest.addWord(1,1);
        wordsTest.addWord(2,2);

        Assert.assertTrue("Area size is wrong", wordsTest.getSize()==2);
    }

    @org.junit.Test
    public void testRemoveWord() throws Exception {
        int num = 1;
        int[] ctrs = {2};

        Words wordsTest = new Words(ctrs, num);
        wordsTest.addWord(1,1);
        wordsTest.addWord(1,2);

        wordsTest.removeWord();
        Assert.assertTrue("Area size is wrong", wordsTest.getSize() == 1);
        Assert.assertFalse("Area is valid, but should not", wordsTest.isValid());

        wordsTest.removeWord();
        Assert.assertTrue("Area is not empty", wordsTest.getSize()==0);
        Assert.assertFalse("Area is valid, but should not", wordsTest.isValid());

    }

    @org.junit.Test
    public void testIsValidTrue() throws Exception {
        int num = 2;
        int[] ctrs = {2, 1};

        Words wordsTest = new Words(ctrs, num);
        wordsTest.addWord(1,1);
        wordsTest.addWord(2,2);
        wordsTest.addWord(1,3);

        Assert.assertTrue("Area should is not valid, as planned", wordsTest.isValid());

    }

    @org.junit.Test
    public void testIsValidFalse() throws Exception {
        int num = 3;
        int[] ctrs = {1, 1, 1};

        Words wordsTest = new Words(ctrs, num);
        wordsTest.addWord(1,1);
        wordsTest.addWord(2,2);
        wordsTest.addWord(1,6);

        Assert.assertFalse("Area should is valid, but should be not valid", wordsTest.isValid());

    }

    @org.junit.Test
    public void testGetSize() throws Exception {
        int num = 1;
        int[] ctrs = {2};

        Words wordsTest = new Words(ctrs, num);
        wordsTest.addWord(1,1);
        Assert.assertTrue("Area size is wrong", wordsTest.getSize()==1);
        wordsTest.addWord(1,2);
        Assert.assertTrue("Area size is wrong", wordsTest.getSize()==2);
        wordsTest.addWord(1,3);
        Assert.assertTrue("Area size is wrong", wordsTest.getSize()==3);


    }
}
