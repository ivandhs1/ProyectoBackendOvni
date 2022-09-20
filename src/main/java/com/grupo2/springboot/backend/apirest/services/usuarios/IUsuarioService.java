package com.grupo2.springboot.backend.apirest.services.usuarios;

import com.grupo2.springboot.backend.apirest.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	public Usuario save(Usuario usuario);
}
