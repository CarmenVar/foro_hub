package foro.project.VargasAPI.repository;


import foro.project.VargasAPI.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    void deleteById(Long id);
}
