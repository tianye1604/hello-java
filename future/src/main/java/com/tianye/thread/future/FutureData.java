package com.tianye.thread.future;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/13 11:12
 * @Description: 表示“提货单”的类
 */
public class FutureData implements Data{
    private RealData realData = null;
    private boolean ready = false;

    //使用了Balking模式
    public synchronized void setRealData(RealData realdata) {
        if(ready){
            return; //balk
        }
        this.realData  = realdata;
        this.ready = true;
        notifyAll();
    }


    //以ready作为守护条件，使用了Guraded Suspension模式
    public synchronized String getContent() {
        while(!ready){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.getContent();
    }
}
