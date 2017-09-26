package ext.gestureDetection.base;

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

    @Override
    public String toString() {
        return getName();
    }
}
