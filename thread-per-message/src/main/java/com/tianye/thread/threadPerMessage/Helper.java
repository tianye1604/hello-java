package com.tianye.thread.threadPerMessage;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/8 20:23
 * @Description:
 */
public class Helper {
    public void handle(int count, char c) {
        System.out.println("    handle(" + count + "," + c + ") BEGIN");
        for(int i=0; i<count;i++){
            slowy();
            System.out.print(c);
        }
        System.out.println(" ");

        System.out.println("    handle(" + count + "," + c + ") END");


    }

    private void slowy() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
