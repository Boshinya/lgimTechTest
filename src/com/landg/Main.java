package com.landg;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static ReplaceBannedWords replaceBannedWords = new ReplaceBannedWords();

    public static void main(String[] args) throws FileNotFoundException {

        String workingDirectory = System.getProperty("user.dir");

        File bannedWords = new File(workingDirectory+"/src/resources/" + "banned_words.txt");

        File prose = new File(workingDirectory+"/src/resources/" + "prose.txt");

        replaceBannedWords.replaceBannedWords(bannedWords,prose);
    }
}
