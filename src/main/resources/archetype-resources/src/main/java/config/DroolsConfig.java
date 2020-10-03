#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DroolsConfig {
	
	 private static final String RULES_PATH = "${packageInPathFormat}/rules/";
	 
	private KieServices getKieServices() {
		return KieServices.Factory.get();
	}

	private List<String> getRuleFiles() {
		return Arrays.asList("SuggestApplicant.drl");
	}

	public KieFileSystem getFileSystem() throws IOException {
		KieFileSystem fs = this.getKieServices().newKieFileSystem();
		for (String file : getRuleFiles()) {
			fs.write(ResourceFactory.newClassPathResource(file));
		}
		return fs;
	}
	
	public KieContainer getContainer() {
		getKieRepository();
		return null;
	}

	private void getKieRepository() {
		KieRepository kr = getKieServices().getRepository();
		kr.addKieModule(new KieModule() {
			
			@Override
			public ReleaseId getReleaseId() {
				return kr.getDefaultReleaseId();
			}
		});
	}
	
	@Bean
	public KieSession getSession() {
		getKieRepository();
		KieServices kieServices = getKieServices();
		KieFileSystem fs = kieServices.newKieFileSystem();
		for (String file: this.getRuleFiles()) {
			fs.write(ResourceFactory.newClassPathResource(RULES_PATH + file));	
		}
		
		KieBuilder builder = kieServices.newKieBuilder(fs);
		builder.buildAll();
		
		KieModule km = builder.getKieModule();
		KieContainer c = kieServices.newKieContainer(km.getReleaseId());
		
		return c.newKieSession();
		
	}

}
