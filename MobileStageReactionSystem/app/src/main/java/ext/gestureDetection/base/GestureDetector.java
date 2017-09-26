package ext.gestureDetection.base;

/**
 * Created by Rowan on 18/09/17.
 */

public class GestureDetector {
    //public static int maxErrors = 3;

    private Gesture gesture;
    private FVectorLine gestureLine;

    public GestureDetector(Gesture gesture) {
        this.gesture = gesture;
        this.gestureLine = new FVectorLine(gesture.getMovement());
    }

    public Gesture getGesture() {
        return gesture;
    }

    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    // New Framework

    private boolean matched = false;

    public void matchGesture(FVector v) {
        gestureLine.setPointer(v);

        boolean outBounds = gestureLine.pointerOutOfBounds(gesture.getTolerance());
        matched = outBounds && gestureLine.pointerAtEnd();
        if (!outBounds) {
            // check if movement is prolonged
            outBounds = !gestureLine.towardsPeak(gesture.getTolerance());
        }

        if (outBounds) {
            gestureLine.resetPointer();
        }
    }

    public boolean matchedGesture() {
        return matched;
    }

    public void reset() {
        matched = false;
        gestureLine.resetPointer();
    }
}
