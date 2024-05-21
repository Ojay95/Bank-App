package com.BankApplication;

import com.BankApplication.model.AccountUser;
import com.BankApplication.model.Role;
import com.BankApplication.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAsync
@SpringBootApplication
public class BankApplication implements CommandLineRunner {

	@Autowired
	private AccountUserService accountUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		AccountUser adminUser = new AccountUser();
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Admin");
		adminUser.setRole(Role.ADMIN);
		adminUser.setUsername("admin@gmail.com");
		adminUser.setPhoneNumber("09011223344");
		adminUser.setPassword(passwordEncoder.encode("password"));

		AccountUser exist = accountUserService.getAccountUserByUsername("admin@gmail.com").getBody();

		if (exist == null) {
			accountUserService.postAccountUser(adminUser);
		}

	}
}