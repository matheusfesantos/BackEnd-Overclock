package com.example.overclockAPI.entitys;

import com.example.overclockAPI.entitys.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "usuarios")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha_hash")
    private String senha;

    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoUsuario tipo_usuario;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data_criacao;

    @Column(name = "cpf")
    private String cpf;

    @PrePersist //incrementar automaticamente data da criação do usuario
    private void DataCriacao(){
        this.data_criacao = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipo_usuario == TipoUsuario.admin)
            return List.of(new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("USER"));

        else return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() { //LOGIN PRINCIPAL E UNICO POR USER
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {//Conta expirada
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//Conta esta ou não bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//Credenciais expiradas
        return true;
    }

    @Override
    public boolean isEnabled() {//Usuario está ativo
        return true;
    }
}