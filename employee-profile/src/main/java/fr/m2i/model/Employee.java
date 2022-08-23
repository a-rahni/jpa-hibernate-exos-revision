package fr.m2i.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * todo:
 * - Implémenter un constructeur sans arguments
 * - Implémenter les getter et les setter
 *
 * - Configurer l'entité JPA
 * - Specifier le nom de la table: "employee"
 * - Configurer un id auto généré
 * - Configurer les colonnes suivantes en not nullable : email, firstName, lastName
 *
 * - Mappé la relation unidirectionnelle entre {@link Employee} et {@link EmployeeProfile} du côté de l'enfant
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="firstName", nullable=false)
    private String fistName;
    @Column(name="lastName", nullable=false)
    private String lastName;
}

