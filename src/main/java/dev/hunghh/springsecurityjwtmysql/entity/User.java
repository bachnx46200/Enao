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

    @NotNull
    private String username;
@NotNull
    private String password;



    @Column(name = "id_infor")
    private Long id_infor;




    @Column(name = "id_role")
    private String role;


    @Column(name = "id_class")
    private Long id_class;
}
