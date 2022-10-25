/*
Write a program BruteCollinearPoints.java that examines 4 points at a time and checks 
whether they all lie on the same line segment, returning all such line segments. To 
check whether the 4 points p, q, r, and s are collinear, check whether the three 
slopes between p and q, between p and r, and between p and s are all equal.

The method segments() should include each line segment containing 4 points exactly 
once. If 4 points appear on a line segment in the order p→q→r→s, then you should include 
either the line segment p→s or s→p (but not both) and you should not include subsegments 
such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints 
that has 5 or more collinear points.

---

Brute force algorithm. Write code to iterate through all 4-tuples and check if the 4 points 
are collinear. To form a line segment, you need to know its endpoints. One approach is to 
form a line segment only if the 4 points are in ascending order (say, relative to the 
natural order), in which case, the endpoints are the first and last points.

Hint: don't waste time micro-optimizing the brute-force solution. Though, there are two easy 
opportunities. First, you can iterate through all combinations of 4 points (N choose 4) 
instead of all 4 tuples (N^4), saving a factor of 4! = 24. Second, you don't need to consider 
whether 4 points are collinear if you already know that the first 3 are not collinear; this 
can save you a factor of N on typical inputs.
 */

import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points){
        // finds all line segments containing 4 points
        if (points == null)     throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++)
            if ((points[i] == null) || containsRepeatedPoint(points, i))    throw new IllegalArgumentException();

        Arrays.sort(points, points[0].slopeOrder()); 
    }
    
    private boolean containsRepeatedPoint(Point[] points, int i){
        for (int j = 0; j < i; j++)
            if (points[j].slopeTo(points[i]) == Double.NEGATIVE_INFINITY)   return true;
        return false;
    }

    public int numberOfSegments() {
        // the number of line segments
    }
    
    public LineSegment[] segments() {
        // the line segments
        // The method segments() should include each line segment containing 4 points exactly once. 
        // If 4 points appear on a line segment in the order p→q→r→s, then you should include either 
        // the line segment p→s or s→p (but not both) and you should not include subsegments 
        // such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints 
        // that has 5 or more collinear points.

        LineSegment[] segmentsList = new LineSegment[4];
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
