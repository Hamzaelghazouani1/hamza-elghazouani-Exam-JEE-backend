package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditPersonnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditPersonnel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditPersonnelMapper {

    public CreditPersonnelDTO toDto(CreditPersonnel credit) {
        if (credit == null) return null;
        
        CreditPersonnelDTO dto = new CreditPersonnelDTO();
        BeanUtils.copyProperties(credit, dto);
        
        if (credit.getClient() != null) {
            dto.setClientId(credit.getClient().getId());
        }
        
        return dto;
    }

    public CreditPersonnel toEntity(CreditPersonnelDTO dto) {
        if (dto == null) return null;
        
        CreditPersonnel credit = new CreditPersonnel();
        BeanUtils.copyProperties(dto, credit);
        
        return credit;
    }

    public List<CreditPersonnelDTO> toDtoList(List<CreditPersonnel> credits) {
        if (credits == null) return new ArrayList<>();
        return credits.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}