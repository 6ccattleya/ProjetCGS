package dev.cgs;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetCgsApplication {

	
	public static void main(String[] args) throws ParseException {
		
		
		SpringApplication.run(ProjetCgsApplication.class, args);
		
//		EtudiantRepository repository=context.getBean(EtudiantRepository.class);
//		
//		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		/**@author Bamba CISSE
		*enregistrement au demarrage de kelk etudiants
		*/
		/*
		repository.save(new Etudiant("Cisse", "Mara", "cisse@CGS.com",df.parse("1993-01-06"),"pp1"));
		repository.save(new Etudiant("Gaye", "Mamadou", "gaye@CGS.com",df.parse("1994-02-07"),"pp2"));
		repository.save(new Etudiant("Sy", "Mamadou", "sy@CGS.com",df.parse("1995-03-08"),"pp3"));
		*/
	}
}
