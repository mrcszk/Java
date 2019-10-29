package cv;

public class Paragraph {
    String content;
    Paragraph(String content){
        this.content = content;
    }
    public Paragraph setContent(String content) {
        this.content = content;
        return this;
    }
    public void writeHTML(){
    }
}
