package foro.project.VargasAPI.controller.dto;
import javax.validation.constraints.NotBlank;

public record DatosNuevoMensaje(
        @NotBlank String contenido,
        @NotBlank String autor) {
}
