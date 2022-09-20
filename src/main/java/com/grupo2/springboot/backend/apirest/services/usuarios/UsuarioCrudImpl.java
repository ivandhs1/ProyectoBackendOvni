package com.grupo2.springboot.backend.apirest.services.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IUsuarioDao;
import com.grupo2.springboot.backend.apirest.entity.Usuario;

@Service
public class UsuarioCrudImpl implements IUsuarioCrud {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

}
