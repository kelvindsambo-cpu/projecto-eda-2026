package util;

import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import modelo.NivelTriagem;
import modelo.Paciente;

public class Validacao {

    public static void validarPaciente(String nome, int idade)
            throws PacienteInvalidoException {

        if (nome == null || nome.trim().isEmpty()) {
            throw new PacienteInvalidoException(
                    "O nome do paciente é obrigatório e não pode estar vazio.");
        }

        if (nome.trim().length() < 2) {
            throw new PacienteInvalidoException(
                    "O nome do paciente deve conter pelo menos 2 caracteres.");
        }

        if (idade < 0 || idade > 130) {
            throw new PacienteInvalidoException(
                    "A idade fornecida (" + idade + ") é inválida.");
        }
    }

    public static void validarPacienteNaoNulo(Paciente paciente)
            throws PacienteInvalidoException {

        if (paciente == null) {
            throw new PacienteInvalidoException(
                    "O paciente fornecido não pode ser nulo.");
        }
    }

    public static void validarTriagem(
            Paciente paciente,
            String sintomas,
            NivelTriagem nivel)
            throws PacienteInvalidoException {

        validarPacienteNaoNulo(paciente);

        if (nivel == null) {
            throw new PacienteInvalidoException(
                    "É obrigatório seleccionar um Nível de Triagem válido.");
        }

        if (sintomas == null || sintomas.trim().isEmpty()) {
            throw new PacienteInvalidoException(
                    "A descrição dos sintomas é obrigatória para a triagem.");
        }
    }

    public static void validarAtendimento(
            Paciente paciente,
            String medico)
            throws PacienteInvalidoException, TriagemPendenteException {

        validarPacienteNaoNulo(paciente);

        if (!paciente.isTriado()) {
            throw new TriagemPendenteException(
                    "O paciente " + paciente.getNome()
                            + " ainda não passou pela triagem.");
        }

        if (medico == null || medico.trim().isEmpty()) {
            throw new PacienteInvalidoException(
                    "O nome do médico responsável é obrigatório.");
        }
    }
}