package kol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Word2 {
    String text;
    String regex;

    public Word2(String filename, String regex, Charset charset){
        text = read(filename,charset).toLowerCase();
        this.regex = regex;
    }

    String read(String name, Charset charset) {
        StringBuilder s = new StringBuilder();
        try( BufferedReader file = new BufferedReader(new InputStreamReader( new FileInputStream(name), charset))){
            for(;;){
                int c=file.read();
                if(c<0)break;
                s.append((char)c);
            }
            return s.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    void most() {
        String[] words = text.split(regex);
        Map<Integer, Integer> wordNum = new HashMap<Integer, Integer>();

        for (String word : words) {
            int wordLength = word.length();
            if (!wordNum.containsKey(wordLength)) {
                wordNum.put(wordLength, 1);
            } else {
                wordNum.put(wordLength, wordNum.get(wordLength) + 1);
            }
        }

        Integer length = 0;
        Integer count = 0;
        for (Integer word : wordNum.keySet()) {
            if (wordNum.get(word) > length) {
                length = word;
                count = wordNum.get(word);

            }
        }
        System.out.printf("Najczęstsza długość słowa \"%d\": - %d razy.", length, count);
    }
}
