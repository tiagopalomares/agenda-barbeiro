package com.barbearia.agenda_barbeiro.controller;

import com.barbearia.agenda_barbeiro.model.Agendamento;
import com.barbearia.agenda_barbeiro.service.AgendamentoService; // Importa seu serviço
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos") // Caminho base para todos os endpoints deste controller
public class AgendamentoController {

    // 1. Injete AgendamentoService via construtor (preferencial ao @Autowired em campo)
    private final AgendamentoService agendamentoService;

    // Construtor para injeção de dependência do serviço
    // O Spring Boot vai injetar uma instância de AgendamentoService aqui
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    // Endpoint para LISTAR TODOS OS AGENDAMENTOS
    // Método: GET
    // URL: /agendamentos
    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        // 2. Chame o método de serviço para listar
        return agendamentoService.listarTodos();
    }

    // Endpoint para CRIAR UM NOVO AGENDAMENTO
    // Método: POST
    // URL: /agendamentos
    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
        // 2. Chame o método de serviço para criar
        // A lógica de setar ID null e concluido false pode ir para o serviço,
        // mas por enquanto, manteremos aqui para clareza
        agendamento.setId(null);
        if (agendamento.getConcluido() == null) {
            agendamento.setConcluido(false);
        }
        Agendamento savedAgendamento = agendamentoService.criar(agendamento); // Chama o serviço
        return new ResponseEntity<>(savedAgendamento, HttpStatus.CREATED);
    }

    // Endpoint para ATUALIZAR UM AGENDAMENTO
    // Método: PUT
    // URL: /agendamentos/{id}
    @PutMapping("/{id}") // {id} como variável de caminho
    public ResponseEntity<Agendamento> atualizarAgendamento(
        @PathVariable Long id,
        @RequestBody Agendamento agendamentoAtualizado) {

        // Chame o método de serviço para atualizar
        Agendamento agendamentoSalvo = agendamentoService.atualizar(id, agendamentoAtualizado);
        return ResponseEntity.ok(agendamentoSalvo);
    }

    // Endpoint para DELETAR UM AGENDAMENTO
    // Método: DELETE
    // URL: /agendamentos/{id}
    @DeleteMapping("/{id}") // 3. Corrigido para apenas /{id}
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        // Chame o método de serviço para deletar
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}