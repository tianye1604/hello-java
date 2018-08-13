package com.tianye.thread.concurrent.future;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/13 11:12
 * @Description: 一个需要花费很长时间才能创建实例的类
 */
public class RealData implements Data {

    private String content;

    public RealData(int count, char c) {
        System.out.println("    making RealData（" + count + "," + c + ") BEGIN" );
        char[] buffer = new char[count];
        for (int i = 0; i<count; i++){
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("    making RealData（" + count + "," + c + ") END");
        this.content = new String(buffer);
    }

    public String getContent() {
        return content;
    }
}
