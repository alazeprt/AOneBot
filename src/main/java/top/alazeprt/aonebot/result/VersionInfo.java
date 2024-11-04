package top.alazeprt.aonebot.result;

import com.google.gson.JsonObject;

public class VersionInfo {
    private final String appName;
    private final String appVersion;
    private final String protocolVersion;

    public VersionInfo(String appName, String appVersion, String protocolVersion) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.protocolVersion = protocolVersion;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public static VersionInfo fromJson(JsonObject jsonObject) {
        jsonObject = jsonObject.get("data").getAsJsonObject();
        String appName = jsonObject.get("app_name").getAsString();
        String appVersion = jsonObject.get("app_version").getAsString();
        String protocolVersion = jsonObject.get("protocol_version").getAsString();
        return new VersionInfo(appName, appVersion, protocolVersion);
    }
}

