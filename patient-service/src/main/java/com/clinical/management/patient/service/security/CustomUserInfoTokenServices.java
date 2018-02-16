package com.clinical.management.patient.service.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.*;

public class CustomUserInfoTokenServices implements ResourceServerTokenServices {
    private static final String ERROR = "ERROR";
    private final Log logger = LogFactory.getLog(getClass());
    private static final String[] PRINCIPAL_KEYS = new String[]{"user", "username", "userid", "user_id", "login", "id", "name"};
    private final String userInfoEndpointUrl;
    private final String clientId;
    private OAuth2RestOperations restTemplate;
    private String tokenType = DefaultOAuth2AccessToken.BEARER_TYPE;
    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();

    public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        this.userInfoEndpointUrl = userInfoEndpointUrl;
        this.clientId = clientId;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setAuthoritiesExtractor(AuthoritiesExtractor authoritiesExtractor) {
        this.authoritiesExtractor = authoritiesExtractor;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        Map<String, Object> map = getMap(this.userInfoEndpointUrl, accessToken);
        if (map.containsKey(ERROR)) {
            this.logger.debug("User info returned error: " + map.get(ERROR));
            throw new InvalidTokenException(accessToken);
        }
        return extractAuthentication(map);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    @SuppressWarnings({"unchecked"})
    private Map<String, Object> getMap(String path, String accessToken) {
        this.logger.debug("Getting user info from: " + path);

        try {
            OAuth2RestOperations restOperations = createRestOperations(accessToken);
            return restOperations.getForEntity(path, Map.class).getBody();
        } catch (Exception ex) {
            this.logger.info("Could not fetch user details: " + ex.getClass() + ", " + ex.getMessage());
            return Collections.<String, Object>singletonMap(ERROR, "Could not fetch user details");
        }
    }

    private OAuth2RestOperations createRestOperations(String accessToken) {
        OAuth2RestOperations restOperations = this.restTemplate;
        if (restOperations == null) {
            BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
            resource.setClientId(this.clientId);
            restOperations = new OAuth2RestTemplate(resource);
        }
        OAuth2AccessToken existingToken = restOperations.getOAuth2ClientContext().getAccessToken();
        if (existingToken == null || !accessToken.equals(existingToken.getValue())) {
            DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
            token.setTokenType(this.tokenType);
            restOperations.getOAuth2ClientContext().setAccessToken(token);
        }
        return restOperations;
    }

    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
        List<GrantedAuthority> authorities = this.authoritiesExtractor.extractAuthorities(map);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(getPrincipal(map), "N/A", authorities);
        token.setDetails(map);
        return new OAuth2Authentication(getRequest(map), token);
    }

    private Object getPrincipal(Map<String, Object> map) {
        for (String key : PRINCIPAL_KEYS) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return "unknown";
    }

    @SuppressWarnings({"unchecked"})
    private OAuth2Request getRequest(Map<String, Object> map) {
        Map<String, Object> request = (Map<String, Object>) map.get("oauth2Request");

        String clientIdRequest = (String) request.get("clientId");
        Set<String> scope = new LinkedHashSet<>(request.containsKey("scope") ?
                (Collection<String>) request.get("scope") : Collections.<String>emptySet());

        return new OAuth2Request(null, clientIdRequest, null, true, new HashSet<>(scope),
                null, null, null, null);
    }

}