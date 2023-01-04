package com.example.wschat.interfaces;

import java.security.PublicKey;

public interface KeyProvider {

    PublicKey getPublicKey(String keyId);
}
