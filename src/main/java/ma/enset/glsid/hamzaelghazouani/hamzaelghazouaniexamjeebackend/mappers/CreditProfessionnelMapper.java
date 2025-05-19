package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditProfessionnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.CreditProfessionnel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditProfessionnelMapper {

    public CreditProfessionnelDTO toDto(CreditProfessionnel credit) {
        if (credit == null) return null;
        
        CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
        BeanUtils.copyProperties(credit, dto);
        
        if (credit.getClient() != null) {
            dto.setClientId(credit.getClient().getId());
        }
        
        return dto;
    }

    public CreditProfessionnel toEntity(CreditProfessionnelDTO dto) {
        if (dto == null) return null;
        
        CreditProfessionnel credit = new CreditProfessionnel();
        BeanUtils.copyProperties(dto, credit);
        
        return credit;
    }

    public List<CreditProfessionnelDTO> toDtoList(List<CreditProfessionnel> credits) {
        if (credits == null) return new ArrayList<>();
        return credits.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}