package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Credit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClient(Client client);
    List<Credit> findByStatut(StatutCredit statut);
    List<Credit> findByDateDemandeAfter(Date date);
    List<Credit> findByMontantGreaterThan(Double montant);
}