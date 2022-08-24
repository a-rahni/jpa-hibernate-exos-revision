package fr.m2i.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * todo:
 * - Implémenter un constructeur sans arguments
 * - Implémenter les getter et les setter
 * - Implémenter equals() et hashCode() en se basant sur le champs {@link Photo#id} (@EqualsAndHashCode)
 *
 * - Configurer l'entité JPA
 * - Specifier le nom de la table: "photo"
 * - Configurer un id auto généré
 * - Configurer la colonne url pour qu'elle soit "not nullable" et "unique"
 * 
 *
 * - Initialiser le champs comments
 * - Mapper la relation entre Photo et PhotoComment coté enfant {@link PhotoComment}
 * - Implémenter les méthodes utilitaires {@link Photo#addComment(PhotoComment)} and {@link Photo#removeComment(PhotoComment)}
 * - Mettre place le type de cascade {@link javax.persistence.CascadeType#ALL} pour le champs {@link Photo#comments}
 * - Mettre en place la suppresion des orphelin (orphanRemoval = true)
 */

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="photo")
public class Photo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="url", nullable=false, unique=true)
    private String url;
    private String description;
    
    @OneToMany(mappedBy="photo",
               cascade={CascadeType.ALL},
               orphanRemoval=true)
    private List<PhotoComment> comments = new ArrayList<>();

    public void addComment(PhotoComment comment) {
        if(comment==null){
            return;
        }
        comments.add(comment);
        if(comment.getPhoto() != this){ // pour eviter de reboucler ((stack overflow))
            comment.setPhoto(this);
        }
        //throw new UnsupportedOperationException("Make me work!");
    }

    public void removeComment(PhotoComment comment) {
        //comment.setPhoto(null);
        comments.remove(comment);
        //comment.setPhoto(null);  // pose probleme nullpoint lors des remove
        //throw new UnsupportedOperationException("Make me work!");
    }

}