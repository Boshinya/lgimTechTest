package com.landg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReplaceBannedWords {

    private static final int MAX_WIDTH_STDOUT_SIZE = 30;

    /**
     * @param bannedWords &
     * @param prose Files
     * @throws FileNotFoundException
     * This methods reads bannedwords and prose files and replace bannedwords and print the prose contents
     */
    public void replaceBannedWords(File bannedWords, File prose) throws FileNotFoundException {

        Scanner sc = new Scanner(bannedWords);
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        // read banned words one by one and insert it for later search
        while (sc.hasNext()) {
            Trie.insert(sc.next());
        }
        sc.close();
        sc = new Scanner(prose);
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int maxWidth=0;

        // read the prose file line by line and break into words then do a serach for banned words so that it can be replaced
        // before printing it on console.
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            for (String word : line) {
                if (word.matches("[A-Za-z]+")) {
                    if (Trie.search(word)) {
                        word= new String(new char[word.length()]).replace('\0', '*');
                    }
                }
                maxWidth = maxWidth + word.length()+1;
                if(maxWidth <= MAX_WIDTH_STDOUT_SIZE) {
                    System.out.print(word + " ");
                } else {
                    System.out.println();
                    System.out.print(word + " ");
                    maxWidth=word.length()+1;
                }
            }
        }
        sc.close();
    }

}
