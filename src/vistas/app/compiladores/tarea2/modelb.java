package app.compiladores.tarea2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class modelb {
    private final StringProperty key,first,follow;

    public modelb(String key, String first, String follow) {
        this.key = new SimpleStringProperty(key);
        this.first = new SimpleStringProperty(first);
        this.follow = new SimpleStringProperty(follow);
    }

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getFirst() {
        return first.get();
    }

    public StringProperty firstProperty() {
        return first;
    }

    public void setFirst(String first) {
        this.first.set(first);
    }

    public String getFollow() {
        return follow.get();
    }

    public StringProperty followProperty() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow.set(follow);
    }
}
