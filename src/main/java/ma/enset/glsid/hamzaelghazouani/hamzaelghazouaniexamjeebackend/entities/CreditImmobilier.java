package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeBien;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;
}