package core.utils;

public class Timer {
    long $endTime;

    public Timer(long timeout) {
        $endTime = System.currentTimeMillis() + timeout;
    }

    public boolean timedOut() {
        return $endTime - System.currentTimeMillis() < 0;
    }

}