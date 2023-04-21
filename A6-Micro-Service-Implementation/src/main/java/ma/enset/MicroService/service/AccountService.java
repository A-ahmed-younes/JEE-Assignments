package ma.enset.MicroService.service;

import ma.enset.MicroService.dto.BankAccountRequestDTO;
import ma.enset.MicroService.dto.BankAccountResponseDTO;
import ma.enset.MicroService.entities.Compte;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    List<BankAccountResponseDTO> findAll();

    BankAccountResponseDTO findById(String id);

    void deleteById(String id);

    BankAccountResponseDTO updateCompte(String id, Compte compte);
}
