package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.RemboursementDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeRemboursement;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.RemboursementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/remboursements")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RemboursementRestController {
    private final RemboursementService remboursementService;

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.save(remboursementDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursement(@PathVariable Long id) {
        return ResponseEntity.ok(remboursementService.findById(id));
    }

    @GetMapping("/credit/{creditId}")
    public List<RemboursementDTO> getRemboursementsByCredit(@PathVariable Long creditId) {
        return remboursementService.findByCredit(creditId);
    }

    @GetMapping("/type/{type}")
    public List<RemboursementDTO> getRemboursementsByType(@PathVariable TypeRemboursement type) {
        return remboursementService.findByType(type);
    }

    @GetMapping("/period")
    public List<RemboursementDTO> getRemboursementsByPeriod(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
        return remboursementService.findByDateBetween(dateDebut, dateFin);
    }

    @GetMapping("/credit/{creditId}/type/{type}")
    public List<RemboursementDTO> getRemboursementsByCreditAndType(
            @PathVariable Long creditId,
            @PathVariable TypeRemboursement type) {
        return remboursementService.findByCreditAndType(creditId, type);
    }

    @GetMapping("/credit/{creditId}/total")
    public ResponseEntity<Double> getTotalRemboursements(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.calculerTotalRemboursements(creditId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(
            @PathVariable Long id,
            @RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.update(id, remboursementDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}