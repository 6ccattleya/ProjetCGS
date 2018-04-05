package dev.cgs.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.cgs.entites.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

	/**
	 * @author <a href="www.github.com/6ccattleya">Bamba</a> <br>
	 * @Doc Interface qui sert de pont entre nos donnees et l'application : On
	 *      l'appelle un <b>Repository</b> <br>
	 *      Cette interface herite de la classe JpaRepository qui possede a peu pres
	 *      toutes les methodes pour gerer notre base de donnes <br>
	 * 		notamment la fonction <b>save()</b> qui permet d'enregistrer un objet
	 *      dans la source de donnees
	 */

	
	
	@Query("select e from Etudiant e where e.nom like :critere")
	public Page<Etudiant> chercherEtudiants(@Param("critere") String critere, Pageable pageable);

}
