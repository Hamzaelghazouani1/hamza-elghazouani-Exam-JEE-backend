package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.RemboursementDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Credit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Remboursement;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeRemboursement;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers.RemboursementMapper;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.CreditRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.RemboursementRepository;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.RemboursementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemboursementServiceImpl implements RemboursementService {
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;

    @Override
    public RemboursementDTO save(RemboursementDTO remboursementDTO) {
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement.setCredit(credit);
        remboursement = remboursementRepository.save(remboursement);

        return remboursementMapper.toDto(remboursement);
    }

    @Override
    public RemboursementDTO update(Long id, RemboursementDTO remboursementDTO) {
        remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement non trouvé"));

        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement.setId(id);
        remboursement.setCredit(credit);
        remboursement = remboursementRepository.save(remboursement);

        return remboursementMapper.toDto(remboursement);
    }

    @Override
    public void delete(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    public RemboursementDTO findById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement non trouvé"));
        return remboursementMapper.toDto(remboursement);
    }

    @Override
    public List<RemboursementDTO> findByCredit(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return remboursementMapper.toDtoList(
            remboursementRepository.findByCredit(credit)
        );
    }

    @Override
    public List<RemboursementDTO> findByType(TypeRemboursement type) {
        return remboursementMapper.toDtoList(
            remboursementRepository.findByType(type)
        );
    }

    @Override
    public List<RemboursementDTO> findByDateBetween(Date dateDebut, Date dateFin) {
        return remboursementMapper.toDtoList(
            remboursementRepository.findByDateBetween(dateDebut, dateFin)
        );
    }

    @Override
    public List<RemboursementDTO> findByCreditAndType(Long creditId, TypeRemboursement type) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return remboursementMapper.toDtoList(
            remboursementRepository.findByCreditAndType(credit, type)
        );
    }

    @Override
    public Double calculerTotalRemboursements(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return remboursementRepository.calculerTotalRemboursements(credit);
    }

    @Override
    public List<RemboursementDTO> findAll() {
        return remboursementMapper.toDtoList(remboursementRepository.findAll());
    }
}