package core.utils;

public class Timer {
    long $endTime;

    public Timer(long timeout) {
        $endTime = System.currentTimeMillis() + timeout;
    }

    public boolean timedOut() {
        return $endTime - System.currentTimeMillis() < 0;
    }

    public void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}