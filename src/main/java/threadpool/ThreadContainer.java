package threadpool;

public class ThreadContainer {

    public static SingleThread singleThread() {
        return new SingleThread();
    }

    public static MultiThread multiThread() {
        return new MultiThread();
    }

    public static FixedThreadPool fixedThreadPool(int nThread) {
        return new FixedThreadPool(nThread);
    }
    public static NewFixedThreadPool newFixedThreadPool(int nThread) {
        return new NewFixedThreadPool(nThread);
    }

    public static CachedThreadPool cachedThreadPool() {
        return new CachedThreadPool();
    }
}
