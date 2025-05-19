package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreditPersonnel extends Credit {
    private String motif;
}