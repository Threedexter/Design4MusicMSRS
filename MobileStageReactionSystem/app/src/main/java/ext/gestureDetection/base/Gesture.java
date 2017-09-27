package ext.gestureDetection.base;

import com.back.Debugger;

import java.util.List;

/**
 * Created by Rowan on 18/09/17.
 */

public class Gesture {
    private List<FVector> movement;
    private float tolerance;
    private String name;

    public Gesture () { }

    public Gesture(float tolerance, List<FVector> movement, String name) {
        this.movement = movement;
        this.tolerance = tolerance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FVector> getMovement() {
        return movement;
    }

    public void setMovement(List<FVector> movement) {
        this.movement = movement;
    }

    public float getTolerance() {
        return tolerance;
    }

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isDumb() {
        // check if really short
        // or containing barely any movements
        return isTooShort() || barelyMoved();
    }

    public boolean isTooShort() {
        return movement.size() < 4;
    }

    public boolean barelyMoved() {
        FVector last = null;
        float tol = 1f;

        boolean movedEnough = false;
        for (FVector v : movement) {
            if (last != null) {
                // compare
                FVector t = FVector.subtract(last, v);
                movedEnough = Math.abs(t.getX()) > tol ||  Math.abs(t.getY()) > tol || Math.abs(t.getZ()) > tol;
            }
            last = v; // set

            if(movedEnough) {
                return false; // not barely moved
            }
        }
        return true; // barely moved
    }

    @Override
    public String toString() {
        return getName();
    }
}
