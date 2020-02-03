package kol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Word {
    String text;
    String regex;

    public Word(String filename, String regex, Charset charset){
        this.text = read(filename,charset).toLowerCase();
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
        Map<String, Integer> wordNum = new HashMap<String, Integer>();

        for (String word : words) {
            if (!wordNum.containsKey(word)) {
                wordNum.put(word, 1);
            } else {
                wordNum.put(word, wordNum.get(word) + 1);
            }
        }

        Integer max = 0;
        String most = "";
        for (String word : wordNum.keySet()) {
            if (wordNum.get(word) > max) {
                max = wordNum.get(word);
                most = word;
            }
        }
        System.out.printf("Najczęstsze słowo \"%s\": - %d razy.", most, max);
    }
}
