package com.example.wschat.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvUtils {

    private DotEnvUtils() {
    }

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String getJwksUrl() {
        return dotenv.get("JWKS_URL");
    }
}