package dev.cgs.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

// L'annotation @Entity permet de dire a spring qu'il faut mappe cette classe a
// une table de la base de donnes
@Entity
public class Etudiant implements Serializable {

	/**
	 * @author <b>Bamba CISSE</b>
	 *         <p>
	 *         Entite Etudiant qui va correspondre a une table au niveau de la base
	 *         de donnees. Ce type de classe est generalement appele <b>binaire
	 *         persistant</b> on les appelle aussi <b>java bean</b>
	 *         </p>
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long code;
	@NotEmpty
	@Size(min=1,max=20)
	private String nom;
	@NotEmpty
	private String prenom;
	@Email
	private String email;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String photo;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Etudiant() {
		super();
	}

	public Etudiant(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Etudiant(String nom, String prenom, String email, Date date) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date = date;
	}

	public Etudiant(String nom, String prenom, String email, Date date, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date = date;
		this.photo = photo;
	}

}
