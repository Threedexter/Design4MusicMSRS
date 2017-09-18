package ext.gestureDetection.base;

/**
 * Created by Rowan on 18/09/17.
 */

public class FVector {
    private float x;
    private float y;
    private float z;

    public FVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FVector() {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
