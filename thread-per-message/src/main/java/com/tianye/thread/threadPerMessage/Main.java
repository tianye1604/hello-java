package com.tianye.thread.threadPerMessage;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/8 20:14
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main BEGIN");
        Host host = new Host();
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        host.request(40,'D');
        System.out.println("main END");
    }
}
