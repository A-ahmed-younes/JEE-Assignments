package ma.enset.MicroService.web;

import ma.enset.MicroService.dto.BankAccountRequestDTO;
import ma.enset.MicroService.dto.BankAccountResponseDTO;
import ma.enset.MicroService.entities.Compte;
import ma.enset.MicroService.entities.Customer;
import ma.enset.MicroService.mappers.AccountMapper;
import ma.enset.MicroService.repositories.CompteRepository;
import ma.enset.MicroService.repositories.CustomerRepository;
import ma.enset.MicroService.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller @AllArgsConstructor
public class BankServiceGraphQLController {
    private CompteRepository compteRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;
    private CustomerRepository customerRepository;
    @QueryMapping
    public List<Compte> accountsList(){
        return compteRepository.findAll();
    }
    @QueryMapping
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @QueryMapping
    public Compte findAccount(@Argument String id){
        return compteRepository.findById(id).orElseThrow(()->new RuntimeException("account not founds"));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO compte){
        return accountService.addAccount(compte);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO compte){
        return accountService.updateCompte(id,accountMapper.fromRequestDTO(compte));
    }
    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
         accountService.deleteById(id);
         return true;
    }
}