package com.study.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/3/25 16:28
 * @Description:
 */
public class HttpGetApp {

	public static void main(String[] args) throws IOException {
		HttpResponse response = null;

		try {
			HttpGet get = new HttpGet("http://item.jd.com/2381431.html");
			response = HttpClientUtil.getHttpClient().execute(get);
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				EntityUtils.consume(response.getEntity());
			}else{
				String result = EntityUtils.toString(response.getEntity());
			}

		} catch (IOException e) {
			if(response != null) {
				EntityUtils.consume(response.getEntity());
			}
		}
	}
}
