package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.ClientDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientMapper {
    private final CreditMapper creditMapper;

    public ClientDTO toDto(Client client) {
        if (client == null) return null;
        
        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);
        
        if (client.getCredits() != null) {
            dto.setCredits(client.getCredits().stream()
                    .map(creditMapper::toDto)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        
        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        
        if (dto.getCredits() != null) {
            client.setCredits(dto.getCredits().stream()
                    .map(creditMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        
        return client;
    }

    public List<ClientDTO> toDtoList(List<Client> clients) {
        if (clients == null) return new ArrayList<>();
        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}