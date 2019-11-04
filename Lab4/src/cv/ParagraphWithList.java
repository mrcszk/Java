package cv;

import java.io.PrintStream;


public class ParagraphWithList extends Paragraph{
    private UnorderedList ul = new UnorderedList();

    public ParagraphWithList(String content) {
        super(content);
    }

    public ParagraphWithList() {
        super("Paragraph");
    }

    public ParagraphWithList setContent(String newContent) {
        super.setContent(newContent);
        return this;
    }

    @Override
    void writeHTML(PrintStream out) {
        super.writeHTML(out);
        ul.writeHTML(out);
    }

    public ParagraphWithList addListItem(String item) {
        ul.addItem(item);
        return this;
    }
}
