package group2.candidates.model.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(length = 50)
    private String username;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column
    private Integer enabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Authority> authorities = new ArrayList<>();
}
