package com.study.anything;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
public class TestImageBase64 {
    public static String convertImageToBase64(String imageUrl) {
        String base64Image = "";
        try {
            // Create a URL object with the image URL
            URL url = new URL(imageUrl);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the input stream from the connection
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // Read the input stream and write it to the byte array output stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            // Convert the byte array output stream to a byte array
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Encode the byte array to a Base64 string
            base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Close the input stream and the byte array output stream
            inputStream.close();
            byteArrayOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return base64Image;
    }

    public static void main(String[] args) {
        String imageUrl = "https://s3.jdpay.com/car-license/license/1743137565953R61?AWSAccessKeyId=BA489584BFDB4B4A9BE6760E079910E7&Expires=1743234326&Signature=ohPdwgGMsny1cS08bmLVCoAB8tU%3D";
        String base64Image = convertImageToBase64(imageUrl);
        System.out.println("Base64 Image: " + base64Image);
    }
}
