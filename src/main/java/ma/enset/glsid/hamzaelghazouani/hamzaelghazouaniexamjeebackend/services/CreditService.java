package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;

import java.util.Date;
import java.util.List;

public interface CreditService {
    CreditDTO save(CreditDTO creditDTO);
    CreditDTO update(Long id, CreditDTO creditDTO);
    void delete(Long id);
    CreditDTO findById(Long id);
    List<CreditDTO> findByClient(Long clientId);
    List<CreditDTO> findByStatut(StatutCredit statut);
    List<CreditDTO> findByDateDemandeAfter(Date date);
    List<CreditDTO> findByMontantGreaterThan(Double montant);
}