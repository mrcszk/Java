package cv;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class UnorderedListTest {

    @Test
    public void addItem() {
        UnorderedList p = new UnorderedList();
        String s = "Zawartość";
        p.addItem(s);
        if(!p.list.get(0).content.equals(s)) fail("Złe podstawienie.");
    }

    @Test
    public void writeHTML()throws Exception {
        String content = "C";
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        new UnorderedList(content).writeHTML(ps);
        String result = null;
        // Pobierz jako String
        result = os.toString(StandardCharsets.UTF_8);

        System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains("</ul>"));
        assertTrue(result.contains(content));
    }
}