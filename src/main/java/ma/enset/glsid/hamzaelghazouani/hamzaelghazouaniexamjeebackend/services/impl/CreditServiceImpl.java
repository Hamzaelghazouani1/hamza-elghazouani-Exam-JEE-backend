package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Credit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers.CreditMapper;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.ClientRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.CreditRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    @Override
    public CreditDTO save(CreditDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setClient(client);
        Credit savedCredit = creditRepository.save(credit);

        return creditMapper.toDto(savedCredit);
    }

    @Override
    public CreditDTO update(Long id, CreditDTO creditDTO) {
        creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setId(id);
        credit.setClient(client);
        Credit updatedCredit = creditRepository.save(credit);

        return creditMapper.toDto(updatedCredit);
    }

    @Override
    public void delete(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> findAll() {
        return creditMapper.toDtoList(creditRepository.findAll());
    }

    @Override
    public CreditDTO findById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return creditMapper.toDto(credit);
    }

    @Override
    public List<CreditDTO> findByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return creditMapper.toDtoList(creditRepository.findByClient(client));
    }

    @Override
    public List<CreditDTO> findByStatut(StatutCredit statut) {
        return creditMapper.toDtoList(creditRepository.findByStatut(statut));
    }

    @Override
    public List<CreditDTO> findByDateDemandeAfter(Date date) {
        return creditMapper.toDtoList(creditRepository.findByDateDemandeAfter(date));
    }

    @Override
    public List<CreditDTO> findByMontantGreaterThan(Double montant) {
        return creditMapper.toDtoList(creditRepository.findByMontantGreaterThan(montant));
    }
}