package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditImmobilierDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeBien;
import java.util.List;

public interface CreditImmobilierService {
    CreditImmobilierDTO save(CreditImmobilierDTO creditDTO);
    CreditImmobilierDTO update(Long id, CreditImmobilierDTO creditDTO);
    void delete(Long id);
    CreditImmobilierDTO findById(Long id);
    List<CreditImmobilierDTO> findByTypeBien(TypeBien typeBien);
    List<CreditImmobilierDTO> findByClientAndTypeBien(Long clientId, TypeBien typeBien);
    List<CreditImmobilierDTO> findByStatutAndTypeBien(StatutCredit statut, TypeBien typeBien);
}