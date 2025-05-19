package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.*;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Clients", description = "API de gestion des clients et leurs crédits")
public class ClientRestController {
    private final ClientService clientService;
    private final CreditService creditService;
    private final CreditPersonnelService creditPersonnelService;
    private final CreditImmobilierService creditImmobilierService;
    private final CreditProfessionnelService creditProfessionnelService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/{id}/credits")
    public ResponseEntity<List<CreditDTO>> getClientCredits(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.findByClient(id));
    }

    @PostMapping("/{id}/credits/personnel")
    public ResponseEntity<CreditPersonnelDTO> addCreditPersonnel(
            @PathVariable Long id,
            @RequestBody CreditPersonnelDTO creditDTO) {
        creditDTO.setClientId(id);
        return ResponseEntity.ok(creditPersonnelService.save(creditDTO));
    }

    @PostMapping("/{id}/credits/immobilier")
    public ResponseEntity<CreditImmobilierDTO> addCreditImmobilier(
            @PathVariable Long id,
            @RequestBody CreditImmobilierDTO creditDTO) {
        creditDTO.setClientId(id);
        return ResponseEntity.ok(creditImmobilierService.save(creditDTO));
    }

    @PostMapping("/{id}/credits/professionnel")
    public ResponseEntity<CreditProfessionnelDTO> addCreditProfessionnel(
            @PathVariable Long id,
            @RequestBody CreditProfessionnelDTO creditDTO) {
        creditDTO.setClientId(id);
        return ResponseEntity.ok(creditProfessionnelService.save(creditDTO));
    }

    @GetMapping("/{id}/credits/total")
    public ResponseEntity<Double> getTotalCredits(@PathVariable Long id) {
        List<CreditDTO> credits = creditService.findByClient(id);
        double total = credits.stream()
                .mapToDouble(CreditDTO::getMontant)
                .sum();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{id}/credits/count")
    public ResponseEntity<Integer> getCreditsCount(@PathVariable Long id) {
        List<CreditDTO> credits = creditService.findByClient(id);
        return ResponseEntity.ok(credits.size());
    }
}