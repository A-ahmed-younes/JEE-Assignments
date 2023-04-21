package ma.enset.MicroService.mappers;

import ma.enset.MicroService.dto.BankAccountRequestDTO;
import ma.enset.MicroService.dto.BankAccountResponseDTO;
import ma.enset.MicroService.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(Compte compte) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(compte, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public Compte fromRequestDTO(BankAccountRequestDTO bankAccountRequestDTO) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(bankAccountRequestDTO, compte);
        return compte;
    }
}
