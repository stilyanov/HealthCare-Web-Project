package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAllContacts();

    void saveContact(ContactDTO contactDTO);

    void deleteContact(Long id);
}
