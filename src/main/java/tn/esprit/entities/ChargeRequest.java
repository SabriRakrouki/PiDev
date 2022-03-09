package tn.esprit.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRequest {

    public enum Currency {
        EUR, USD;
    }
    private String name;
    private String email;
    private String description;
    private int amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String cardNumber;
    private String cvc;
    private String exMonth;
    private String exYear;
    
    
    
    
   
}
