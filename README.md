# projecto-eda-2026
Projecto semestral da cadeira de Estrutura de Dados e Algoritmo — simulação do atendimento hospitalar com Fila (FIFO) e Fila de Prioridade.

## Compilar e testar

```bash
javac -encoding UTF-8 -d out $(find src testes -name '*.java')
java -cp out TesteFila
```

Os restantes testes (`TesteFilaPrioridade`, `TesteSistema`) serão feitos pelo Membro 6.

## API para a GUI

A GUI usa **apenas** `servicos.GestorAtendimento`. Nunca mexe directamente nas filas.

| Acção na GUI | Método |
|---|---|
| Registar paciente | `Paciente registarPaciente(String nome, int idade)` — o id é gerado automaticamente |
| Escolher paciente para triar | `List<Paciente> listarNaoTriados()` |
| Fazer triagem | `Triagem realizarTriagem(Paciente p, String sintomas, NivelTriagem nivel)` — encaminha para a fila certa |
| Ver filas | `List<Paciente> listarFilaPrioridade()`, `List<Paciente> listarFilaNormal()` |
| Mostrar próximo (sem remover) | `Paciente proximoPaciente()` |
| Chamar próximo | `Atendimento chamarProximo(String medico)` |
| Atendimento em curso | `Atendimento getAtendimentoAtual()` (`null` se não houver) |
| Finalizar atendimento | `Atendimento finalizarAtendimento(String observacoes)` |
| Estatísticas | `List<Atendimento> getHistorico()`, `List<Paciente> listarPacientes()`, `Paciente buscarPaciente(int id)` |

Os níveis para a triagem estão em `NivelTriagem.values()` (o `toString()` já mostra `1 - Emergência (Vermelho)`).

Erros que a GUI deve apanhar e mostrar ao utilizador (`e.getMessage()`):

- `PacienteInvalidoException` — dados inválidos, paciente já triado, médico em falta;
- `FilaVaziaException` — não há pacientes à espera;
- `TriagemPendenteException` — paciente sem triagem;
- `IllegalStateException` — já existe um atendimento em curso / não existe atendimento para finalizar.

## Decisões de desenho

- **FIFO em empates**: a ordem de chegada é a ordem de inserção na fila; `horaChegada` serve apenas para consulta/estatística.
- **Estado do paciente**: o `Atendimento` é a fonte de verdade; `Paciente.getEstado()` é um espelho para consulta.
- **Número de utente**: fora do âmbito, por decisão do grupo.
- **Complexidade**: `Fila` — `enqueue`/`dequeue`/`peek` O(1); `FilaPrioridade` — `enqueue` O(n), `dequeue`/`peek` O(1); `toList` O(n) em ambas.
