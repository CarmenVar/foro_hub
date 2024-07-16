package foro.project.VargasAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios, incluyendo registro y consulta.")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Registra un nuevo usuario.
     *
     * @param datosNuevoUsuario Datos del nuevo usuario a registrar.
     * @return ResponseEntity<Usuario> con los detalles del usuario registrado.
     */
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
