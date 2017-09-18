package ext.gestureDetection.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rowan on 18/09/17.
 */

public class DelegateRegister<O> {
    private List<Delegate<O>> delList = new ArrayList<>();

    public DelegateRegister() {

    }

    public void attachDelegate(Delegate<O> delegate) {
        delList.add(delegate);
    }
    public void removeDelegate(Delegate<O> delegate) {
        delList.remove(delegate);
    }
    public void removeDelegate(int index) {
        delList.remove(index);
    }

    public void invokeAll(O obj) {
        for (Delegate<O> d : delList
             ) {
            d.invoke(obj);
        }
    }
}
