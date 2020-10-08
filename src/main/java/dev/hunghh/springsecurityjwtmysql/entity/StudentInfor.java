package dev.hunghh.springsecurityjwtmysql.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfor {
private String fullname;
private Date birthday;
private String address;
private String gender;
private String phone;
private String class_name;
}
