package fr.m2i.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
 * - Specifier le nom de la table: "employee_profile"
 * - Configurer un id auto généré
 * - Configurer les colonnes suivantes en not nullable : position, department
 *
 * - Mappé la relation entre {@link Employee} et {@link EmployeeProfile} en définissant la clé étrangère (foreign_key) avec le nom: "employee_id"
 * - Configurer une clé primaire partagée. E.g. La colonne mappé (par la relation) "employee_id" doit aussi être la clé primaire (id) pour cette entité (Voir: @MapsId)
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="employee_profile")
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "id_employee", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Employee employee;
    
    @Column(name="position", nullable=false)
    private String position;
    @Column(name="department", nullable=false)
    private String department;
}
