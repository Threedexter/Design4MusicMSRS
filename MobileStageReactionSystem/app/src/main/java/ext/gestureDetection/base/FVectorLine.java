package ext.gestureDetection.base;

import com.back.Debugger;

import java.util.List;

/**
 * Created by Rowan on 21/09/17.
 */

public class FVectorLine {
    private List<FVector> vectors;

    private FVector pointer;
    private boolean lastPeak = false;
    private int index = 0;

    public FVectorLine(List<FVector> vectors) {
        this.vectors = vectors;
    }

    /**
     * @param pointer the new vector to be added and calculated
     */
    public void setPointer(FVector pointer) {
        this.pointer = pointer;
    }

    public boolean pointerOutOfBounds(float tolerance) {
        // get next peak (add all vectors)
        FVector peak = vectorToNextPeak(tolerance);
        if (peak == null) {
            Debugger.log("\t\tLAST FOUND");
            return lastPeak;
        }

        // calculate how much times bigger the line is A -> B and B -> A
        float sizeDifference = 1;

        if (pointer.sameDirectionsAs(peak, tolerance)) {
            // calculate how much difference they have
            sizeDifference = peak.content() / pointer.content();
            Debugger.log("\t\tStrength difference was " + sizeDifference);

            // If the new vector is longer and not around the pointer, it is out of bounds
            if (!pointer.isAround(peak, tolerance) && pointer.length() > peak.length()) { // big overhead
                Debugger.log("\t\tPointer is out of bounds -- too strong for peak");
                return true;
            }
        } else {
            // Not a box
            Debugger.log("\t\tPointer was not a boxform \r\n\t\t" + pointer.toString() + " \r\n \t\tVS\r\n\t\t" + peak.toString());
            return true;
        }

        return false;
    }

    public boolean pointerAtEnd() {
        return lastPeak;
    }

    private FVector vectorToNextPeak(float tolerance) {
        // get peak to remember
        FVector tempPeak = null;

        int count = 0;
        // add, add, add
        while (true) {
            count++;

            if (index + 1 == size()) {
                break;
            }
            FVector t = get();
            index++; // raise after get
            if (tempPeak == null) {
                tempPeak = t.copy();
            } else {
                tempPeak.add(t);
                if (count > 1 && isPeak(index, tolerance)) {
                    break;
                }
            }
        }

        // check if last peak
        lastPeak = (index + 1 == size());
        return tempPeak;
    }

    private boolean isPeak(int index, float tolerance) {
        // if last, then true
        if (index == size() - 1) {
            return true;
        }

        // check if vector before and after follow the same trend
        FVector trend = FVector.subtract(get(index), get(index - 1));
        if (trend.sameDirectionsAs(get(index + 1), tolerance)) {
            return false;
        }
        return true;
    }

    public int size() {
        return vectors.size();
    }

    public void resetPointer() {
        lastPeak = false;
        pointer = null;
        index = 0;
    }

    public FVector get(int index) {
        return vectors.get(index);
    }

    public FVector get() {
        return vectors.get(index);
    }
}
