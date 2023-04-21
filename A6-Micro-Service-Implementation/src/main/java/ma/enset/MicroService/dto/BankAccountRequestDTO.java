package ma.enset.MicroService.dto;

import ma.enset.MicroService.enums.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountRequestDTO {
    private String id;
    private Date createDate;
    private Double balance;
    private String currency;
    private TypeCompte type;
}
