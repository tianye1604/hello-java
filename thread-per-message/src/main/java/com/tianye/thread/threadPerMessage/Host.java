package com.tianye.thread.threadPerMessage;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/8 20:16
 * @Description:
 */
public class Host {
    private final Helper helper = new Helper();

    public void request(final int count,final char c) {
        System.out.println("    request(" + count + "," + c + ") BEGIN");
        new Thread() {
            public void run() {
                helper.handle(count,c);
            }
        }.start();
        System.out.println("    request(" + count + "," + c + ") END");
    }
}
