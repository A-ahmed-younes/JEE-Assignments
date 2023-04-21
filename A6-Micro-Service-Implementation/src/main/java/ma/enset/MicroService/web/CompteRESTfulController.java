package ma.enset.MicroService.web;

import ma.enset.MicroService.dto.BankAccountRequestDTO;
import ma.enset.MicroService.dto.BankAccountResponseDTO;
import ma.enset.MicroService.entities.Compte;
import ma.enset.MicroService.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CompteRESTfulController {
    private AccountService accountService;


    @GetMapping("/compte")
    public List<BankAccountResponseDTO> comptes() {
        return accountService.findAll();
    }

    @GetMapping("/compte/{id}")
    public BankAccountResponseDTO compte(@PathVariable String id) {
        return accountService.findById(id);
    }

    @PostMapping("/compte")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO compte) {
        return accountService.addAccount(compte);
    }

    @PutMapping("/compte/{id}")
    public BankAccountResponseDTO update(@PathVariable String id, @RequestBody Compte compte) {
        return accountService.updateCompte(id, compte);
    }

    @DeleteMapping("/compte/{id}")
    public void deleteCompte(@PathVariable String id) {
        accountService.deleteById(id);
    }
}