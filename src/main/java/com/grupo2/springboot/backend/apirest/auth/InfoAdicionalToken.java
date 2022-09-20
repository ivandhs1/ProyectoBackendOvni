package com.grupo2.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.grupo2.springboot.backend.apirest.entity.Usuario;
import com.grupo2.springboot.backend.apirest.services.usuarios.UserDetailsServiceImpl;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UserDetailsServiceImpl usuarioSerive;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioSerive.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("correo", usuario.getUsername());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
