package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import lombok.RequiredArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.CreditProfessionnelDTO;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.CreditProfessionnelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits/professionnel")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditProfessionnelRestController {
    private final CreditProfessionnelService creditProfessionnelService;

    @GetMapping("/raison-sociale/{raisonSociale}")
    public List<CreditProfessionnelDTO> getByRaisonSociale(@PathVariable String raisonSociale) {
        return creditProfessionnelService.findByRaisonSociale(raisonSociale);
    }

    @GetMapping("/motif/{motif}")
    public List<CreditProfessionnelDTO> getByMotif(@PathVariable String motif) {
        return creditProfessionnelService.findByMotif(motif);
    }

    @GetMapping("/search/raison-sociale")
    public List<CreditProfessionnelDTO> searchByRaisonSociale(@RequestParam String query) {
        return creditProfessionnelService.searchByRaisonSociale(query);
    }
}