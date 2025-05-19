package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreditPersonnel extends Credit {
    private String motif;
}