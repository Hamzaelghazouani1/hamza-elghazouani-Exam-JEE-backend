package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditProfessionnelDTO;
import java.util.List;

public interface CreditProfessionnelService {
    CreditProfessionnelDTO save(CreditProfessionnelDTO creditDTO);
    CreditProfessionnelDTO update(Long id, CreditProfessionnelDTO creditDTO);
    void delete(Long id);
    CreditProfessionnelDTO findById(Long id);
    List<CreditProfessionnelDTO> findByRaisonSociale(String raisonSociale);
    List<CreditProfessionnelDTO> findByMotif(String motif);
    List<CreditProfessionnelDTO> searchByRaisonSociale(String raisonSociale);
}