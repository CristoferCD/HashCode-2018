public class Ride {
    int id;
    Point start;
    Point end;

    int earliestStart;
    int latestFinish;

    public Ride(int id, Point start, Point end, int earliestStart, int latestFinish) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }
    public String toString() {
        return "inicio: " + start + "; fin: " + end + "; eS: " + earliestStart + "; lF: " + latestFinish;
    }

    public int distance() {
        return Helpers.distance(start, end);
    }
}
