package android.widget.log;

public final class LogManager {
    private static Logger logger;

    static {
        Logger logger2;
        new LoggerDefault();
        logger = logger2;
    }

    public LogManager() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger2) {
        logger = logger2;
    }
}
