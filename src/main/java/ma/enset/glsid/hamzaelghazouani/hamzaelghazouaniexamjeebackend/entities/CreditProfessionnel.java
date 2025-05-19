package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}