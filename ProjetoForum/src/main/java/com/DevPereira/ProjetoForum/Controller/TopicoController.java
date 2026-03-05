package com.DevPereira.ProjetoForum.Controller;

import com.DevPereira.ProjetoForum.domain.Topico.*;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;


    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<DadosCadastroTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {

        var topico = topicoService.cadastrar(dados);

        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new DadosCadastroTopico(topico));


    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = topicoService.listar(paginacao)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id){
       var detalhamento = topicoService.detalhar(id);
       return ResponseEntity.ok(new DadosDetalhamentoTopico(detalhamento));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(@PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados){

        var topicoAtualizado = topicoService.atualizar(id,dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoAtualizado));

    }

   @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
       topicoService.excluir(id);
       return ResponseEntity.noContent().build();
   }
}
