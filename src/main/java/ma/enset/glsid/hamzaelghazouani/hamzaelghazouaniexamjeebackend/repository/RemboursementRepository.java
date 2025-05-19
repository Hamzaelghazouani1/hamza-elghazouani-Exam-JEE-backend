package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Credit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Remboursement;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCredit(Credit credit);
    List<Remboursement> findByType(TypeRemboursement type);
    List<Remboursement> findByDateBetween(Date dateDebut, Date dateFin);
    List<Remboursement> findByCreditAndType(Credit credit, TypeRemboursement type);
    @Query("SELECT SUM(r.montant) FROM Remboursement r WHERE r.credit = :credit")
    Double calculerTotalRemboursements(@Param("credit") Credit credit);
}