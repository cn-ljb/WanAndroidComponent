package net.ljb.kt.interceptor;


import net.ljb.kt.utils.NetLog;
import net.ljb.kt.utils.StringUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created By jie on 2019-12-27
 * 请求拦截器 打印参数和响应
 */
public class LogInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        NetLog.INSTANCE.i("\n");
        NetLog.INSTANCE.i("----------Start----------------");
        NetLog.INSTANCE.i("| " + request.toString());
        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                NetLog.INSTANCE.i("| RequestParams:{" + sb.toString() + "}");
            }
        }

        NetLog.INSTANCE.i("| Response:" + StringUtils.decode(content));
        NetLog.INSTANCE.i("----------End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
