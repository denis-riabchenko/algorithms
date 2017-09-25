package com.riabchenko.algorithms.coursera;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by driabchenko on 7/26/15.
 */
public class ClosestPair {
    public static void main(String[] args) {
        (new ClosestPair()).execute();
    }

    private static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return String.format("(%f, %f)", x, y);
        }
    }

    private static class PairOfPoints implements Comparable<PairOfPoints> {
        private Point p;
        private Point q;
        private double delta;

        public PairOfPoints(Point p, Point q) {
            this.p = p;
            this.q = q;
            this.delta = Math.sqrt(Math.pow(p.getX() - q.getX(), 2) + Math.pow(p.getY() - q.getY(), 2));
        }

        public Point getP() {
            return p;
        }

        public Point getQ() {
            return q;
        }

        public double getDelta() {
            return delta;
        }

        @Override
        public int compareTo(PairOfPoints o) {
            return Double.compare(delta, o.getDelta());
        }

        @Override
        public String toString() {
            return String.format("%s-%s, %f", p, q, delta);
        }
    }

    public void execute() {
        final Point[] points = new Point[] {
                new Point(-3, 0), new Point(-2, 2),
                new Point(0, -3), new Point(0, 3),
                new Point(1, -1), new Point(3, 0)};

        System.out.println(closestPair(points));
    }

    private PairOfPoints closestPair(final Point[] points) {
        if (points == null || points.length < 2) {
            return null;
        }
        if (points.length == 2) {
            return new PairOfPoints(points[0], points[1]);
        }

        Point[] px = new Point[points.length];
        Point[] py = new Point[points.length];
        for (int i = 0; i < points.length; i ++) {
            px[i] = py[i] = points[i];
        }
        MergeSortD.mergeSort(px, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getX(), o2.getX());
            }
        });
        MergeSortD.mergeSort(py, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getY(), o2.getY());
            }
        });

        System.out.println(Arrays.toString(px));
        System.out.println(Arrays.toString(py));

        return closestPair(px, py, 0, points.length);
    }

    private PairOfPoints closestPair(final Point[] px, final Point[] py, int start, int length) {
        int half = length / 2;
        if (half == 0) {
            return new PairOfPoints(px[start], py[start]);
        }

        // Closest pair in left half
        PairOfPoints l = closestPair(px, py, start, half);
        // Closest pair in left half
        PairOfPoints r = closestPair(px, py, start + half, length - half);

        PairOfPoints best = l;
        if (best.compareTo(r) > 0) {
            best = r;
        }

        // Closest split pair
        return closestSplitPair(px, py, start, length, best);
    }

    private PairOfPoints closestSplitPair(final Point[] px, final Point[] py, int start, int length, PairOfPoints best) {
        int half = length / 2;
        if (half == 0) {
            return best;
        }

        double xm = px[start + half - 1].getX();
        for (int i = start; i < start + length - 1; i++) {
            if (Math.abs(py[i].getX() - xm) <= best.getDelta()) {
                int c = 0;
                for (int j = i + 1; j < start + length && c < 7; j++) {
                    if (Math.abs(py[j].getX() - xm) <= best.getDelta()) {
                        c++;
                        PairOfPoints p = new PairOfPoints(py[i], py[j]);
                        if (best.compareTo(p) > 0) {
                            best = p;
                        }
                    }
                }
            }
        }

        return best;
    }
}
