package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Client;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditImmobilier;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByTypeBien(TypeBien typeBien);
    List<CreditImmobilier> findByClientAndTypeBien(Client client, TypeBien typeBien);
    List<CreditImmobilier> findByStatutAndTypeBien(StatutCredit statut, TypeBien typeBien);
}