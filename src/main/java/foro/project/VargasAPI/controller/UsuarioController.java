package foro.project.VargasAPI.controller;

import foro.project.VargasAPI.controller.dto.DatosNuevoUsuario;
import foro.project.VargasAPI.model.Usuario;
import foro.project.VargasAPI.service.UsuarioService; // Asegúrate de importar el servicio
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios, incluyendo registro y consulta.")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // Cambié de Usuario a UsuarioService

    @PostMapping("/registro")
    @Operation(summary = "Registrar un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema.")
    public ResponseEntity<Usuario> registrarUsuario(
            @Parameter(description = "Datos del nuevo usuario a registrar", required = true)
            @Valid @RequestBody DatosNuevoUsuario datosNuevoUsuario) {
        Usuario usuarioCreado = usuarioService.registrarNuevoUsuario(datosNuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }
}

