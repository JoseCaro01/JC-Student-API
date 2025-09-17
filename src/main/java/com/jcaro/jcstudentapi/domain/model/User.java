package com.jcaro.jcstudentapi.domain.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public record User(
        Long id,
        String name,
        String lastName,
        String email,
        String password,
        LocalDateTime creationDate,
        Collection<Role> roles
) {
    /**
     * Constructor compacto para el record de User.
     * <p>
     * Se utiliza para validar las propiedades del objeto de dominio justo
     * después de su creación. Asegura que el correo electrónico y la
     * contraseña cumplan con las reglas de negocio antes de que la instancia
     * sea utilizada.
     *
     * @throws IllegalArgumentException si el formato del email es inválido,
     *                                  si la contraseña es nula o tiene menos de 8 caracteres,
     *                                  o si la colección de roles es nula.
     */
    public User {
        // Validation logic remains the same
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        if (roles == null) {
            roles = Collections.emptyList();
        }
    }

    /**
     * Creates a new User record with an updated name.
     *
     * @param name The new name.
     * @return A new User instance with the updated name.
     */
    public User withName(String name) {
        return new User(this.id, name, this.lastName, this.email, this.password, this.creationDate, this.roles);
    }

    /**
     * Creates a new User record with an updated last name.
     *
     * @param lastName The new last name.
     * @return A new User instance with the updated last name.
     */
    public User withLastName(String lastName) {
        return new User(this.id, this.name, lastName, this.email, this.password, this.creationDate, this.roles);
    }

    /**
     * Creates a new User record with an updated collection of roles.
     *
     * @param roles the new collection of roles.
     * @return a new User instance with the updated roles.
     */
    public User withRoles(Collection<Role> roles) {
        return new User(this.id, this.name, this.lastName, this.email, this.password, this.creationDate, roles);
    }

    /**
     * Creates a new User record with an updated password.
     *
     * @param password the new password.
     * @return a new User instance with the updated password.
     */
    public User withPassword(String password) {
        return new User(this.id, this.name, this.lastName, this.email, password, this.creationDate, this.roles);
    }

    /**
     * Creates a new User record with an updated creation date.
     *
     * @param creationDate the new creation date.
     * @return a new User instance with the updated creation date.
     */
    public User withCreationDate(LocalDateTime creationDate) {
        return new User(this.id, this.name, this.lastName, this.email, this.password, creationDate, this.roles);
    }
}