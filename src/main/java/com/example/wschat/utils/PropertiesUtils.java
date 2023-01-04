package com.example.wschat.utils;

import com.example.wschat.log.Log;

public class PropertiesUtils {

    private static final Log logger = new Log(PropertiesUtils.class);

    private PropertiesUtils() {
        // Do nothing
    }

    public static void loadByEnv() {
        logger.info("Setting properties by env...");
        System.setProperty("app.auth.jwks-url", DotEnvUtils.getJwksUrl());
    }
}
