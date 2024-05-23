package br.edu.fateccotia.boratroca.controller;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.dto.LivroMapper;
import br.edu.fateccotia.boratroca.model.*;
import br.edu.fateccotia.boratroca.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroControllerTest {

    @InjectMocks
    private LivroController livroController;

    @Mock
    private LivroService livroService;

    @Mock
    private TokenService tokenService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AutorService autorService;

    @Mock
    private CondicaoService condicaoService;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private LivroMapper livroMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() throws IOException {
        String token = "token";
        String email = "user@example.com";
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setAutor("Author");
        livroDTO.setCondicao("New");
        livroDTO.setCategoria("Category");

        Usuario usuario = new Usuario();
        Autor autor = new Autor("Author");
        Condicao condicao = new Condicao("New");
        Categoria categoria = new Categoria("Category");
        Livro livro = new Livro();
        livro.setUsuario(usuario);
        livro.setAutor(autor);
        livro.setCondicao(condicao);
        livro.setCategoria(categoria);

        when(tokenService.getSubject(token)).thenReturn(email);
        when(usuarioService.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(autorService.findByNomeAutor("Author")).thenReturn(Optional.of(autor));
        when(condicaoService.findByNomeCondicao("New")).thenReturn(Optional.of(condicao));
        when(categoriaService.findByNomeCategoria("Category")).thenReturn(Optional.of(categoria));
        when(livroMapper.toEntity(livroDTO)).thenReturn(livro);
        when(livroService.save(livro)).thenReturn(livro);

        ResponseEntity<Livro> response = livroController.cadastrar(livroDTO, token);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    void testFindAll() {
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        LivroDTO livroDTO1 = new LivroDTO();
        LivroDTO livroDTO2 = new LivroDTO();

        when(livroService.findAll()).thenReturn(Arrays.asList(livro1, livro2));
        when(livroMapper.toDTO(livro1)).thenReturn(livroDTO1);
        when(livroMapper.toDTO(livro2)).thenReturn(livroDTO2);

        ResponseEntity<List<LivroDTO>> response = livroController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(livroDTO1, response.getBody().get(0));
        assertEquals(livroDTO2, response.getBody().get(1));
    }

    @Test
    void testFindByIdLivro() {
        int id = 1;
        Livro livro = new Livro();
        LivroDTO livroDTO = new LivroDTO();

        when(livroService.findByIdLivro(id)).thenReturn(Optional.of(livro));
        when(livroMapper.toDTO(livro)).thenReturn(livroDTO);

        ResponseEntity<LivroDTO> response = livroController.findByIdLivro(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livroDTO, response.getBody());
    }

    @Test
    void testDeletarLivro() {
        int id = 1;
        String token = "token";
        String email = "user@example.com";
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        Livro livro = new Livro();
        livro.setUsuario(usuario);

        when(tokenService.getSubject(token)).thenReturn(email);
        when(usuarioService.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(livroService.findByIdLivro(id)).thenReturn(Optional.of(livro));
        when(livroService.delete(id)).thenReturn(livro);

        ResponseEntity<Livro> response = livroController.deletarLivro(id, token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    // @Test
    // void testAlterarLivro() {
    //     int id = 1;
    //     String token = "token";
    //     String email = "user@example.com";
    //     Livro livro = new Livro();
    //     Usuario usuario = new Usuario();
    //     usuario.setIdUsuario(1);
    //     Autor autor = new Autor("Author");
    //     Condicao condicao = new Condicao("New");
    //     Categoria categoria = new Categoria("Category");

    //     Livro livroFromDb = new Livro();
    //     livroFromDb.setUsuario(usuario);
    //     livroFromDb.setAutor(autor); // Certifique-se de definir o autor do livro recuperado do banco de dados

    //     when(tokenService.getSubject(token)).thenReturn(email);
    //     when(usuarioService.findByEmail(email)).thenReturn(Optional.of(usuario));
    //     when(livroService.findByIdLivro(id)).thenReturn(Optional.of(livroFromDb));
    //     when(autorService.findByNomeAutor("Author")).thenReturn(Optional.of(autor));
    //     when(condicaoService.findByNomeCondicao("New")).thenReturn(Optional.of(condicao));
    //     when(categoriaService.findByNomeCategoria("Category")).thenReturn(Optional.of(categoria));
    //     when(livroService.save(any(Livro.class))).thenReturn(livro);

    //     ResponseEntity<Livro> response = livroController.alterarLivro(id, livro, token);

    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertNotNull(response.getBody());
    // }


    @Test
    void testPesquisarLivro() {
        String parametro = "search";
        Livro livro = new Livro();
        LivroDTO livroDTO = new LivroDTO();
        List<Livro> livros = Arrays.asList(livro);

        when(livroService.findAllByNomeLivroOrDescricaoOrAutorNomeOrCategoriaNomeLike(parametro)).thenReturn(Optional.of(livros));
        when(livroMapper.toDTO(livro)).thenReturn(livroDTO);

        ResponseEntity<List<LivroDTO>> response = livroController.alterarLivro(parametro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(livroDTO, response.getBody().get(0));
    }
}
