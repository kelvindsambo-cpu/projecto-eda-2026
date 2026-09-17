import excecoes.FilaVaziaException;
import excecoes.PacienteInvalidoException;
import excecoes.TriagemPendenteException;
import estruturas.FilaPrioridade;
import modelo.Atendimento;
import modelo.NivelTriagem;
import modelo.Paciente;
import servicos.GestorAtendimento;

public class TesteSistema {
    public static void main(String[] args)
            throws PacienteInvalidoException, FilaVaziaException, TriagemPendenteException {
        GestorAtendimento gestor = new GestorAtendimento();
        Paciente normal = gestor.registarPaciente(1, "Ana", 30);
        Paciente urgente = gestor.registarPaciente(2, "Bruno", 40);

        gestor.realizarTriagem(normal, "Dor ligeira", NivelTriagem.NORMAL);
        gestor.realizarTriagem(urgente, "Dor intensa", NivelTriagem.URGENTE);

        assert gestor.getFilaPrioridade().size() == 1;
        assert gestor.getFilaNormal().size() == 1;
        assert gestor.chamarProximo() == urgente;

        Atendimento atendimento = gestor.iniciarAtendimento(urgente, "Dr. Silva");
        gestor.finalizar(atendimento, "Atendido");
        assert atendimento.getEstado().name().equals("ATENDIDO");
        assert gestor.chamarProximo() == normal;

        FilaPrioridade fila = new FilaPrioridade();
        Paciente semTriagem = new Paciente(3, "Carla", 25);
        boolean rejeitado = false;
        try {
            fila.enqueue(semTriagem);
        } catch (IllegalArgumentException e) {
            rejeitado = true;
        }
        assert rejeitado;
    }
}
