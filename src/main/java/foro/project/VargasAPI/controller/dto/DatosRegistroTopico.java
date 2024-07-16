package foro.project.VargasAPI.controller.dto;

import foro.project.VargasAPI.model.Curso;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio.")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio.")
        String mensaje,
        @NotBlank(message = "El autor es obligatorio.")
        String autor,
        @NotNull(message = "El curso es obligatorio.")
        Curso curso) {
}
