package tr.com.obss.jip2022.jip_backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@Entity
public class User {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Size(min = 3, max = 20)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Book> favouriteList;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Book> readList;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
