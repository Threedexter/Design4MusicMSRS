package ext.gestureDetection.base;

/**
 * Created by Rowan on 18/09/17.
 */

public class FVector {
    private static FVector nullVector = new FVector(0, 0, 0);

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

    public float distanceTo(FVector other) {
        return (float) Math.sqrt(Math.pow(other.getX() - getX(), 2)
                + Math.pow(other.getY() - getY(), 2)
                + Math.pow(other.getZ() - getZ(), 2));
    }

    public static FVector substract(FVector a, FVector b) {
        return new FVector(a.getX() - b.getX(),
                a.getY() - b.getY(),
                a.getZ() - b.getZ());
    }

    public static FVector add(FVector a, FVector b) {
        return new FVector(a.getX() + b.getX(),
                a.getY() + b.getY(),
                a.getZ() + b.getZ());
    }

    public float length() {
        return distanceTo(nullVector);
    }

    public float angleBetween(FVector other) {
        return 2.0f * (float) Math.atan(FVector.substract(this, other).length() / FVector.add(this, other).length());
    }

    @Override
    public String toString() {
        return "FVector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
