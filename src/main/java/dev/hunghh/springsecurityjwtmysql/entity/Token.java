package dev.hunghh.springsecurityjwtmysql.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_token")
@Getter
@Setter
public class Token extends BaseEntity{


    @Column(length = 1000)
    private String token;
    private Date tokenExpDate;
    @Column(name = "id_user")
    private long id_user;
    @Column(name = "id_infor")
    private long id_infor;
    @Column(name = "id_role")
    private long id_role;
}
