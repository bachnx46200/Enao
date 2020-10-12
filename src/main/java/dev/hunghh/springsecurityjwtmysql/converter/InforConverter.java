package dev.hunghh.springsecurityjwtmysql.converter;

import java.util.List;
import java.util.stream.Collectors;

import dev.hunghh.springsecurityjwtmysql.dto.InforDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Infor;

public class InforConverter {

	public static List<InforDTO> convertToInforDTO(List<Infor> infor){
		return infor.stream().map(s ->
		InforDTO.builder()
		.id(s.getId())
		.fullname(s.getFullname())
		.birthday(s.getBirthday())
		.gender(s.getGender())
		.address(s.getAddress())
		.phone(s.getPhone())
		.active(s.isActive())
		.photos(s.getPhotos())
		.build()
		).collect(Collectors.toList());
	}
	
	public static Infor convertToInfor(InforDTO inforDTO) {
		Infor infor = new Infor();
		infor.setId(inforDTO.getId());
		infor.setFullname(inforDTO.getFullname());
		infor.setBirthday(inforDTO.getBirthday());
		infor.setGender(inforDTO.getGender());
		infor.setAddress(inforDTO.getAddress());
		infor.setPhone(inforDTO.getPhone());
		infor.setActive(inforDTO.isActive());
		infor.setPhotos(inforDTO.getPhotos());
		return infor;
	}
}
