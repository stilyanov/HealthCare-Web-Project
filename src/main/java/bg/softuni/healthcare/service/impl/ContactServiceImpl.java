package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.ContactDTO;
import bg.softuni.healthcare.model.entity.ContactEntity;
import bg.softuni.healthcare.repository.ContactRepository;
import bg.softuni.healthcare.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .toList();
    }

    @Override
    public void saveContact(ContactDTO contactDTO) {
        ContactEntity contact = modelMapper.map(contactDTO, ContactEntity.class);
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
