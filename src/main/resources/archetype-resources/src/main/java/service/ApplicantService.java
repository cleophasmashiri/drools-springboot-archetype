#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.model.Applicant;
import ${package}.model.SuggestedRole;

@Service
public class ApplicantService {

	@Autowired
	KieSession kieSession;

	public SuggestedRole suggestRole(Applicant a, SuggestedRole r) {
		
		kieSession.insert(a);
		kieSession.setGlobal("suggestedRole", r);
		kieSession.fireAllRules();
		return r;
		
	}

}
