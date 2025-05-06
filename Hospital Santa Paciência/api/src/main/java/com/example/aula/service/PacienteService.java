package com.example.aula.service;

import com.example.aula.exception.EmailJaCadastradoException;
import com.example.aula.model.Paciente;
import com.example.aula.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente salvar(@Valid Paciente paciente) {
        if (pacienteRepository.findByEmail(paciente.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException("Usuário já cadastrado.");
        }

        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(@Valid Paciente paciente) {
        Paciente pacienteAtualizar = pacienteRepository.findById(paciente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));

        pacienteAtualizar.setNome(paciente.getNome());
        pacienteAtualizar.setEmail(paciente.getEmail());
        pacienteAtualizar.setSenha(paciente.getSenha());

        return pacienteRepository.save(pacienteAtualizar);
    }

    public void excluir(Long id) {
        Paciente pacienteExcluir = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        pacienteRepository.deleteById(pacienteExcluir.getId());
    }

}
