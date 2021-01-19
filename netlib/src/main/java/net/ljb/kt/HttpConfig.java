package net.ljb.kt;

import net.ljb.kt.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class HttpConfig {

    public interface CommHeader {
        void addCommHeader(Map<String, String> headers);
    }

    public interface CommParam {
        void addCommHeader(Map<String, String> params);
    }

    private final String baseUrl;
    private final boolean openLog;
    private final CommHeader commHeader;
    private final CommParam commParam;


    private HttpConfig(String baseUrl, CommHeader header, CommParam param, boolean openLog) {
        this.baseUrl = baseUrl;
        this.commHeader = header;
        this.commParam = param;
        this.openLog = openLog;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isOpenLog(){
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


    public static class Builder {

        private final String baseUrl;
        private boolean openLog;
        private CommHeader commHeader;
        private CommParam commParam;

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder addCommHeader(CommHeader header) {
            this.commHeader = header;
            return this;
        }

        public Builder addCommParam(CommParam param) {
            this.commParam = param;
            return this;
        }

        public Builder openLog(boolean open) {
            this.openLog = open;
            return this;
        }

        public void build() {
            HttpConfig httpConfig = new HttpConfig(baseUrl, commHeader, commParam, openLog);
            HttpClient.INSTANCE.init(httpConfig);
        }

    }

}
