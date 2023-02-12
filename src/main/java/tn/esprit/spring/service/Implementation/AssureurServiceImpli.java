package tn.esprit.spring.service.Implementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Assureur;
import tn.esprit.spring.repository.AssureurRepo;
import tn.esprit.spring.service.Interface.AssureurService;
@Service

public class AssureurServiceImpli implements AssureurService{
	@Autowired
	AssureurRepo assureurRep;

	@Override
	public List<Assureur> getAssureurs() {
		return (List<Assureur>) (assureurRep.findAll());
	}

	@Override
	public Assureur addAssureur(Assureur assureur) {
		return assureurRep.save(assureur);		
	}

	@Override
	public void deleteAssureur(long id) {
		assureurRep.deleteById(id);		
	}

	@Override
	public Assureur updateAssureur(long id, Assureur newAssureur) {
		System.out.println(newAssureur);
		Assureur AssureurModified = assureurRep.findById(id).get();
		AssureurModified.setNomAssurance(newAssureur.getNomAssurance());
		AssureurModified.setRue(newAssureur.getRue());
		AssureurModified.setVille(newAssureur.getVille());
		AssureurModified.setCodePostal(newAssureur.getCodePostal());
		

		assureurRep.save(AssureurModified); 
		return AssureurModified;
	}

	@Override
	public Assureur getAssureur(long id) {
		return assureurRep.findById(id).get();
	}

}
