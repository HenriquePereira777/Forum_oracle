package com.DevPereira.ProjetoForum.domain.Topico;

import com.DevPereira.ProjetoForum.domain.Curso.CursoRepository;
import com.DevPereira.ProjetoForum.domain.Usuario.UsuarioRepository;
import com.DevPereira.ProjetoForum.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TopicoService{
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

public Topico cadastrar(DadosCadastroTopico dados) throws ValidacaoException {
    if(topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
        throw  new ValidacaoException("Titulo e Mensagem ja existem");
    }
    var autor = usuarioRepository.findById(dados.autorId())
            .orElseThrow(() -> new ValidacaoException("Autor não encontrado"));

    var curso = cursoRepository.findById(dados.cursoId())
            .orElseThrow(() -> new ValidacaoException("curso não encontrado"));

    var topico = new Topico(dados.titulo(), dados.mensagem(), autor,curso);
    return  topicoRepository.save(topico);

}
    public Page<Topico> listar(Pageable paginacao) {
        return topicoRepository.findAll(paginacao);
    }
    public  Topico detalhar(Long id) throws ValidacaoException {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

    }
    public  Topico atualizar(Long id,DadosAtualizacaoTopico dados){
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        var topicoExistente = topicoRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if(topicoExistente.isPresent() && !topicoExistente.get().getId().equals(id)){
            throw  new ValidacaoException("Titulo e mensagem ja existem");
        }
        topico.atualizarInformacoes(dados);
        return topico;
    }

    public void  excluir(Long id){
        topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        topicoRepository.deleteById(id);
    }



}
