package cv;

import java.io.PrintStream;

public class Photo {
    Photo(String url){
        this.url =url;
    }
    String url;
    public void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}