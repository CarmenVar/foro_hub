package foro.project.VargasAPI.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosNuevoMensaje(
        @NotBlank String contenido,
        @NotBlank String autor) {
}
