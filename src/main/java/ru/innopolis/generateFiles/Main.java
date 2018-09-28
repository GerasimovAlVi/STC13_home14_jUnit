package ru.innopolis.generateFiles;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

public class Main {
    static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        GenerateFiles generateFiles = new GenerateFiles();
        String[] words = new String[]{"aaaaaaaaaa", "bbbbbbbbbb", "cccccccccc"};
        generateFiles.getFile("d://", 5, 50, words, 1);
        LOGGER.info("Время работы программы: " + (System.currentTimeMillis() - startTime));
    }
}
