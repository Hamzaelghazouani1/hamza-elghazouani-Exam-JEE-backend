package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditPersonnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditPersonnelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits/personnel")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Crédits Personnels", description = "API de gestion des crédits personnels")
public class CreditPersonnelRestController {
    private final CreditPersonnelService creditPersonnelService;

    @GetMapping("/motif/{motif}")
    public List<CreditPersonnelDTO> getByMotif(@PathVariable String motif) {
        return creditPersonnelService.findByMotif(motif);
    }

    @GetMapping("/client/{clientId}/statut/{statut}")
    public List<CreditPersonnelDTO> getByClientAndStatut(
            @PathVariable Long clientId,
            @PathVariable StatutCredit statut) {
        return creditPersonnelService.findByClientAndStatut(clientId, statut);
    }

    @GetMapping("/montant")
    public List<CreditPersonnelDTO> getByMontantRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        return creditPersonnelService.findByMontantBetween(min, max);
    }
}