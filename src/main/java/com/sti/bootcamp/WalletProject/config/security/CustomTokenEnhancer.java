package com.sti.bootcamp.WalletProject.config.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static java.time.Instant.now;

public class CustomTokenEnhancer implements TokenEnhancer {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    long millis = date.toInstant().toEpochMilli();
    long timestamp = now().getEpochSecond();

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization", authentication.getName());
//        additionalInfo.put("first_name", );
//        additionalInfo.put("dateFormat", dateFormat);
        additionalInfo.put("seconds", timestamp);
        additionalInfo.put("date", millis);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
