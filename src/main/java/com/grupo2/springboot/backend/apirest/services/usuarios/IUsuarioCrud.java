package com.grupo2.springboot.backend.apirest.services.usuarios;

import com.grupo2.springboot.backend.apirest.entity.Usuario;

public interface IUsuarioCrud {

	public Usuario registrarUsuario(Usuario usuario);

	public Usuario findByUsername(String username);

}
