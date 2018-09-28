package ru.innopolis.generateFiles;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class GenerateFilesTest {
    private static Logger LOGGER = Logger.getLogger(GenerateFilesTest.class);
    private GenerateFiles generateFiles;

    @BeforeEach
    void setUp() {
        generateFiles = new GenerateFiles();
    }

    @AfterEach
    void tearDown() {
    }

    @RepeatedTest(100)
    void getWord() {
        boolean bool = true;
        char[] arrrayChar = generateFiles.getWord().toCharArray();
        if(arrrayChar.length > 15 || arrrayChar.length == 0){
            bool = false;
        }
        assertTrue(bool);
    }

    @RepeatedTest(100)
    void getSentence() {
        boolean bool = true;
        String[] arrrayString = generateFiles.getSentence().split(" ");
        if(arrrayString.length > 15 || arrrayString.length == 0){
            bool = false;
        }
        assertTrue(bool);
    }

    @RepeatedTest(100)
    void getText() {
        boolean bool = true;
        String[] words = {"aaaaaaaaaa", "bbbbbbbbbb", "cccccccccc"};
        int size = 50;
        String[] arrrayString = generateFiles.getText(size, words, 1)
                .replaceAll("!|\\?", "\\.")
                .trim()
                .split("\\.");
        if(arrrayString.length != size){
            bool = false;
        }
        assertTrue(bool);
    }

    @RepeatedTest(10)
    void addEnd() {
        char ch = generateFiles.addEnd();
        assertEquals('.', ch);
    }

    @RepeatedTest(10)
    void randomReplaceWord() {
        int i = generateFiles.randomReplaceWord(3);
        assertEquals(1, i);
    }

    @RepeatedTest(10)
    void replaceWord() {
        boolean bool = false;
        String[] words = {"aaaaaaaaaa"};
        String[] arrrayString = generateFiles.replaceWord(words, 1)
                .replaceAll(",|!|\\?|\\.", "")
                .toLowerCase()
                .split(" ");
        for(String arr:arrrayString){
            if(arr.equals("aaaaaaaaaa")){
                bool = true;
            }
        }
        assertTrue(bool);
    }

    @RepeatedTest(100)
    void comma() {
        String string = generateFiles.comma();
        assertEquals(",", string);
    }

    @Test
    void writeInFilesWithException() {
        String[] words = {"aaaaaaaaaa", "bbbbbbbbbb", "cccccccccc"};
        assertThrows(FileNotFoundException.class, ()->generateFiles.writeInFiles("d1://", 1, 50, words, 1));
    }
}