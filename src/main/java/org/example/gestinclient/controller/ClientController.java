package org.example.gestinclient.controller;

import org.example.gestinclient.model.Client;
import org.example.gestinclient.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/clients";
    }

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        if (q != null && !q.isBlank()) {
            model.addAttribute("clients", service.searchByNom(q));
            model.addAttribute("q", q);
        } else {
            model.addAttribute("clients", service.listAll());
        }
        return "list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("client", new Client());
        return "form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("client") Client client, BindingResult br) {
        if (br.hasErrors()) {
            return "form";
        }
        service.save(client);
        return "redirect:/clients";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Client> c = service.get(id);
        if (c.isPresent()) {
            model.addAttribute("client", c.get());
            return "form";
        }
        return "redirect:/clients";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/clients";
    }
}
