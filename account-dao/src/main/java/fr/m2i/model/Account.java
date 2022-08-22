package fr.m2i.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO.setScale(2);
    
    public void copy(Account accountData){
        if(accountData.getFirstName() != null){
            this.setFirstName(accountData.getFirstName());
        }
        
        if(accountData.getLastName()!= null){
            this.setLastName(accountData.getLastName());
        }
        
        if(accountData.getEmail()!= null){
            this.setEmail(accountData.getEmail());
        }
        
        if(accountData.getBirthday()!= null){
            this.setBirthday(accountData.getBirthday());
        }
        
        if(accountData.getGender()!= null){
            this.setGender(accountData.getGender());
        }
        
        if(accountData.getCreationTime()!= null){
            this.setCreationTime(accountData.getCreationTime());
        }
        
        if(accountData.getBalance()!= null){
            this.setBalance(accountData.getBalance());
        }
    }
}