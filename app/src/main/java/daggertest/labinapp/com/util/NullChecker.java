package daggertest.labinapp.com.util;

public class NullChecker {
    public static boolean isNotNull(Object obj) {
        return !(obj == null);
    }

    public static boolean isNull(Object obj) {
        return (obj == null);
    }
}
