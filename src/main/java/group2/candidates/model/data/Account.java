package group2.candidates.model.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "Username", length = 50)
    private String username;
    @Column(name = "Password", length = 50, nullable = false)
    private String password;
    @Column(name = "FirstName", length = 50)
    private String firstName;
    @Column(name = "LastName", length = 50)
    private String lastName;
    @Column(name = "Enabled")
    private Integer enabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Accounts_Roles",
            joinColumns = @JoinColumn(name = "Username"),
            inverseJoinColumns = @JoinColumn(name = "Roles_Id"))
    private List<Authority> authorities = new ArrayList<>();
}
