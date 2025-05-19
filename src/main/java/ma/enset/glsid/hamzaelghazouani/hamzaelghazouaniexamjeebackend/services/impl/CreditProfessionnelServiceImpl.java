package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditProfessionnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditProfessionnel;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers.CreditProfessionnelMapper;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.ClientRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.CreditProfessionnelRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditProfessionnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditProfessionnelServiceImpl implements CreditProfessionnelService {
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final ClientRepository clientRepository;
    private final CreditProfessionnelMapper creditProfessionnelMapper;

    @Override
    public CreditProfessionnelDTO save(CreditProfessionnelDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        CreditProfessionnel credit = creditProfessionnelMapper.toEntity(creditDTO);
        credit.setClient(client);
        credit = creditProfessionnelRepository.save(credit);

        return creditProfessionnelMapper.toDto(credit);
    }

    @Override
    public CreditProfessionnelDTO update(Long id, CreditProfessionnelDTO creditDTO) {
        creditProfessionnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit professionnel non trouvé"));

        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        CreditProfessionnel credit = creditProfessionnelMapper.toEntity(creditDTO);
        credit.setId(id);
        credit.setClient(client);
        credit = creditProfessionnelRepository.save(credit);

        return creditProfessionnelMapper.toDto(credit);
    }

    @Override
    public void delete(Long id) {
        creditProfessionnelRepository.deleteById(id);
    }

    @Override
    public CreditProfessionnelDTO findById(Long id) {
        CreditProfessionnel credit = creditProfessionnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit professionnel non trouvé"));
        return creditProfessionnelMapper.toDto(credit);
    }

    @Override
    public List<CreditProfessionnelDTO> findByRaisonSociale(String raisonSociale) {
        return creditProfessionnelMapper.toDtoList(
            creditProfessionnelRepository.findByRaisonSociale(raisonSociale)
        );
    }

    @Override
    public List<CreditProfessionnelDTO> findByMotif(String motif) {
        return creditProfessionnelMapper.toDtoList(
            creditProfessionnelRepository.findByMotif(motif)
        );
    }

    @Override
    public List<CreditProfessionnelDTO> searchByRaisonSociale(String raisonSociale) {
        return creditProfessionnelMapper.toDtoList(
            creditProfessionnelRepository.findByRaisonSocialeContainingIgnoreCase(raisonSociale)
        );
    }
}