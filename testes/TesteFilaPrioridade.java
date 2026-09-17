import estruturas.FilaPrioridade;
import modelo.NivelTriagem;
import modelo.Paciente;

public class TesteFilaPrioridade {
    public static void main(String[] args) throws Exception {
        FilaPrioridade fila = new FilaPrioridade();

        Paciente p1 = new Paciente(1, "Ana Silva", 30);
        Paciente p2 = new Paciente(2, "Bia Costa", 30);
        Paciente p3 = new Paciente(3, "Cia Sousa", 30);
        Paciente p4 = new Paciente(4, "Dio Lima", 30);
        p1.setNivel(NivelTriagem.URGENTE);
        p2.setNivel(NivelTriagem.EMERGENCIA);
        p3.setNivel(NivelTriagem.EMERGENCIA);
        p4.setNivel(NivelTriagem.MUITO_URGENTE);

        fila.enqueue(p1);
        fila.enqueue(p2);
        fila.enqueue(p3);
        fila.enqueue(p4);

        assert fila.dequeue() == p2;
        assert fila.dequeue() == p3;
        assert fila.dequeue() == p4;
        assert fila.dequeue() == p1;
        assert fila.isEmpty();
    }
}
