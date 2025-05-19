package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend;

import lombok.extern.slf4j.Slf4j;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.*;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.StatutCredit;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeBien;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.enums.TypeRemboursement;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class HamzaElghazouaniExamJeeBackendApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final RemboursementRepository remboursementRepository;

    public HamzaElghazouaniExamJeeBackendApplication(
            ClientRepository clientRepository,
            CreditPersonnelRepository creditPersonnelRepository,
            CreditImmobilierRepository creditImmobilierRepository,
            RemboursementRepository remboursementRepository) {
        this.clientRepository = clientRepository;
        this.creditPersonnelRepository = creditPersonnelRepository;
        this.creditImmobilierRepository = creditImmobilierRepository;
        this.remboursementRepository = remboursementRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HamzaElghazouaniExamJeeBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Client client1 = new Client(null, "Hassan Ahmed", "hassan.ahmed@email.com", new ArrayList<>());
        Client client2 = new Client(null, "Fatima Zahra", "fatima.zahra@email.com", new ArrayList<>());
        Client client3 = new Client(null, "Karim Benali", "karim.benali@email.com", new ArrayList<>());

        clientRepository.saveAll(Arrays.asList(client1, client2, client3));

        CreditPersonnel creditPerso1 = new CreditPersonnel();
        creditPerso1.setClient(client1);
        creditPerso1.setMontant(50000.0);
        creditPerso1.setTauxInteret(4.5);
        creditPerso1.setDureeRemboursement(24);
        creditPerso1.setDateDemande(new Date());
        creditPerso1.setStatut(StatutCredit.ACCEPTE);
        creditPerso1.setDateAcceptation(new Date());
        creditPerso1.setMotif("Achat voiture");
        creditPerso1.setRemboursements(new ArrayList<>());

        CreditPersonnel creditPerso2 = new CreditPersonnel();
        creditPerso2.setClient(client1);
        creditPerso2.setMontant(20000.0);
        creditPerso2.setTauxInteret(5.0);
        creditPerso2.setDureeRemboursement(12);
        creditPerso2.setDateDemande(new Date());
        creditPerso2.setStatut(StatutCredit.EN_COURS);
        creditPerso2.setMotif("Travaux maison");
        creditPerso2.setRemboursements(new ArrayList<>());

        creditPersonnelRepository.saveAll(Arrays.asList(creditPerso1, creditPerso2));

        // Création des crédits immobiliers
        CreditImmobilier creditImmo1 = new CreditImmobilier();
        creditImmo1.setClient(client2);
        creditImmo1.setMontant(800000.0);
        creditImmo1.setTauxInteret(2.5);
        creditImmo1.setDureeRemboursement(240);
        creditImmo1.setDateDemande(new Date());
        creditImmo1.setStatut(StatutCredit.ACCEPTE);
        creditImmo1.setDateAcceptation(new Date());
        creditImmo1.setTypeBien(TypeBien.APPARTEMENT);
        creditImmo1.setRemboursements(new ArrayList<>());

        creditImmobilierRepository.save(creditImmo1);

        createRemboursements(creditPerso1);
        createRemboursements(creditImmo1);
    }

    private void createRemboursements(Credit credit) {
        double tauxMensuel = credit.getTauxInteret() / 12 / 100;
        double mensualite = (credit.getMontant() * tauxMensuel * 
                Math.pow(1 + tauxMensuel, credit.getDureeRemboursement())) / 
                (Math.pow(1 + tauxMensuel, credit.getDureeRemboursement()) - 1);

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 3; i++) {
            calendar.add(Calendar.MONTH, 1);
            Remboursement remboursement = new Remboursement();
            remboursement.setCredit(credit);
            remboursement.setMontant(mensualite);
            remboursement.setDate(calendar.getTime());
            remboursement.setType(TypeRemboursement.REMBOURSEMENT_ANTICIPE);
            remboursementRepository.save(remboursement);
        }

        calendar.add(Calendar.MONTH, 1);
        Remboursement remboursementAnticipe = new Remboursement();
        remboursementAnticipe.setCredit(credit);
        remboursementAnticipe.setMontant(5000.0);
        remboursementAnticipe.setDate(calendar.getTime());
        remboursementAnticipe.setType(TypeRemboursement.MENSUALITE);
        remboursementRepository.save(remboursementAnticipe);
    }
}