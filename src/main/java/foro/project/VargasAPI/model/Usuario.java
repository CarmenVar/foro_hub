package foro.project.VargasAPI.model;

import foro.project.VargasAPI.controller.dto.DatosNuevoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder // Añadir el patrón de construcción
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El campo email no puede ser nulo")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotNull(message = "El campo clave no puede ser nulo")
    @Size(min = 6, max = 300, message = "La clave debe tener entre 6 y 300 caracteres")
    private String clave;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Constructor que acepta DatosNuevoUsuario
    public Usuario(DatosNuevoUsuario datosNuevoUsuario) {
        this.nombre = datosNuevoUsuario.getNombre();
        this.email = datosNuevoUsuario.getEmail();
        this.clave = (String) datosNuevoUsuario.getPassword(); // No necesitas hacer casting
    }

    // Método para actualizar la clave si es necesario
    public void actualizarClave(String nuevaClave) {
        this.clave = nuevaClave;
    }

    public void setPassword(String encode) {

    }


    }

