package ext.gestureDetection.base;

/**
 * Created by Rowan on 18/09/17.
 */

public interface Delegate<O> {
    void invoke(O obj);
}
