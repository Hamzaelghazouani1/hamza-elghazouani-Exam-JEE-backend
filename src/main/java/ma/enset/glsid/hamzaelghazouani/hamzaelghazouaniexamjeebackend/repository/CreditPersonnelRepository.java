package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditPersonnel;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {
    List<CreditPersonnel> findByMotif(String motif);
    List<CreditPersonnel> findByClientAndStatut(Client client, StatutCredit statut);
    List<CreditPersonnel> findByMontantBetween(Double montantMin, Double montantMax);
}