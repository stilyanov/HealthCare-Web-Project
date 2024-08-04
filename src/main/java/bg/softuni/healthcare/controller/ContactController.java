package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.ContactDTO;
import bg.softuni.healthcare.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @ModelAttribute("contactDTO")
    public ContactDTO contactDTO() {
        return new ContactDTO();
    }

    @GetMapping("/contact")
    public String viewContacts(Model model) {
        List<ContactDTO> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contact";
    }

    @PostMapping("/contact")
    public String saveContact(@Valid ContactDTO contactDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("contactDTO", contactDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactDTO", bindingResult);
            return "redirect:/contact";
        }
        contactService.saveContact(contactDTO);
        return "redirect:/";
    }

}
