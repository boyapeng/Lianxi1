package bawei.com.lianxi1.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pc on 2016/10/24.
 */
public class NetWorkUtils {
    public static String getstr(String path) {
        URL url;
        try {
            // 创建url对象
            url = new URL(path);
            // 连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方式
            httpURLConnection.setRequestMethod("GET");
            // 设置连接超时
            httpURLConnection.setConnectTimeout(5000);
            // 设置读取超时
            httpURLConnection.setReadTimeout(5000);
            // 得到响应吗
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 200) {

                // 返回输入流
                InputStream input = httpURLConnection.getInputStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = input.read(buffer)) != -1) {
                    output.write(buffer, 0, len);
                }
                return output.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
