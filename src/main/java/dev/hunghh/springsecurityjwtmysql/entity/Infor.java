package dev.hunghh.springsecurityjwtmysql.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
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

	public Infor() {
		super();
	}

	public Infor(String fullname, Date birthday, String gender, String address, String phone, String photos) {
		super();
		this.fullname = fullname;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.photos = photos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

}
