package co.simplon.crud.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run(String... args) throws Exception {
		for (String arg : args) {
			if (arg.equals("demo")) {
				demoData();
			}
			if (arg.equals("reset")) {
				resetAdminPassword();
			}
		}
	}

	private void demoData() {
		logger.info("Inserting demo data");
	}

	private void resetAdminPassword() {
		logger.info("Reseting admin password");
		String newPass = UUID.randomUUID().toString();
		logger.info("New admin password " + newPass);
	}

}
