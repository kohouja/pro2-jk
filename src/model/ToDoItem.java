package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoItem implements Serializable {

    private String content;
    private String date;
    private boolean done;

    public ToDoItem(String content){
        this.content = content;
        this.date = new SimpleDateFormat("d.M.yyyy").format(new Date());
        this.done = false;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
