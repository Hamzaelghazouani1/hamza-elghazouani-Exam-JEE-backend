package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    
    @Enumerated(EnumType.STRING)
    private StatutCredit statut;
    
    @Temporal(TemporalType.DATE)
    private Date dateAcceptation;
    
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;
}