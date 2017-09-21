package ext.gestureDetection.base;

import android.util.Log;

import com.back.Debugger;

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
        if (other == this) return 0f;
        return (float) Math.sqrt(Math.pow(other.getX() - getX(), 2)
                + Math.pow(other.getY() - getY(), 2)
                + Math.pow(other.getZ() - getZ(), 2));
    }

    public static float distance(FVector a, FVector b) {
        return (Math.abs(a.distanceTo(b)));
    }

    public static FVector subtract(FVector a, FVector b) {
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

    public boolean isBetween(FVector one, FVector other, float tolerance) {
        boolean is = (isBetween(one.x, other.x, this.x, tolerance) && isBetween(one.y, other.y, this.y, tolerance) && isBetween(one.z, other.z, this.z, tolerance));
        Debugger.log("> isBetween", "is it? " + (is ? "Yes" : "No"));
        return is;
    }

    private boolean isBetween(float l, float v, float what, float tolerance) { // tolerance of 0 means on the line drawn between x1 and x2. Tolerance > 0 means an orb around the point of the line
        tolerance = Math.abs(tolerance); // positive only.
        if (l <= v) { // if first is the smallest
            // x should be bigger than first and smaller than biggest
            return what >= l - tolerance && what <= v + tolerance;
        } else  {
            // x should be bigger than second and smaller than biggest
            return what >= v - tolerance && what <= l + tolerance;
        }
    }

    public float sizesSmaller(FVector other) {
        return (1/ sizesBigger(other));
    }

    public float sizesBigger(FVector other) {
        //return divideBy(other).average();
        return content() / other.content();
    }

    public float average() {
        return (this.x + this.y + this.z) / 3;
    }

    public float content() {
        return (this.x * this.y * this.z);
    }

    public FVector divideBy(FVector other) {
        return new FVector(
                this.x / other.getX(),
                this.y / other.getY(),
                this.z / other.getZ()
        );
    }

    public float angleBetween(FVector other) {
        return 2.0f * (float) Math.atan(FVector.subtract(this, other).length() / FVector.add(this, other).length());
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
