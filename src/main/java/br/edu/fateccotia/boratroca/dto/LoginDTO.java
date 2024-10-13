package br.edu.fateccotia.boratroca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class LoginDTO {
	@NotBlank
	@Email(message = "Email invalido")
	private String email;
	@Min(value = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String senha;
}
