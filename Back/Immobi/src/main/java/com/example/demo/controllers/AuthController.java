package com.example.demo.controllers;

import java.util.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
	/*Cette url est ignorer par la securité
	il sera utile prochainement pour la validation des comptes qui seront crée.
	Chaque compte doit etre valider par son email
	pour cela ce mapping est accesible partout avec une signature qui sera identifié prochainement.*/
	@RequestMapping("/validation")
	public String validation(@RequestParam(value = "signature", required = false)String base64) {
		/*test*/
		byte[] decodedBytes = Base64.getDecoder().decode("eyJlbWFpbCI6ImJvdWplbWFhLndhaGlkQGdtYWlsLmNvbSJ9");
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
}
