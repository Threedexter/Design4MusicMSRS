package ext.gestureDetection.base;

import java.util.List;

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
        matched = gestureLine.pointerOutOfBounds(gesture.getTolerance()) && gestureLine.pointerAtEnd();

        if (gestureLine.pointerOutOfBounds(gesture.getTolerance())) {
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
