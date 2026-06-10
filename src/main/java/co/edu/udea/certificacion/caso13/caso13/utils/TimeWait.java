package co.edu.udea.certificacion.caso13.caso13.utils;

public class TimeWait {

    private TimeWait() {
    }

    public static void forSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread was interrupted during visual wait", exception);
        }
    }
}