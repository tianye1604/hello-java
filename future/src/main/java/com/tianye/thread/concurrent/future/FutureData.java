package com.tianye.thread.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/13 11:12
 * @Description: 表示“提货单”的类
 */
public class FutureData extends FutureTask<RealData> implements Data {

    public FutureData(Callable<RealData> callable) {
        super(callable);
    }

    public String getContent() {
      String result = null;
        try {
            result = get().getContent();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }
}
