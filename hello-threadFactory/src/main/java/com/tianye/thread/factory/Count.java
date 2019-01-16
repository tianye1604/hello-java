package com.tianye.thread.factory;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/15 20:31
 * @Description:
 */
public class Count {

    private volatile int count = 0;
    public void add(){
        count ++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
