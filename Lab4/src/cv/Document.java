package cv;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();
    Document (String title){
        this.title = title;
    }
    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section s = new Section(sectionTitle);
        sections.add(s);
        return s;
    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.print("<!DOCTYPE html>\n");
        out.print("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang = \"en\" lang = \"en\">\n");
        out.print("<head>\n");
        out.print("<meta charset=\"UTF-8\">\n");
        out.printf("<title>%s</title>\n", title);
        out.print("</head>\n");
        out.print("<body>\n");

        out.printf("<div>\n<h1 align=\"left\">%s</h1>\n", title);
        photo.writeHTML(out);
        out.print("</div>");

        for (Section section : sections) {
            section.writeHTML(out);
        }

        out.print("</body>\n");
        out.print("</html>");
    }
}
