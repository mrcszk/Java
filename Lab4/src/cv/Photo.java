package cv;

import java.io.PrintStream;

public class Photo {
    Photo(String url){
        this.url =url;
    }
    private String url;
    public void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"420\" width=\"420\"/>\n",url);
    }
}