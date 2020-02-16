package help;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Shuffle {
    String text;
    String regex = "[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?]+";
    public Shuffle(String filename, Charset charset){
        this.text = readFile(filename,charset);
    }

    static String readFile(String name, Charset charset){
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

    void change() throws IOException {
        String[] words = text.split(regex);
        List<StringBuilder> words2= new ArrayList<StringBuilder>();
        PrintStream out = new PrintStream("text.txt", StandardCharsets.UTF_8);

        for (String word : words) {
            words2.add(shuffle(word));
        }

        for (StringBuilder word:words2){
            out.print(word + " ");
        }


    }

    StringBuilder shuffle(String word){
        StringBuilder word2 = new StringBuilder();
        List<Integer> used = new ArrayList<Integer>();
        int num;
        int length = word.length();
        word2.append(word.charAt(0));
        while(used.size() < length-2) {
            num = (int) (Math.random() * (length-2))+1 ;
            if (!used.contains(num)) {
                word2.append(word.charAt(num));
                used.add(num);
            }
        }
        word2.append(word.charAt(length-1));
        return word2;
    }
}
