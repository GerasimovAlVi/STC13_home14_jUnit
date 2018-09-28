package ru.innopolis.generateFiles;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFiles {

    public void getFile(String path, int n, int size, String[] words, int probability) {
        try {
            writeInFiles(path,n,size,words,probability);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getWord() {
        Random randomWord = new Random();
        int sizeWord = randomWord.nextInt(15) + 1;
        StringBuilder stringBuilderWord = new StringBuilder(sizeWord);
        for (int i = 0; i < sizeWord; i++) {
            char word = (char) ('a' + randomWord.nextInt('z' - 'a'));
            stringBuilderWord.append(word);
        }
        return stringBuilderWord.toString();
    }

    public String getSentence() {
        Random randomSentence = new Random();
        int sizeSentence = randomSentence.nextInt(15) + 1;
        StringBuilder stringBuilderSentence = new StringBuilder(sizeSentence);
        for (int i = 0; i < sizeSentence; i++) {
            String Sentence = "";
            if (i == 0 && i == sizeSentence - 1) {
                Sentence = getWord().substring(0, 1).toUpperCase() + getWord().substring(1) + addEnd() + " ";
                stringBuilderSentence.append(Sentence);
            }else if(i == 0){
                Sentence = getWord().substring(0, 1).toUpperCase() + getWord().substring(1) + comma() + " ";
                stringBuilderSentence.append(Sentence);
            }else if(i == sizeSentence - 1) {
                Sentence = getWord() + addEnd() + " ";
                stringBuilderSentence.append(Sentence);
            }else {
                Sentence = getWord() + comma() + " ";
                stringBuilderSentence.append(Sentence);
            }
        }
        return stringBuilderSentence.toString();
    }

    public String getText(int size, String[] words, int probability) {
        StringBuilder stringBuilderText = new StringBuilder();
        int size2 = size;
        while (size2 != 0) {
            Random randomParagraph = new Random();
            int sizeParagraph = randomParagraph.nextInt(20) + 1;
            StringBuilder stringBuilderParagraph = new StringBuilder(sizeParagraph);
            if (size2 < sizeParagraph) {
                sizeParagraph = size2;
            }
            for (int i = 0; i < sizeParagraph; i++) {
                if (i == 0 && size2 != size) {
                    String Paragraph = "\r\n" + replaceWord(words,probability);
                    stringBuilderParagraph.append(Paragraph);
                }else{
                    String Paragraph = replaceWord(words,probability);
                    stringBuilderParagraph.append(Paragraph);
                }
            }
            stringBuilderText.append(stringBuilderParagraph);
            size2 -= sizeParagraph;
        }
        return stringBuilderText.toString();
    }

    public char addEnd() {
        Random random = new Random();
        char[] arrChar = new char[]{33, 46, 63};
        return arrChar[random.nextInt(3)];
    }

    public int randomReplaceWord(int probability) {
        Random random = new Random();
        return random.nextInt(probability);
    }

    public String replaceWord(String[] words, int probability) {
        String string1 = new String(getSentence());
        String string2 = new String();
        List<String> arrayList = new ArrayList<>();

        for (String i:string1.split(" ")) {
            arrayList.add(i);
        }
        String newString = words[new Random().nextInt(words.length)];
        if(randomReplaceWord(probability) == 0){
            int randomSize = new Random().nextInt(arrayList.size());
            if(randomSize == 0 && randomSize == arrayList.size() - 1){
                arrayList.set(randomSize, newString.substring(0, 1).toUpperCase() + newString.substring(1) + addEnd());
            }else if(randomSize == 0){
                arrayList.set(randomSize, newString.substring(0, 1).toUpperCase() + newString.substring(1));
            }else if(randomSize == arrayList.size() - 1){
                arrayList.set(randomSize, newString + addEnd());
            }else {
                arrayList.set(randomSize, newString );
            }
        }
        for(String j:arrayList){
            string2 += j + " ";
        }
        return string2;
    }

    public String comma() {
        int random = new Random().nextInt(10);
        String comma = "";
        if(random == 0){
            comma = ",";
        }
        return comma;
    }

    public void writeInFiles(String path, int n, int size, String[] words, int probability) throws FileNotFoundException {
        for(int i = 1; i <= n; i++) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(path + "file" + i + ".txt")) {
                byte[] buffer = getText(size,words,probability).getBytes();
                fileOutputStream.write(buffer, 0, buffer.length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw e;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
