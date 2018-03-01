public class Ride {
    Point start;
    Point end;

    int earliestStart;
    int latestFinish;

    public Ride(Point start, Point end, int earliestStart, int latestFinish) {
        this.start = start;
        this.end = end;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }
}
