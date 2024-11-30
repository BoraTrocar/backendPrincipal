package br.edu.fateccotia.boratroca.ouath2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomerOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;

    private String clienteName;

    public CustomerOAuth2User(OAuth2User oAuth2User, String clienteName) {
        this.oAuth2User = oAuth2User;
        this.clienteName = clienteName;
    }

    @Override
    public <A> A getAttribute(String name) {
        return oAuth2User.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    public String getClienteName() {
        return this.clienteName;
    }
}
