package org.example.gestinclient.service;

import org.example.gestinclient.model.Client;
import org.example.gestinclient.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    public List<Client> listAll() { return repo.findAll(); }
    public List<Client> searchByNom(String nom) { return repo.findByNomContainingIgnoreCase(nom); }
    public Client save(Client client) { return repo.save(client); }
    public Optional<Client> get(Long id) { return repo.findById(id); }
    public void delete(Long id) { repo.deleteById(id); }
}
