package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Credit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditMapper {

    public CreditDTO toDto(Credit credit) {
        if (credit == null) return null;
        
        CreditDTO dto = new CreditDTO();
        BeanUtils.copyProperties(credit, dto);
        
        if (credit.getClient() != null) {
            dto.setClientId(credit.getClient().getId());
        }
        
        return dto;
    }

    public Credit toEntity(CreditDTO dto) {
        if (dto == null) return null;
        
        Credit credit = new Credit() {};  // Classe anonyme car Credit est abstrait
        BeanUtils.copyProperties(dto, credit);
        
        return credit;
    }

    public List<CreditDTO> toDtoList(List<Credit> credits) {
        if (credits == null) return new ArrayList<>();
        return credits.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}