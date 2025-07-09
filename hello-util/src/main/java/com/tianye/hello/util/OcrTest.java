package com.tianye.hello.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OcrTest {

    private static final String CAPTCHA_IDENTIFY_URL = "http://gateway-jd-pre.az.jd.local/ins-bdg-vision-carins.ins-bdg-pre-prod/captcha_identify";
    private static final String CAPTCHA_PATH = "/Users/tianshujian1/Desktop/ocrTest/source";
    private static final String DECODE_CAPTCHA_PATH = "/Users/tianshujian1/Desktop/ocrTest/decodeResult";
    private static final String OCR_RESULT_PATH = "/Users/tianshujian1/Desktop/ocrTest/ocrResult/";


    private final static int timeout = 5000; //请求超时时间，毫秒


    public static void main(String[] args) {
//        batchConvertBase64ToFile(DECODE_CAPTCHA_PATH);
        batchOcr(DECODE_CAPTCHA_PATH);
    }


    public static void batchOcr(String directoryPath) {
//        String directoryPath = CAPTCHA_PATH;

        // 创建文件对象
        File directory = new File(directoryPath);

        // 检查路径是否为目录
        if (directory.isDirectory()) {
            // 获取目录中的所有文件和子目录
            File[] filesList = directory.listFiles();

            if (filesList != null) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        // 打印文件名称
                        String code = identify(file);
                        System.out.println("File: " + file.getName() + ",code="+code + "," + file.getName().toUpperCase().startsWith(code));

                        // 指定要写入的文件路径
                        String filePath = OCR_RESULT_PATH + code + ".jpg";

                        copyFile(file,filePath);

                    } else if (file.isDirectory()) {
                        // 打印子目录名称
                        System.out.println("Directory: " + file.getName());
                    }
                }
            } else {
                System.out.println("The directory is empty or an I/O error occurred.");
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }
    }

    public static void ocrFile(String fileName,String content) {
        // 指定要写入的文件路径
        String filePath = OCR_RESULT_PATH + fileName + ".jpg";

        // 要写入文件的内容
//        String content = "Hello, World!\nThis is a sample text written to the file.";

        // 使用try-with-resources语句自动关闭资源
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
            // 也可以逐行写入
            // bw.write("Hello, World!");
            // bw.newLine();
            // bw.write("This is a sample text written to the file.");
        } catch (Exception e) {
            // 处理文件写入过程中可能发生的异常
            e.printStackTrace();
        }
    }

    // 复制文件的方法
    public static boolean copyFile(File sourceFile, String destinationFilePath) {
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void batchConvertBase64ToFile(String directoryPath) {

        // 创建文件对象
        File directory = new File(CAPTCHA_PATH);

        // 检查路径是否为目录
        if (directory.isDirectory()) {
            // 获取目录中的所有文件和子目录
            File[] filesList = directory.listFiles();

            if (filesList != null) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        // 打印文件名称
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                // 打印文件的每一行
                                System.out.println(line);
                                File decodeFile = convertBase64ToFile(line,directoryPath,file.getName());
                            }
                        } catch (Exception e) {
                            // 处理文件读取过程中可能发生的异常
                            e.printStackTrace();
                        }



                    } else if (file.isDirectory()) {
                        // 打印子目录名称
                        System.out.println("Directory: " + file.getName());
                    }
                }
            } else {
                System.out.println("The directory is empty or an I/O error occurred.");
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }
    }

    public static String identify( File file) {
        String checkCode = "";

        try {
            String url = CAPTCHA_IDENTIFY_URL;
            Map<String, Object> map = new HashMap<String, Object>();

            log.info("验证码识别地址：" + url);
            map.put("image", file);
            String res = HttpUtil.post(url, map, timeout);
            log.info("验证码识别返回：" + res);
            CaptchaBean bean = JSONObject.parseObject(res, CaptchaBean.class);
            if (bean.getCode() == 200) {
                checkCode = bean.getResult().getValue();
            }
        } catch (Exception e) {
            log.info("验证码识别失败：" + e.getMessage(), e);
        }
        log.info("识别后的验证码：" + checkCode);
        return checkCode;
    }

    /**
     * 转换文件
     * @param fileBase64String
     * @param filePath
     * @param fileName
     * @return
     */
    public static File convertBase64ToFile(String fileBase64String,String filePath,String fileName) {
        File dir = new File(filePath + File.separator + fileName);
        return Base64.decodeToFile(fileBase64String, dir);
    }

    /**
     * CaptchaBean
     */
    public static class CaptchaBean {
        public CaptchaBean() {
            super();
        }

        private int code;
        private String msg = "";
        private Result result = new Result();

        public void setCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Result getResult() {
            return result;
        }

        /**
         * Result
         */
        public static class Result {
            private String value = "";

            public void setValue(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }
    }
}
