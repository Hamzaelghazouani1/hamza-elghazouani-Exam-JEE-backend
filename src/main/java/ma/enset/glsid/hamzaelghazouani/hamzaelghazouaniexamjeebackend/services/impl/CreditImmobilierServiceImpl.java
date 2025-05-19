package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditImmobilierDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditImmobilier;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeBien;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers.CreditImmobilierMapper;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.ClientRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.CreditImmobilierRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditImmobilierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditImmobilierServiceImpl implements CreditImmobilierService {
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final ClientRepository clientRepository;
    private final CreditImmobilierMapper creditImmobilierMapper;

    @Override
    public CreditImmobilierDTO save(CreditImmobilierDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        CreditImmobilier credit = creditImmobilierMapper.toEntity(creditDTO);
        credit.setClient(client);
        credit = creditImmobilierRepository.save(credit);
        
        return creditImmobilierMapper.toDto(credit);
    }

    @Override
    public CreditImmobilierDTO update(Long id, CreditImmobilierDTO creditDTO) {
        creditImmobilierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit immobilier non trouvé"));

        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        CreditImmobilier credit = creditImmobilierMapper.toEntity(creditDTO);
        credit.setId(id);
        credit.setClient(client);
        credit = creditImmobilierRepository.save(credit);

        return creditImmobilierMapper.toDto(credit);
    }

    @Override
    public void delete(Long id) {
        creditImmobilierRepository.deleteById(id);
    }

    @Override
    public CreditImmobilierDTO findById(Long id) {
        CreditImmobilier credit = creditImmobilierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit immobilier non trouvé"));
        return creditImmobilierMapper.toDto(credit);
    }

    @Override
    public List<CreditImmobilierDTO> findByTypeBien(TypeBien typeBien) {
        return creditImmobilierMapper.toDtoList(
            creditImmobilierRepository.findByTypeBien(typeBien)
        );
    }

    @Override
    public List<CreditImmobilierDTO> findByClientAndTypeBien(Long clientId, TypeBien typeBien) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return creditImmobilierMapper.toDtoList(
            creditImmobilierRepository.findByClientAndTypeBien(client, typeBien)
        );
    }

    @Override
    public List<CreditImmobilierDTO> findByStatutAndTypeBien(StatutCredit statut, TypeBien typeBien) {
        return creditImmobilierMapper.toDtoList(
            creditImmobilierRepository.findByStatutAndTypeBien(statut, typeBien)
        );
    }
}