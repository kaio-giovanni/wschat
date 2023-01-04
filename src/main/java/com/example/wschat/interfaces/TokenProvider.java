package com.example.wschat.interfaces;

import java.util.Map;

public interface TokenProvider {

    Map<String, String> decode(String token);
}
