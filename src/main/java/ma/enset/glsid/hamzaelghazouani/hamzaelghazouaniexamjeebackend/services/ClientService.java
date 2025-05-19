package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO save(ClientDTO clientDTO);
    ClientDTO update(Long id, ClientDTO clientDTO);
    void delete(Long id);
    ClientDTO findById(Long id);
    ClientDTO findByEmail(String email);
    List<ClientDTO> findAll();
    List<ClientDTO> searchByNom(String nom);
}