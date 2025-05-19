package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditImmobilierDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeBien;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditImmobilierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits/immobilier")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditImmobilierRestController {
    private final CreditImmobilierService creditImmobilierService;

    @PostMapping
    public ResponseEntity<CreditImmobilierDTO> createCredit(@RequestBody CreditImmobilierDTO creditDTO) {
        return ResponseEntity.ok(creditImmobilierService.save(creditDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditImmobilierDTO> getCredit(@PathVariable Long id) {
        return ResponseEntity.ok(creditImmobilierService.findById(id));
    }

    @GetMapping("/type-bien/{typeBien}")
    public List<CreditImmobilierDTO> getCreditsByTypeBien(@PathVariable TypeBien typeBien) {
        return creditImmobilierService.findByTypeBien(typeBien);
    }

    @GetMapping("/client/{clientId}/type-bien/{typeBien}")
    public List<CreditImmobilierDTO> getCreditsByClientAndTypeBien(
            @PathVariable Long clientId,
            @PathVariable TypeBien typeBien) {
        return creditImmobilierService.findByClientAndTypeBien(clientId, typeBien);
    }

    @GetMapping("/statut/{statut}/type-bien/{typeBien}")
    public List<CreditImmobilierDTO> getCreditsByStatutAndTypeBien(
            @PathVariable StatutCredit statut,
            @PathVariable TypeBien typeBien) {
        return creditImmobilierService.findByStatutAndTypeBien(statut, typeBien);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditImmobilierDTO> updateCredit(
            @PathVariable Long id,
            @RequestBody CreditImmobilierDTO creditDTO) {
        return ResponseEntity.ok(creditImmobilierService.update(id, creditDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditImmobilierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}