package com.study.anything;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TestSplitFile {

    public static void main(String[] args) throws IOException {
        File src = new File("F:\\download\\live800_im.sql");

        InputStreamReader ir = new InputStreamReader(new FileInputStream(src), "UTF8");
        MyBufferedReader bf = new MyBufferedReader(ir);

        String ss = "";
        File target = new File("F:\\download\\targetFile\\live800_im\\0.sql");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), "UTF8"));

        int NO = 0;
        int count = 0;
        do {
            ss = bf.myReadLine();
            count ++;
            System.out.println(count);
            if( ss.startsWith("DROP TABLE IF EXISTS" )) {
                bw.close();
                NO++;
                String table = ss.substring(ss.indexOf("`"),ss.lastIndexOf("`") + 1);
                target = new File("F:\\download\\targetFile\\live800_im\\"+ NO + "_" + table +".sql");
                bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), "UTF8"));
            }
            bw.write(new String(ss.getBytes(StandardCharsets.UTF_8)));
            bw.newLine();

        }while (!Objects.isNull(ss));
        bw.close();
        bf.myClose();

    }

    static class MyBufferedReader {
        private InputStreamReader r;
        MyBufferedReader(InputStreamReader re){
            this.r = re;
        }
        //模拟readLine()方法；构建自己的myReadLine()
        public String myReadLine() throws IOException{
            //定义一个临时的容器，就用StringBuilder吧.
            StringBuilder sb = new StringBuilder();
            int ch = 0;
            while((ch=r.read())!=-1) {
                if(ch=='\r')
                    continue;
                if(ch==0)
                    continue;
                if(ch=='\n')
                    return sb.toString();
                sb.append((char)ch);
            }
            //如果文件最后一行没有换行符，但是字符，通过上面的循环无法将其返回给文件读取流；
            //于是我们需要做个判断:
            //疑问：为什么这里判断sb！=null；就不行，主程序中运行会无限循环？？？？？
            if(sb.length()!=0)
                return sb.toString();
            //如文件或该行为空，就返回空。
            return null;
        }
        public void myClose() throws IOException{
            r.close();
        }
    }
}
