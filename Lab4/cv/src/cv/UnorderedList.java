package cv;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> list = new ArrayList<>() ;
    public UnorderedList addItem(String content){
        ListItem li = new ListItem(content);
        list.add(li);
    }
}
