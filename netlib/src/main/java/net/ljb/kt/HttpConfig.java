package net.ljb.kt;

import net.ljb.kt.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class HttpConfig {

    public interface ICommHeader {
        void addCommHeader(Map<String, String> headers);
    }

    public interface ICommParam {
        void addCommHeader(Map<String, String> params);
    }

    public interface ICommCookie {
        void saveCookie(String host, String cookie);

        String loadCookie(String host);
    }

    private final String baseUrl;
    private final boolean openLog;
    private final ICommHeader commHeader;
    private final ICommParam commParam;
    private final ICommCookie commCookie;


    private HttpConfig(String baseUrl, ICommHeader header, ICommParam param, ICommCookie cookie, boolean openLog) {
        this.baseUrl = baseUrl;
        this.commHeader = header;
        this.commParam = param;
        this.commCookie = cookie;
        this.openLog = openLog;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isOpenLog() {
        return openLog;
    }

    public Map<String, String> getCommParam() {
        if (commParam == null) return null;
        HashMap<String, String> map = new HashMap<>();
        commParam.addCommHeader(map);
        return map;
    }

    public Map<String, String> getCommHeader() {
        if (commHeader == null) return null;
        HashMap<String, String> map = new HashMap<>();
        commHeader.addCommHeader(map);
        return map;
    }


    public ICommCookie getCommCookie() {
        return commCookie;
    }

    public static class Builder {

        private final String baseUrl;
        private boolean openLog;
        private ICommHeader commHeader;
        private ICommParam commParam;
        private ICommCookie commCookie;

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder addCommCookie(ICommCookie cookie) {
            this.commCookie = cookie;
            return this;
        }

        public Builder addCommHeader(ICommHeader header) {
            this.commHeader = header;
            return this;
        }

        public Builder addCommParam(ICommParam param) {
            this.commParam = param;
            return this;
        }

        public Builder openLog(boolean open) {
            this.openLog = open;
            return this;
        }

        public void build() {
            HttpConfig httpConfig = new HttpConfig(baseUrl, commHeader, commParam, commCookie, openLog);
            HttpClient.INSTANCE.init(httpConfig);
        }

    }

}
