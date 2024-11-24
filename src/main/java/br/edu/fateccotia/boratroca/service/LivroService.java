package br.edu.fateccotia.boratroca.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.infra.DistanceCalculator;
import br.edu.fateccotia.boratroca.mapper.LivroMapper;
import br.edu.fateccotia.boratroca.exception.*;
import br.edu.fateccotia.boratroca.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private TokenService tokenService;
    @Lazy
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private CondicaoService condicaoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private LivroMapper livroMapper;
    @Autowired
    private DistanceCalculator distanceCalculator;

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro cadastrar(LivroDTO livroDTO, String authorization) {
        String tokenEmail = tokenService.getSubject(authorization);
        Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
        Optional<Autor> autor = autorService.findByNomeAutor(livroDTO.getAutor());
        Optional<Condicao> condicao = condicaoService.findByNomeCondicao(livroDTO.getCondicao());
        Optional<Categoria> categoria = categoriaService.findByNomeCategoria(livroDTO.getCategoria());

        if (usuario.isEmpty()) {
            throw new UsuarioNotFoundException();
        }

        try {
            Livro livro = livroMapper.toEntity(livroDTO);
            livro.setUsuario(usuario.get());
            livro.setCondicao(condicao.get());
            if (autor.isEmpty()) {
                Autor novoAutor = autorService.save(new Autor(livroDTO.getAutor()));
                livro.setAutor(novoAutor);
            } else {
                livro.setAutor(autor.get());
            }

            if (categoria.isEmpty()) {
                Categoria novaCategoria = categoriaService.save(new Categoria(livroDTO.getCategoria()));
                livro.setCategoria(novaCategoria);
            } else {
                livro.setCategoria(categoria.get());
            }
            return livroRepository.save(livro);

        } catch (IOException e) {
            throw new LivroMapperException("");
        }
    }

    public List<LivroDTO> findAll() {
        List<Livro> livros = livroRepository.findAll();
        List<LivroDTO> livroDTO = new ArrayList<LivroDTO>();

        for (Livro livro : livros) {
            livroDTO.add(livroMapper.toDTO(livro));
        }
        return livroDTO;
    }

    public LivroDTO findByIdLivro(int id) {
        Optional<Livro> livro = livroRepository.findByIdLivro(id);
        if (livro.isPresent()) {
            return livroMapper.toDTO(livro.get());
        } else {
            throw new LivroNotFoundException();
        }
    }

    public void delete(int id, String authorization) {
        String tokenEmail = tokenService.getSubject(authorization);
        Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
        Optional<Livro> livro = livroRepository.findByIdLivro(id);

        if (livro.isPresent()) {
            if (usuario.get().getIdUsuario() == livro.get().getUsuario().getIdUsuario()) {
                Livro livroDeletado = livroRepository.deleteById(id);
            } else {
                throw new UsuarioUnauthorizedExecption();
            }

        } else {
            throw new LivroNotFoundException();
        }
    }

    public List<LivroDTO> findAllByUsuario(Usuario usuario) {
        List<Livro> livros = livroRepository.findAllByUsuario(usuario);
        List<LivroDTO> livroDTO = new ArrayList<LivroDTO>();

        for (Livro livro : livros) {
            livroDTO.add(livroMapper.toDTO(livro));
        }
        return livroDTO;
    }

    public void alterarLivro(int id, Livro livro, String authorization) {
        Optional<Livro> livroFind = livroRepository.findByIdLivro(id);
        String tokenEmail = tokenService.getSubject(authorization);
        Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
        Optional<Autor> autorFind = autorService.findByNomeAutor(livro.getAutor().getNomeAutor());
        Optional<Condicao> condicaoFind = condicaoService.findByNomeCondicao(livro.getCondicao().getNomeCondicao());
        Optional<Categoria> categoriaFind = categoriaService.findByNomeCategoria(livro.getCategoria().getNomeCategoria());
        ;


        if (condicaoFind.isEmpty()) {
            throw new CondicaoNotFoundException();
        }

        livro.setCondicao(condicaoFind.get());

        if (autorFind.isEmpty()) {
            Autor autor = autorService.save(livro.getAutor());
            livro.setAutor(autor);
        } else {
            livro.setAutor(autorFind.get());
        }

        if (categoriaFind.isEmpty()) {
            Categoria categoria = categoriaService.save(livro.getCategoria());
            livro.setCategoria(categoria);
        } else {
            livro.setCategoria(categoriaFind.get());
        }


        if (livroFind.isPresent()) {
            if (usuario.get().getIdUsuario() == livroFind.get().getUsuario().getIdUsuario()) {
                if (livro.getNomeLivro() != null) {
                    livroFind.get().setNomeLivro(livro.getNomeLivro());
                }

                if (livro.getIsbn() != null) {
                    livroFind.get().setIsbn(livro.getIsbn());
                }

                if (livro.getDescricao() != null) {
                    livroFind.get().setDescricao(livro.getDescricao());
                }

                if (livro.getCondicao() != null) {
                    livroFind.get().setCondicao(livro.getCondicao());
                }

                if (livro.getCategoria() != null) {
                    livroFind.get().setCategoria(livro.getCategoria());
                }

                if (livro.getAutor() != null) {
                    livroFind.get().setAutor(livro.getAutor());
                }


                Livro livroAlterado = livroRepository.save(livroFind.get());

            } else {
                throw new UsuarioUnauthorizedExecption();
            }

        } else {
            throw new LivroNotFoundException();
        }
    }

    public List<LivroDTO> pesquisarLivro(String parametro) {
        Optional<List<Livro>> livros = (livroRepository.findAllByNomeLivroOrDescricaoOrAutorNomeOrCategoriaNomeLike(parametro));
        if (livros.isPresent()) {
            List<LivroDTO> livrosDTO = new ArrayList<LivroDTO>();
            for (Livro livro : livros.get()) {
                livrosDTO.add(livroMapper.toDTO(livro));
            }
            return livrosDTO;
        } else {
            throw new LivroNotFoundException();
        }
    }

    public Livro findLivroById(int idLivro) {
        Optional<Livro> livro = livroRepository.findByIdLivro(idLivro);
        if (livro.isPresent()) {
            return livro.get();
        } else {
            throw new LivroNotFoundException();
        }
    }

    public List<LivroDTO> findLivroByLocalizacao(String authorization, Double distancia) {
        String tokenEmail = tokenService.getSubject(authorization);
        Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
        List<Usuario> usuarios = usuarioService.findAll();
        List<Livro> livrosPorPerto = new ArrayList<>();

        List<Double> distancias = new ArrayList<>();

        if(usuario.get().getLatitude() != null && usuario.get().getLatitude() != null) {

            for (Usuario usuarioFind:usuarios) {
                if (usuarioFind.getIdUsuario() != usuario.get().getIdUsuario()) {
                    Double distanciaCalc = distanceCalculator.calculateDistance(usuario.get().getLatitude(), usuario.get().getLongitude(), usuarioFind.getLatitude(), usuarioFind.getLongitude());

                    distancias.add(distanciaCalc);

                    if (distanciaCalc <= distancia) {
                        livrosPorPerto.addAll(livroRepository.findAllByUsuario(usuarioFind));
                    }
                }
            }
            List<LivroDTO> livrosDTO = new ArrayList<LivroDTO>();
            for (Livro livro : livrosPorPerto) {
                livrosDTO.add(livroMapper.toDTO(livro));
            }
            return livrosDTO;
        } else {
            throw new LocalizacaoInvalidaException();
        }

    }
}
