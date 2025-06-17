package br.edu.fateccotia.boratroca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Teste de inicialização do contexto da aplicação Bora Trocar")
class BoratrocaApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	@DisplayName("Deve carregar o contexto Spring Boot com sucesso")
	void deveCarregarContextoSpring() {
		assertThat(context).isNotNull();
	}

	@Test
	@DisplayName("Deve inicializar bean do UsuarioService")
	void deveTerBeanUsuarioService() {
		assertThat(context.containsBean("usuarioService")).isTrue();
	}
}
