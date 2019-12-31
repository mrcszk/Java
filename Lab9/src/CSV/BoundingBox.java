package CSV;
import java.util.Locale;

public class BoundingBox {
    static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean isEmpty;

    public BoundingBox(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.isEmpty = false;
    }

    public BoundingBox() {
        this.isEmpty = true;
    }

    /**
     * Enlarges BoundingBox so that it contains a point (x,y)
     *
     * @param x - coordinate x
     * @param y - coordinate y
     */
    void addPoint(double x, double y) {
        if (!this.isEmpty()) {
            if (x > xmax)
                xmax = x;
            if (x < xmin)
                xmin = x;
            if (y > ymax)
                ymax = y;
            if (y < ymin)
                ymin = y;
        } else {
            this.xmax = this.xmin = x;
            this.ymax = this.ymin = y;
        }
        isEmpty = false;
    }

    /**
     * Checks whether BoundingBox contains a point (x,y)
     *
     * @param x - coordinate x
     * @param y - coordinate y
     * @return
     */

    boolean contains(double x, double y) {
        return !isEmpty && ymin < y && ymax > y && xmin < x && xmax > x;
    }

    /**
     * Checks whether BoundingBox contains another bounding box object (x,y)
     *
     * @param bb - bounding box to compare with given BoundingBox
     * @return
     */
    boolean contains(BoundingBox bb) {
        return !isEmpty && bb.xmin >= xmin && bb.xmax <= xmax && bb.ymin >= ymin && bb.ymax <= ymax;
    }

    boolean intersects(BoundingBox bb) {
        return !isEmpty && (bb.xmin < xmax || bb.xmax > xmin || bb.ymin < ymax || bb.ymax > ymin);
    }

    BoundingBox add(BoundingBox bb) {
        if (!bb.isEmpty()) {
            if (bb.xmin < xmin)
                this.xmin = bb.xmin;
            if (bb.xmax > xmax)
                this.xmax = bb.xmax;
            if (bb.ymin < ymin)
                this.ymin = bb.ymin;
            if (bb.ymax > ymax)
                this.ymax = bb.ymax;
            isEmpty = false;
        }
        return this;
    }

    boolean isEmpty() {
        return isEmpty;
    }

    double getCenterX() {
        if (this.isEmpty())
            throw new RuntimeException("BoundingBox is empty!");
        return (xmin + xmax) / 2;
    }

    double getCenterY() {
        if (this.isEmpty())
            throw new RuntimeException("BoundingBox is empty!");
        return (ymin + ymax) / 2;
    }

    @Override
    public String toString() {
        if (this.isEmpty())
            return "EmptyBoundigBox";
        return "BoundingBox{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                '}';
    }

    double distanceTo(BoundingBox bbx) {
        if (this.isEmpty())
            throw new RuntimeException("BoundingBox is empty!");

        double startLat = this.getCenterX();
        double endLat = bbx.getCenterX();
        double startLong = this.getCenterY();
        double endLong = bbx.getCenterY();
        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public String getWKT() {
        if (this.isEmpty())
            throw new RuntimeException("BoundingBox is empty!");
        return String.format(Locale.US, "LINESTRING(%f %f, %f %f, %f %f, %f %f, %f %f)",
                xmin, ymin, xmin, ymax, xmax, ymin, xmax, ymax, xmin, ymin);
    }
}
