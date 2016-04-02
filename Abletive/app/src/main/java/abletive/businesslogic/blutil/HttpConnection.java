package abletive.businesslogic.blutil;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 连接，发送Http请求，关闭连接，解析流，关闭流<br>
 * Created by Alan on 2016/3/30.
 */
public class HttpConnection {

    HttpURLConnection httpURLConnection;

    /**
     * 进行连接
     *
     * @param site    连接的url String
     * @param timeout 连接超时
     * @return InputStream
     */
    public InputStream processConnection(String site, int timeout) {

        try {
            URL url = new URL(site);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(timeout);

            return httpURLConnection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得JSON返回值
     *
     * @param site 请求网址
     * @return String类型的JSON返回值
     */
    public String getResult(String site) {
        InputStream inputStream = processConnection(site);
        String result = transStream(inputStream);
        closeConn();
        closeStream(inputStream);
        return result;
    }

    /**
     * 进行默认连接，连接超时3000ms
     *
     * @param site 连接的url String
     * @return InputStream
     */
    public InputStream processConnection(String site) {
        return processConnection(site, 3000);
    }

    /**
     * 关闭连接
     */
    public void closeConn() {
        httpURLConnection.disconnect();
    }

    /**
     * 关闭流
     *
     * @param stream 可关闭的流
     * @throws IOException
     */
    public void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析流为String
     *
     * @param inputStream 输入流
     * @return String
     */
    public String transStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String content, result = "";
        try {
            while ((content = bufferedReader.readLine()) != null) {
                result += content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
