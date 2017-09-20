package ext.gestureDetection.base;

import java.util.List;

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

    // Old, rough Framework

    public void resetIndex() {
        this.index = 0;
    }

    // New Framework

    private boolean matched = false;

    public void matchExpectations(FVector v) {
        if (index == 0) {
            calculateNextExpectations(v);
        } else {
            int chunk = 5;

            if (index + chunk >= gesture.getMovement().size()) {
                matched = true;
                return;
            }

            // if v != in list of expectations with margin
            FVector vg = gesture.getMovement().get(index);
            FVector vg2 = gesture.getMovement().get(index + chunk);
            if (v.isBetween(vg, vg2, gesture.getTolerance())) {
                calculateNextExpectations(v);
            } else {
                reset();
            }
        }
    }

    public boolean matchedGesture() {
        return matched;
    }

    public void reset() {
        matched = false;
        resetIndex();
    }

    private void calculateNextExpectations(FVector v) {
        index += 3;
    }
}
