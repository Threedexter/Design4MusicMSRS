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
        if (gestureLine.hasMovement(v)) {

            gestureLine.setPointer(v);

            boolean prolongedMovement = false;
            // Check if movement was prolonged
            if (gestureLine.hasPeak() && gestureLine.towardsPeak(gesture.getTolerance())) {
                prolongedMovement = true;
            }

            boolean outBounds = gestureLine.pointerOutOfBounds(gesture.getTolerance());
            matched = outBounds && gestureLine.pointerAtEnd();

            if (outBounds && !prolongedMovement) {
                gestureLine.resetPointer();
            }
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
