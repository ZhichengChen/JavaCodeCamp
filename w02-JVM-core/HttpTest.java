import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http Test
 * 写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub。
 */
public class HttpTest {
  public static void main(String[] args) {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    Request request = new Request.Builder()
        .url("http://localhost:8801")
        .method("GET", null)
        .build();
    try {
      Response response = client.newCall(request).execute();
      System.out.println(response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
