package fr.m2i.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * todo:
 * - Implémenter un constructeur sans arguments
 * - Implémenter les getter et les setter
 *
 * - Configurer l'entité JPA
 * - Specifier le nom de la table: "photo_comment"
 * - Configurer un id auto généré
 * - Configurer la colonne text en tant que "not nullable"
 *
 * - Mapper la relation entre Photo et PhotoComment en définissant le nom de la colonne de la foreign_key en: "photo_id"
 * - Configurer la relation pour qu'elle soit obligatoire (not optional)
 */
@NoArgsConstructor
@EqualsAndHashCode(of = "id")  // necessaire pour les add and remove
@Getter
@Setter
@Entity
@Table(name="photo_comment")
public class PhotoComment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String text;
    private LocalDateTime createdOn;
    
    @JoinColumn(name="photo_id", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    private Photo photo;
    
    public void setPhoto(Photo photo){
        this.photo = photo;
        photo.addComment(this); //stack overflow si pas de control dans le addComment
    }
}