package com.shopping.shopping_mall.oauth2;

import com.shopping.shopping_mall.domain.Role;
import com.shopping.shopping_mall.domain.SocialType;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.oauth2.userInfo.NaverOAuth2UserInfo;
import com.shopping.shopping_mall.oauth2.userInfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;

import java.util.Map;
import java.util.UUID;
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes){

        if("naver".equals(socialName)) {
            return ofNaver("id", attributes);
        }

        return null;
    }


    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
