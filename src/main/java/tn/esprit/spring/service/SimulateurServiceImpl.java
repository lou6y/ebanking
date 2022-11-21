package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Simulateur;
import tn.esprit.spring.repository.SimulateurRepository;

@Service
public class SimulateurServiceImpl implements  ISimulateurService{

	@Autowired
	SimulateurRepository simRep;
	
	@Override
	public Float Simulater1(float MontantEmprunte, int Duree) {
		
		Simulateur s = new Simulateur();
		double Taux;
		
		
		if (Duree<10) {
			 Taux= (float) 1.56;
		}
			else if ((Duree>10) && (Duree<=15)) {
				 Taux=(float) 1.78;
			}
						else if ((Duree>15) && ( Duree<=20 )) {
           Taux=(float) 1.92;
						}
          else Taux=(float) 2.05;
		
		float Taux2= (float) (Taux/100);
		
	
		Float montantDeLaMensualitecalcule=
				(float)	 (MontantEmprunte*((Taux2)/12)/ 
				(1-(Math.pow(1+(Taux2)/12,Duree*(-12))))  );  
		
		 Float montanttotaldesintérêts = 
				 (montantDeLaMensualitecalcule*Duree*12)
			     -MontantEmprunte; 
		 
		System.out.println("Montant De La Mensualité : " +montantDeLaMensualitecalcule+ " dt "+
			    "Montant Total Des Intérêts : " +montanttotaldesintérêts+ " dt ");
		
		s.setDurée(Duree);
		s.setMontant(montanttotaldesintérêts);
		s.setTaux(Taux2);
		
		simRep.save(s);
		
		return montantDeLaMensualitecalcule;

	}
	}

	

		
	

	
	