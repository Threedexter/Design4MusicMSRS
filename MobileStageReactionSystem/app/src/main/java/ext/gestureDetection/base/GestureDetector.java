package ext.gestureDetection.base;

/**
 * Created by Rowan on 18/09/17.
 */

public class GestureDetector {
    //public static int maxErrors = 3;

    private Gesture gesture;

    private int index = 0;
    private int errCount = 0;

    public GestureDetector(Gesture gesture) {
        this.gesture = gesture;
    }

    public Gesture getGesture() {
        return gesture;
    }

    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    public boolean isOngoing() {
        return this.index > 0;
    }

    public boolean shouldTrigger() {
        return this.index == gesture.getMovement().size();
    }

    public void raiseIndex() {
        this.index++;
    }

    public int getIndex() {
        return this.index;
    }

    public void resetIndex() {
        this.index = 0;
    }

    public int getErrorCount() {
        return this.errCount;
    }

    public void resetErrors() {
        this.errCount = 0;
    }

    public void raiseErrorCount() {
        this.errCount++;
    }
}
