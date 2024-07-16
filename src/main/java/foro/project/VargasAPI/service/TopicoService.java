package foro.project.VargasAPI.service;

import foro.project.VargasAPI.controller.TopicoController;
import foro.project.VargasAPI.controller.dto.*;
import foro.project.VargasAPI.model.Curso;
import foro.project.VargasAPI.model.Mensaje;
import foro.project.VargasAPI.model.Topico;
import foro.project.VargasAPI.repository.MensajeRepository;
import foro.project.VargasAPI.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    public Topico registrarTopico(@NotNull DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTituloAndMensajes_contenido(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tópico ya existe.");
        }
        Topico nuevoTopico = new Topico(datosRegistroTopico);
        return topicoRepository.save(nuevoTopico);
    }

    public Page<DatosListadoTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAllActive(paginacion).map(DatosListadoTopico::new);
    }

    public PagedModel<EntityModel<DatosListadoTopico>> convertirAPagedModel(Page<DatosListadoTopico> topicosPage,
                                                                            PagedResourcesAssembler<DatosListadoTopico> pagedResourcesAssembler,
                                                                            Pageable paginacion) {
        return pagedResourcesAssembler.toModel(topicosPage,
                topico -> EntityModel.of(topico,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicoController.class)
                                .listarTopicos(paginacion)).withSelfRel()));
    }

    public Page<DatosListadoTopico> buscarTopicosPorCurso(String nombreCurso, Pageable paginacion) {
        Curso curso;
        try {
            curso = Curso.valueOf(nombreCurso.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso inválido");
        }
        return topicoRepository.findByCursoAndStatusNotClosed(curso, paginacion).map(DatosListadoTopico::new);
    }

    public Optional<Topico> buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    @Transactional
    public void actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        topico.actualizarTopico(datosActualizarTopico);
        topicoRepository.save(topico);
    }

    public DatosListadoMensaje obtenerUltimoMensaje(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        List<Mensaje> mensajes = topico.getMensajes();
        if (!mensajes.isEmpty()) {
            Mensaje ultimoMensaje = mensajes.get(mensajes.size() - 1);
            return new DatosListadoMensaje(
                    ultimoMensaje.getId(),
                    ultimoMensaje.getContenido(),
                    ultimoMensaje.getFecha(),
                    ultimoMensaje.getAutor()
            );
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay mensajes en el tópico");
        }
    }

    @Transactional
    public DatosListadoMensaje agregarMensaje(Long id, DatosNuevoMensaje datosNuevoMensaje) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        Mensaje nuevoMensaje = new Mensaje(datosNuevoMensaje);
        topico.agregarMensaje(nuevoMensaje);
        topicoRepository.save(topico);
        mensajeRepository.save(nuevoMensaje);
        return new DatosListadoMensaje(nuevoMensaje);
    }

    @Transactional
    public void cerrarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        topico.cerrarTopico();
        topicoRepository.save(topico);
    }

    @Transactional
    public void eliminarMensaje(Long idTopico, Long idMensaje) {
        Topico topico = topicoRepository.findById(idTopico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        Mensaje mensaje = topico.getMensajes().stream()
                .filter(m -> m.getId().equals(idMensaje))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no encontrado"));
        topico.getMensajes().remove(mensaje);
        topicoRepository.save(topico);
        mensajeRepository.deleteById(idMensaje);
    }
}
