package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditImmobilierDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditImmobilier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditImmobilierMapper {

    public CreditImmobilierDTO toDto(CreditImmobilier credit) {
        if (credit == null) return null;
        
        CreditImmobilierDTO dto = new CreditImmobilierDTO();
        BeanUtils.copyProperties(credit, dto);
        
        if (credit.getClient() != null) {
            dto.setClientId(credit.getClient().getId());
        }
        
        return dto;
    }

    public CreditImmobilier toEntity(CreditImmobilierDTO dto) {
        if (dto == null) return null;
        
        CreditImmobilier credit = new CreditImmobilier();
        BeanUtils.copyProperties(dto, credit);
        
        return credit;
    }

    public List<CreditImmobilierDTO> toDtoList(List<CreditImmobilier> credits) {
        if (credits == null) return new ArrayList<>();
        return credits.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}