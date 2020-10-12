package dev.hunghh.springsecurityjwtmysql.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "t_user_role")
public class userRole {

    @Id
    @NotNull
    private long user_id;
    @NotNull
    private long role_id;
}
