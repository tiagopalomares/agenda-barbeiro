package com.barbearia.agenda_barbeiro.repository; // Ajuste o pacote

import com.barbearia.agenda_barbeiro.model.Agendamento; // Ajuste o pacote
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional, mas boa prática
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    // Spring Data JPA vai gerar automaticamente os métodos CRUD básicos
}