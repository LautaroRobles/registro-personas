package registropersonas.modelo;

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
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Transient
    protected boolean accountNonExpired = true;

    @Transient
    protected boolean accountNonLocked = true;

    @Transient
    protected boolean credentialsNonExpired = true;

    @Transient
    protected boolean enabled = true;

    @Transient
    protected Collection<? extends GrantedAuthority> authorities;
}
