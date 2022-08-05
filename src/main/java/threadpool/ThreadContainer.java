package threadpool;

public class ThreadContainer {

    public static SingleThread getSingle() {
        return new SingleThread();
    }

    public static MultiThread getMulti() {
        return new MultiThread();
    }

    public static CustomThreadPool getCustom(int nThread) {
        return new CustomThreadPool(nThread);
    }
}
