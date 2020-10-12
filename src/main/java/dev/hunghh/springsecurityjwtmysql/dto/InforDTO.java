package dev.hunghh.springsecurityjwtmysql.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InforDTO {

	private Long id;

	private String fullname;

	private Date birthday;

	private String gender;

	private String address;

	private String phone;

	private boolean active = true;

	private String photos;

}
