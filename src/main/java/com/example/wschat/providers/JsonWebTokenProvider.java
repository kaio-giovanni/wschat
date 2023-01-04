package com.example.wschat.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.wschat.exceptions.InvalidTokenException;
import com.example.wschat.interfaces.KeyProvider;
import com.example.wschat.interfaces.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Component
public class JsonWebTokenProvider implements TokenProvider {

    @Autowired
    private KeyProvider keyProvider;

    @Override
    public Map<String, String> decode(String token) {
        DecodedJWT jwt = JWT.decode(token);
        PublicKey publicKey = keyProvider.getPublicKey(jwt.getKeyId());
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
        algorithm.verify(jwt);
        boolean expired = jwt
                .getExpiresAtAsInstant()
                .atZone(ZoneId.systemDefault())
                .isBefore(ZonedDateTime.now());
        if (expired) {
            throw new InvalidTokenException("Token expired");
        }
        return Map.of(
                "id", jwt.getSubject(),
                "name", jwt.getClaim("name").asString(),
                "picture", jwt.getClaim("picture").asString()
        );
    }
}
