package android.util;

public class Log {

    public static int d(String tag, String info) {
        System.out.println(tag + ":" + info);
        return 0;
    }

    public static int i(String tag, String info) {
        System.out.println(tag + ":" + info);
        return 0;
    }

    public static int w(String tag, String info) {
        System.err.println(tag + ":" + info);
        return 0;
    }

    public static int e(String tag, String info) {
        System.err.println(tag + ":" + info);
        return 0;
    }

    public static int wtf(String tag, String info) {
        System.err.println(tag + ":" + info);
        return 0;
    }
}
