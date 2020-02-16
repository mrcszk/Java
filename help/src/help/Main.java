package help;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws IOException {
	Shuffle s = new Shuffle("w-pustyni.txt", Charset.forName("cp1250"));
	s.change();
    }
}
