package com.dm.framework.protocol.http;

import com.alibaba.fastjson.JSON;
import com.dm.framework.entity.Invocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {

        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(JSON.toJSONString(invocation).getBytes());
            oos.flush();
            oos.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
