package core.utils;

public class Attempts {

    private final int $initialAttempts;
    private int $currentAttemptCount;

    public Attempts(int attempts) {
        $initialAttempts = attempts;
        $currentAttemptCount = 0;
    }

    public void start() {
        if ($currentAttemptCount > 0) return;
        $currentAttemptCount++;
    }

    public boolean hasMoreToGo() {
        boolean hasMoreToGo = $currentAttemptCount < $initialAttempts;
        $currentAttemptCount++;
        return hasMoreToGo;
    }
}
