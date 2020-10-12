package dev.hunghh.springsecurityjwtmysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "infor")
public class Infor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 30, message = " Fullname must be equal to or greater than 2 characters and less than 30 characters")
	@Column(name = "fullname")
	private String fullname;

	@NotNull
	@Column(name = "birthday")
	private Date birthday;

	@NotNull
	@Size(min = 2, max = 10, message = " Gender must be equal to or greater than 8 characters and less than 10 characters")
	@Column(name = "gender")
	private String gender;

	@NotNull
	@Size(min = 2, max = 30, message = " Addres must be equal to or greater than 2 characters and less than 30 characters")
	@Column(name = "address")
	private String address;

	@NotNull
	@Size(min = 2, max = 15, message = " Phone must be equal to or greater than 2 characters and less than 15 characters")
	@Column(name = "phone")
	private String phone;


	@Column(columnDefinition = "boolean default true")
	private boolean active = true;


	@Column(name = "photos")
	private String photos;


}
