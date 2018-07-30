package cn.aouo.common.util;

import java.util.concurrent.ThreadLocalRandom;

public final class UUIDUtil {

    private static long[] ls = new long[3000];

    private static int li = 0;

    /**
     * 私有构造器
     */
    private UUIDUtil() {

    }

    /**
     * @return
     */
    public static synchronized String getUUID() {
        long lo = getKey();
        for (int i = 0; i < 3000; i++) {
            long lt = ls[i];
            if (lt == lo) {
                lo = getKey();
                break;
            }
        }
        ls[li] = lo;
        li++;
        if (li == 3000) {
            li = 0;
        }
        return Long.toString(lo);
    }

    /**
     * @return
     */
    private static long getKey() {
        String a = (String.valueOf(System.currentTimeMillis()-1470140046088L));
        String d = (String.valueOf(ThreadLocalRandom.current().nextDouble())).substring(4, 8);
        return Long.parseLong(a + d);
    }
}
