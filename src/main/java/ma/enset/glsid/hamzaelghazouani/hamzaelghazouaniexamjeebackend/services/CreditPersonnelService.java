package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditPersonnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import java.util.List;

public interface CreditPersonnelService {
    CreditPersonnelDTO save(CreditPersonnelDTO creditDTO);
    CreditPersonnelDTO update(Long id, CreditPersonnelDTO creditDTO);
    void delete(Long id);
    CreditPersonnelDTO findById(Long id);
    List<CreditPersonnelDTO> findByMotif(String motif);
    List<CreditPersonnelDTO> findByClientAndStatut(Long clientId, StatutCredit statut);
    List<CreditPersonnelDTO> findByMontantBetween(Double montantMin, Double montantMax);
}