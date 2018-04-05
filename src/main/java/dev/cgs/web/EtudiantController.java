package dev.cgs.web;

import java.io.File;
import java.io.FileInputStream;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.cgs.dao.EtudiantRepository;
import dev.cgs.entites.Etudiant;

@Controller
@RequestMapping(value = "/Etudiant")
public class EtudiantController {
	/**
	 *@author <a href="https://www.github.com/6ccattleya">bamba cisse</a> 
	 *Controller Etudiant <br>
	 *
	 **/
	@Autowired
	private EtudiantRepository repository;
	@Value("${bd.images}")
	private String bd_img;
	@RequestMapping(value = "")
	public String accueil() {

		return "index.html";
	}
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String ajouter(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "add";
	}
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @Valid Etudiant e, BindingResult result,
			@RequestParam(value = "image") MultipartFile image) throws Exception {
		if (result.hasErrors()) {
			return "add";
		}
			//repository.save(e);
			
		if(!(image.isEmpty())) {
			Long code=1L;
		
			if(repository.findAll().size()!=0) 
				 code=(repository.findAll().get(repository.findAll().size()-1).getCode()+1);
			
			String nom_photo="pp"+code;
			System.out.println(nom_photo);
			e.setPhoto(nom_photo);
			image.transferTo(new File(bd_img+nom_photo));
			repository.save(e);
		}
		
		return "redirect:list";
	}
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value="/edit")
	public String edit(Model model,@RequestParam Long code) {
		Etudiant etd=repository.getOne(code);
		model.addAttribute("etudiant",etd);
		return "update";
	}
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @Valid Etudiant e, BindingResult result,
			@RequestParam(value = "image") MultipartFile image) throws Exception {
		
		if (result.hasErrors()) {
			return "redirect:edit";
		}
			//repository.save(e);
			
		if(!(image.isEmpty())) {
			Long code=e.getCode();
			String nom_photo="pp"+code;
			//System.out.println(nom_photo);
			e.setPhoto(nom_photo);
			image.transferTo(new File(bd_img+nom_photo));
		}
		else {
			String nom_photo=e.getPhoto();
			e.setPhoto(nom_photo);
		}
		repository.save(e);
		return "redirect:list";
	}
	@RequestMapping(value="/del")
	public String del(@RequestParam Long code) {
		repository.deleteById(code);
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	/**
	 * @author bamba cisse
	 * @Method -> Cette methode retourne la liste des etudiants par page de @param
	 *         -> size [default=10] <b>Etudiant</b>
	 */
	public String etudiantByName(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(value = "nom", defaultValue = "") String critere) {
		Page<Etudiant> etds = repository.chercherEtudiants("%" + critere + "%", PageRequest.of(page, 10));

		int[] pages = new int[etds.getTotalPages()];
		for (int i = 0; i < pages.length; i++) {
			pages[i] = i;
		}
		model.addAttribute("pages", pages);
		model.addAttribute("AllEtudiants", etds);
		model.addAttribute("page", page);
		model.addAttribute("nom", critere);

		return "list";
	}
	@RequestMapping(value="/showPP",produces=MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] showPP(@RequestParam Long code) throws Exception{
		File fic=new File(bd_img+"pp"+code);
		return IOUtils.toByteArray(new FileInputStream(fic));
	}

}
