package cv;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ParagraphTest {

    @Test
    public void setContent() {
        Paragraph p = new Paragraph();
        p.setContent("Zawartość");
        if(!p.content.equals("Zawartość")) fail("Złe podstawienie.");
    }

    @Test
    public void writeHTML() throws Exception {
        String content = "\"2018- AGH\"";
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        new Paragraph(content).writeHTML(ps);
        String result = null;
        // Pobierz jako String
        result = os.toString(StandardCharsets.UTF_8);

        System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains(content));

    }
}