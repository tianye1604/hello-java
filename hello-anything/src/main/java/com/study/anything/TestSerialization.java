package com.study.anything;

import com.jd.com.caucho.hessian.io.Hessian2Output;
import com.jd.org.msgpack.MessagePack;
import com.study.anything.model.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerialization {

    public static <T> byte[] msgpackSerialization(T t) {
        MessagePack messagePack = new MessagePack();
        byte[] write = new byte[0];
        try {
            write = messagePack.write(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return write;
    }

    public static <T> byte[] hessianSerialization(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(outputStream);
            output.writeObject(t);
            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();
            data = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> byte[] jdkSerialization(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(outputStream);
            output.writeObject(t);
            output.flush();
            output.close();
            data = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0L);
        userInfo.setBizUserPin("123");
        userInfo.setTenantId("123");
        userInfo.setShopId(0L);
        userInfo.setMerchantId(0L);
        byte[] hessianBytes = hessianSerialization(userInfo);
        byte[] jdkBytes = jdkSerialization(userInfo);
        byte[] msgPackBytes = msgpackSerialization(userInfo);
        System.out.println("jdk序列化长度：" + jdkBytes.length);
        System.out.println("msgpack序列化长度：" +msgPackBytes.length);
        System.out.println("hessian序列化长度：" +hessianBytes.length);
        System.out.println();
    }

}
