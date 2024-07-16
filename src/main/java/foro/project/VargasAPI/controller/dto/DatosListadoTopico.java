package foro.project.VargasAPI.controller.dto;

import foro.project.VargasAPI.model.Curso;
import foro.project.VargasAPI.model.Topico;

import java.util.List;
import java.util.stream.Collectors;

public record DatosListadoTopico(
        Long id,
        String titulo,
        List<DatosListadoMensaje> mensajes,
        String status,
        Curso curso) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensajes().stream()
                        .map(DatosListadoMensaje::new)
                        .collect(Collectors.toList()),
                topico.getStatus(),
                topico.getCurso());
    }
}
