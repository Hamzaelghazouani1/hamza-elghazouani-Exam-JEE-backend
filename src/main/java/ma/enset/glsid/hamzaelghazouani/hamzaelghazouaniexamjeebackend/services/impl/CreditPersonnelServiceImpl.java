package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditPersonnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditPersonnel;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers.CreditPersonnelMapper;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.ClientRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.CreditPersonnelRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditPersonnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditPersonnelServiceImpl implements CreditPersonnelService {
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final ClientRepository clientRepository;
    private final CreditPersonnelMapper creditPersonnelMapper;

    @Override
    public CreditPersonnelDTO save(CreditPersonnelDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        
        CreditPersonnel credit = creditPersonnelMapper.toEntity(creditDTO);
        credit.setClient(client);
        CreditPersonnel savedCredit = creditPersonnelRepository.save(credit);
        
        return creditPersonnelMapper.toDto(savedCredit);
    }

    @Override
    public CreditPersonnelDTO update(Long id, CreditPersonnelDTO creditDTO) {
        CreditPersonnel existingCredit = creditPersonnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit personnel non trouvé"));
        
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        
        CreditPersonnel updatedCredit = creditPersonnelMapper.toEntity(creditDTO);
        updatedCredit.setId(id);
        updatedCredit.setClient(client);
        updatedCredit = creditPersonnelRepository.save(updatedCredit);
        
        return creditPersonnelMapper.toDto(updatedCredit);
    }

    @Override
    public void delete(Long id) {
        creditPersonnelRepository.deleteById(id);
    }

    @Override
    public CreditPersonnelDTO findById(Long id) {
        CreditPersonnel credit = creditPersonnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit personnel non trouvé"));
        return creditPersonnelMapper.toDto(credit);
    }

    @Override
    public List<CreditPersonnelDTO> findByMotif(String motif) {
        List<CreditPersonnel> credits = creditPersonnelRepository.findByMotif(motif);
        return creditPersonnelMapper.toDtoList(credits);
    }

    @Override
    public List<CreditPersonnelDTO> findByClientAndStatut(Long clientId, StatutCredit statut) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        List<CreditPersonnel> credits = creditPersonnelRepository.findByClientAndStatut(client, statut);
        return creditPersonnelMapper.toDtoList(credits);
    }

    @Override
    public List<CreditPersonnelDTO> findByMontantBetween(Double montantMin, Double montantMax) {
        List<CreditPersonnel> credits = creditPersonnelRepository.findByMontantBetween(montantMin, montantMax);
        return creditPersonnelMapper.toDtoList(credits);
    }
}