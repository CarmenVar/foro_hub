package foro.project.VargasAPI.service;

import foro.project.VargasAPI.controller.dto.DatosNuevoUsuario;
import foro.project.VargasAPI.model.Usuario;
import foro.project.VargasAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarNuevoUsuario(DatosNuevoUsuario datosNuevoUsuario) {
        // Crear un nuevo usuario utilizando el constructor
        Usuario nuevoUsuario = new Usuario(datosNuevoUsuario);

        // Encriptar la contraseña
        nuevoUsuario.setPassword(passwordEncoder.encode(datosNuevoUsuario.getPassword()));

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }
}