package cv;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class CV {

    public static void main(String[] args) {
        Document cv = new Document("Jan Kowalski - CV");
        cv.setPhoto("doge.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Śnieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("2018- AGH");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        try {
            cv.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
