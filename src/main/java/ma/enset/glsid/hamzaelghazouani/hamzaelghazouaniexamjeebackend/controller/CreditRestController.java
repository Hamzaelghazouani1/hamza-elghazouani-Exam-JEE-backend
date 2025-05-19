package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/credits")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Crédits", description = "API de gestion des crédits")
public class CreditRestController {
    private final CreditService creditService;
    
    

    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) StatutCredit statut,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateApres,
            @RequestParam(required = false) Double montantMin) {

        if (clientId != null) {
            return ResponseEntity.ok(creditService.findByClient(clientId));
        } else if (statut != null) {
            return ResponseEntity.ok(creditService.findByStatut(statut));
        } else if (dateApres != null) {
            return ResponseEntity.ok(creditService.findByDateDemandeAfter(dateApres));
        } else if (montantMin != null) {
            return ResponseEntity.ok(creditService.findByMontantGreaterThan(montantMin));
        }
        return ResponseEntity.ok(creditService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCredit(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@RequestBody CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.save(creditDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditDTO> updateCredit(@PathVariable Long id, @RequestBody CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.update(id, creditDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.delete(id);
        return ResponseEntity.noContent().build();
    }
}