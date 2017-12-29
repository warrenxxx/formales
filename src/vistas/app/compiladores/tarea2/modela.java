package app.compiladores.tarea2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class modela {
    private final StringProperty key;
    private final StringProperty value;

    public modela(String key, String value) {
        this.key = new SimpleStringProperty(key);
        this.value = new SimpleStringProperty(value);
    }

    @Override
    public String toString() {
        return "modela{" +
                "key=" + key +
                ", value=" + value +
                '}';
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

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
