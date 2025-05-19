package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.RemboursementDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeRemboursement;
import java.util.Date;
import java.util.List;

public interface RemboursementService {
    RemboursementDTO save(RemboursementDTO remboursementDTO);
    RemboursementDTO update(Long id, RemboursementDTO remboursementDTO);
    void delete(Long id);
    RemboursementDTO findById(Long id);
    List<RemboursementDTO> findByCredit(Long creditId);
    List<RemboursementDTO> findByType(TypeRemboursement type);
    List<RemboursementDTO> findByDateBetween(Date dateDebut, Date dateFin);
    List<RemboursementDTO> findByCreditAndType(Long creditId, TypeRemboursement type);
    Double calculerTotalRemboursements(Long creditId);
}