package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.mappers;

import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.RemboursementDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.Remboursement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class RemboursementMapper {

    public RemboursementDTO toDto(Remboursement remboursement) {
        if (remboursement == null) return null;
        
        RemboursementDTO dto = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, dto);
        
        if (remboursement.getCredit() != null) {
            dto.setCreditId(remboursement.getCredit().getId());
        }
        
        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto) {
        if (dto == null) return null;
        
        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(dto, remboursement);
        
        return remboursement;
    }

    public List<RemboursementDTO> toDtoList(List<Remboursement> remboursements) {
        if (remboursements == null) return new ArrayList<>();
        return remboursements.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}