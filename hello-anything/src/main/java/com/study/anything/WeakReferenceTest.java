package com.study.anything;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
//    public static void main(String[] args) {
//        WeakReference<Object> weakReference0 = new WeakReference<>(new Object());
//        System.out.println(weakReference0.get());
//        System.gc();
//        System.out.println(weakReference0.get());
//    }

    public static void main(String[] args) {
        Object object = new Object();
        WeakReference<Object> weakReference1 = new WeakReference<>(object);
        System.out.println(weakReference1.get());
        System.gc();
        System.out.println(weakReference1.get());

        object = null;
        System.out.println(weakReference1.get());
        System.gc();
        System.out.println(weakReference1.get());
    }
}
