package dev.hunghh.springsecurityjwtmysql.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class User extends BaseEntity {

    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();


    @NotNull
    @Column(name = "id_infor")
    private Long id_infor;

    @NotNull
    @Column(name = "id_role")
    private Long id_role;

    @NotNull
    @Column(name = "id_class")
    private Long id_class;
}
