package com.rentacar.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rentacar.domain.ContactMessage;
import com.rentacar.dto.ContactMessageDTO;
import com.rentacar.dto.request.ContactMessageRequest;

@Mapper(componentModel = "spring")
public interface ContactMessageMapper {

	ContactMessageDTO contactMessageToDTO(ContactMessage contactMessage);

	@Mapping(target = "id", ignore = true)
	ContactMessage contactMessageRequestToContactMessage(ContactMessageRequest contactMessageRequest);

	// List<ContactMessage> --> List<ContactMessageDTO>

	List<ContactMessageDTO> map(List<ContactMessage> contactMessageList);

}
