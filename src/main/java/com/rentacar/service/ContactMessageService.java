package com.rentacar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentacar.domain.ContactMessage;
import com.rentacar.exception.ResourceNotFoundException;
import com.rentacar.exception.message.ErrorMessage;
import com.rentacar.repository.ContactMessageRepository;

@Service
public class ContactMessageService {

	private ContactMessageRepository contactMessageRepository;

	@Autowired
	public ContactMessageService(ContactMessageRepository contactMessageRepository) {

		this.contactMessageRepository = contactMessageRepository;
	}

	public void saveMessage(ContactMessage contactMessage) {
		contactMessageRepository.save(contactMessage);

	}

	public List<ContactMessage> getAllMessages() {

		return contactMessageRepository.findAll();
	}

	public Page<ContactMessage> getAllMessages(Pageable pageable) {

		return contactMessageRepository.findAll(pageable);
	}

	public ContactMessage getMessageById(Long id) {
		return contactMessageRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
	}

	public void deleteContactMessage(Long id) {

		ContactMessage contactMessage = getMessageById(id);

		contactMessageRepository.delete(contactMessage);

	}

	public void updateContactMessage(Long id, ContactMessage contactMessage) {

		ContactMessage contMessage = getMessageById(id);
		
		contMessage.setName(contactMessage.getName());
		contMessage.setBody(contactMessage.getBody());
		contMessage.setEmail(contactMessage.getEmail());
		contMessage.setSubject(contactMessage.getSubject());

		contactMessageRepository.save(contMessage);
	}

}
