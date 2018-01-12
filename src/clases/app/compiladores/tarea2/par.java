package app.compiladores.tarea2;

import sun.plugin2.message.PrintAppletReplyMessage;

public class par {
    public par(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String key;
    public String value;

    @Override
    public String toString() {
        return key+":"+value    ;
    }
}
