package ext.gestureDetection.base;

import java.sql.Timestamp;

/**
 * Created by Rowan on 19/09/17.
 */

public class TimedObject<T> {
    private T o;
    private long time;

    public TimedObject(T o) {
        setTimeNow();
        this.o = o;
    }

    public TimedObject() {
        setTimeNow();
    }

    public T getObject() {
        return o;
    }

    public void setObject(T o) {
        this.o = o;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTimeNow() {
        this.time = System.currentTimeMillis();
    }
}
