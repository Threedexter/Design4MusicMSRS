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

    public void subtract(FVector other) {
        FVector.add(other, this);
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
    }

    public void add(FVector other) {
        FVector.add(other, this);
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public boolean sameDirectionsAs(FVector directions, float tolerance) {
        return sameDirectionAs(this.x, directions.x, tolerance) &&
                sameDirectionAs(this.y, directions.y, tolerance) &&
                sameDirectionAs(this.z, directions.z, tolerance);
    }

    private static boolean sameDirectionAs(float n1, float n2, float tolerance) {
        if (n1 >= 0 && n2 >= 0 || n1 < 0 && n2 < 0) {
            // if both signs are the same
            return true;
        } else {
            // calculate with tolerance
            return ((n1 > 0 && n1 - tolerance <= 0) ||
                    (n2 > 0 && n2 - tolerance <= 0) ||
                    (n1 < 0 && n1 + tolerance >= 0) ||
                    (n2 < 0 && n2 + tolerance >= 0));
        }
    }

    public float length() {
        return distanceTo(nullVector);
    }

    public boolean isBetween(FVector one, FVector other, float tolerance) {
        return (isBetween(one.x, other.x, this.x, tolerance) && isBetween(one.y, other.y, this.y, tolerance) && isBetween(one.z, other.z, this.z, tolerance));
    }

    private boolean isBetween(float l, float v, float what, float tolerance) { // tolerance of 0 means on the line drawn between x1 and x2. Tolerance > 0 means an orb around the point of the line
        tolerance = Math.abs(tolerance); // positive only.
        if (l <= v) { // if first is the smallest
            // x should be bigger than first and smaller than biggest
            return what >= l - tolerance && what <= v + tolerance;
        } else {
            // x should be bigger than second and smaller than biggest
            return what >= v - tolerance && what <= l + tolerance;
        }
    }

    public float sizesSmaller(FVector other) {
        return (1 / sizesBigger(other));
    }

    public float sizesBigger(FVector other) {
        return this.content() / other.content();
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

    public FVector copy() {
        return new FVector(this.x, this.y, this.z);
    }

    public boolean isSizedVersionOf(FVector other, float tolerance) {
        FVector t;
        if (other.content() > this.content()) {
            t = other.divideBy(this);
        } else {
            t = this.divideBy(other);
        }
        // Get size differences
        float x = t.x, y = t.y, z = t.z;

        // get lowest
        float a = t.lowestValue();
        x = x / a;
        y = y / a;
        z = z / a;

        x = 1 - (1 / x);
        y = 1 - (1 / y);
        z = 1 - (1 / z);

        // Size differences should be in range of each other
        return (isAround(x, y, tolerance) && isAround(x, z, tolerance) &&
                isAround(y, z, tolerance));
    }

    public boolean isAround(FVector other, float tolerance) {
        return (isAround(other.sizesBigger(this), 1, tolerance) && isSizedVersionOf(other, tolerance));
    }

    public boolean isEnlargementOf(FVector other, float tolerance) {
        // If it's actually smaller
        if (this.sizesBigger(other) > 1) {
            return isSizedVersionOf(other, tolerance);
        }
        return false;
    }

    public boolean isCompressionOf(FVector other, float tolerance) {
        // If it's actually larger
        if (this.sizesSmaller(other) > 1) {
            return isSizedVersionOf(other, tolerance);
        }
        return false;
    }

    public float angleBetween(FVector other) {
        return 2.0f * (float) Math.atan(FVector.subtract(this, other).length() / FVector.add(this, other).length());
    }

    private static boolean isAround(float n1, float n2, float tol) {
        return (Math.abs(n1 - n2) <= tol);
    }

    public float lowestValue() {
        if (x <= y && x <= z) {
            return x;
        } else if (y <= z && y <= x) {
            return y;
        }
        return z;
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
