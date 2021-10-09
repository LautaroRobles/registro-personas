package registropersonas.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Getter
@Setter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Transient
    @JsonIgnore
    protected boolean accountNonExpired = true;

    @Transient
    @JsonIgnore
    protected boolean accountNonLocked = true;

    @Transient
    @JsonIgnore
    protected boolean credentialsNonExpired = true;

    @Transient
    @JsonIgnore
    protected boolean enabled = true;

    @Transient
    @JsonIgnore
    protected Collection<? extends GrantedAuthority> authorities;
}
