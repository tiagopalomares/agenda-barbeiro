package com.barbearia.agenda_barbeiro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity // Essa anotação diz ao Spring Data JPA que esta classe é uma entidade de banco de dados
@Data // Anotação do Lombok para gerar getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera o construtor padrão (sem argumentos)
@AllArgsConstructor // Gera um construtor com TODOS os campos como argumentos

public class Agendamento {
     @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o banco de dados para gerar automaticamente o ID
    private Long id; // Identificador único para cada agendamento

    private String nomeCliente;
    private String telefoneCliente;
    private LocalDate dataAgendamento; // Para armazenar a data (ex: 2025-07-09)
    private LocalTime horaAgendamento; // Para armazenar a hora (ex: 10:30)
    private String servico;     // "Cabelo", "Barba", etc.
    private Boolean concluido = false;   //Estado inicial: não concluído

    // Construtores, Getters e Setters serão gerados pelo Lombok (@Data)
    // Se não usar Lombok, teria que escrevê-los manualmente aqui.


}
