package com.barbearia.agenda_barbeiro.service;

import com.barbearia.agenda_barbeiro.model.Agendamento;
import com.barbearia.agenda_barbeiro.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List; // <<< Adicione este import!

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    // Método para criar
    public Agendamento criar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    // === MUDANÇA AQUI: Altere Iterable para List ===
    public List<Agendamento> listarTodos() { // << Alterado o tipo de retorno para List
        return agendamentoRepository.findAll(); // findAll() já retorna List
    }

    // Método para atualizar
    public Agendamento atualizar(Long id, Agendamento agendamentoAtualizado) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado com o ID: " + id));

        agendamentoExistente.setNomeCliente(agendamentoAtualizado.getNomeCliente());
        agendamentoExistente.setTelefoneCliente(agendamentoAtualizado.getTelefoneCliente());
        agendamentoExistente.setServico(agendamentoAtualizado.getServico());
        agendamentoExistente.setDataAgendamento(agendamentoAtualizado.getDataAgendamento());
        agendamentoExistente.setHoraAgendamento(agendamentoAtualizado.getHoraAgendamento());
        agendamentoExistente.setConcluido(agendamentoAtualizado.getConcluido());

        return agendamentoRepository.save(agendamentoExistente);
    }

    // Método para deletar
    public void deletar(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado com o ID: " + id);
        }
        agendamentoRepository.deleteById(id);
    }
}