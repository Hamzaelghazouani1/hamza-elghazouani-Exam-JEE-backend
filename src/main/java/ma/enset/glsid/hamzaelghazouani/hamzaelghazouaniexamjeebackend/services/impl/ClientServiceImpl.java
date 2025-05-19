package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.ClientDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers.ClientMapper;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.ClientRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        
        Client updatedClient = clientMapper.toEntity(clientDTO);
        updatedClient.setId(id);
        updatedClient = clientRepository.save(updatedClient);
        
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO findByEmail(String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients);
    }

    @Override
    public List<ClientDTO> searchByNom(String nom) {
        List<Client> clients = clientRepository.findByNomContainingIgnoreCase(nom);
        return clientMapper.toDtoList(clients);
    }
}