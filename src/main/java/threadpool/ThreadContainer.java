package threadpool;

public class ThreadContainer {

    public static SingleThread singleThread() {
        return new SingleThread();
    }

    public static MultiThread multiThread() {
        return new MultiThread();
    }

    public static FixedThreadPool FixedThreadPool(int nThread) {
        return new FixedThreadPool(nThread);
    }

    public static CachedThreadPool cachedThreadPool() {
        return new CachedThreadPool();
    }
}
