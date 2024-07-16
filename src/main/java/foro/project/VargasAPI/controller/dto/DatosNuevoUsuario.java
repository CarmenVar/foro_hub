package foro.project.VargasAPI.controller.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public record DatosNuevoUsuario(
        @NotBlank(message = "El nombre es obligatorio.")
        String nombre,
        @NotBlank(message = "El email es obligatorio.")
        @Email(message = "El email debe ser válido.")
        String email,
        @NotBlank(message = "La contraseña es obligatoria.")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
        String password
) {
        @Override
        public String nombre() {
                return nombre;
        }

        @Override
        public String email() {
                return email;
        }

        @Override
        public String password() {
                return password;
        }

        public CharSequence getPassword() {
                return getPassword();
        }

        public String getNombre() {
                return getNombre();
        }

        public String getEmail() {
                return getEmail();
        }
}
