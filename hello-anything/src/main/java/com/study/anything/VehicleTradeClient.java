package com.study.anything;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class VehicleTradeClient {

    private static final String REQUEST_URL = "http://chexian.jd.com/reconciliation/vehicledownload";
    private static final String OUTPUT_FILE_PATH = "/Users/tianshujian1/workspaces/tools/hello-java/hello-web/src/main/file/"; // 本地保存文件的路径

    public static void main(String[] args) {
        String comId = "209";
        String serialNo = System.currentTimeMillis()+"";;
        String requestBody = createRequestBody();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(REQUEST_URL + "?comId=" + comId + "&serialNo=" + serialNo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                saveResponseToFile(connection.getInputStream(), OUTPUT_FILE_PATH + "209-20250312.txt");
            } else {
                System.out.println("Failed to fetch file. Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String createRequestBody() {
        // 创建您的JSON请求体
        return "uGoyA5Ush6s+fGdHwUMd71y6DvKut/2pDUABOxMvJU3Ge9EEM6hofDrIEKzgdABMOU2EYT1DKgum+kC9joLcvTUySNoi/pEyJ16kgr2PITk="; // Replace with actual JSON

    }

    private static void saveResponseToFile(InputStream inputStream, String finalName) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        GZIPInputStream gcis = null;
        try {

            bis = new BufferedInputStream(inputStream);//输入流之间转换

            bos = new BufferedOutputStream(new FileOutputStream(finalName));//输出流

            gcis = new GZIPInputStream(bis); //.gz压缩包解压核心 步骤
            byte[] buffer = new byte[1024];
            int read = -1;
            while((read = gcis.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(".gz 压缩包解压异常");
        }finally {
            try {
                gcis.close();
                bos.close();
                bis.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("IO流关闭异常");
            }
        }
    }

    private static void getDownloadReconciliation(HttpServletRequest request){

        String finalName = ""; //解压到目标文件夹的地址

        ServletInputStream inputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        GZIPInputStream gcis = null;
        try {
            inputStream = request.getInputStream(); //request 获取输入流

            bis = new BufferedInputStream(inputStream);//输入流之间转换

            bos = new BufferedOutputStream(new FileOutputStream(finalName));//输出流

            gcis = new GZIPInputStream(bis); //.gz压缩包解压核心 步骤
            byte[] buffer = new byte[1024];
            int read = -1;
            while((read = gcis.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(".gz 压缩包解压异常");
        }finally {
            try {
                gcis.close();
                bos.close();
                bis.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("IO流关闭异常");
            }
        }

    }
}