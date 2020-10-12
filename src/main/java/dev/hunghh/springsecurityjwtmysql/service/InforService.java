package dev.hunghh.springsecurityjwtmysql.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.hunghh.springsecurityjwtmysql.dto.InforDTO;

public interface InforService {
	
	List<InforDTO> getInfors(Date fromDate, Date toDate,
String fullname, String gender,String address,String phone,Pageable pageable);

}
