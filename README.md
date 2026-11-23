# Sistema de Escalonamento de Atendimento de um Hospital

**Universidade Eduardo Mondlane** — Departamento de Electrotecnia
Licenciatura em Engenharia Informática — Estrutura de Dados e Algoritmos
Projecto semestral — Maputo, Novembro de 2026

**Discentes:** Jéssica Jorge Chindela, Edson Nicolau Condjo, Wendy Carla Lissenga, Nuno João Nhantumbo, Kelvin Damião Sambo, Cíntia de Carmen Sílvio Saite

**Docentes:** PhD Hélio Pesanhane, Engº Cristiliano Maculuve

---

## Descrição

Aplicação em Java (Swing) que simula o atendimento de pacientes num hospital com dois Tipos Abstractos de Dados implementados pelo grupo:

- **Fila (FIFO)** para pacientes de atendimento normal;
- **Fila de Prioridade** para pacientes prioritários, com FIFO em caso de empate.

Nenhuma das estruturas usa `Queue`, `LinkedList` ou `PriorityQueue` do Java: ambas são listas simplesmente ligadas construídas sobre a classe `No<T>`.

O projecto é uma simulação académica. A classificação por níveis é inspirada no Sistema de Triagem de Manchester, mas o sistema não faz diagnóstico nem calcula a prioridade clínica: o nível é escolhido pelo utilizador durante a triagem.

## Fluxo de atendimento

1. **Registar** o paciente (nome e idade; o id é gerado automaticamente).
2. **Triar**: registar os sintomas e escolher o nível de triagem.
3. **Encaminhar** automaticamente: níveis 1–3 → Fila de Prioridade; níveis 4–5 → Fila normal.
4. **Chamar o próximo**: se a Fila de Prioridade não estiver vazia, atende-se o seu primeiro paciente; caso contrário, o primeiro da Fila normal.
5. **Finalizar** o atendimento com observações. O atendimento fica registado no histórico.

| Nível | Categoria     | Cor      | Fila               |
|:-----:|---------------|----------|--------------------|
| 1     | Emergência    | Vermelho | Fila de Prioridade |
| 2     | Muito Urgente | Laranja  | Fila de Prioridade |
| 3     | Urgente       | Amarelo  | Fila de Prioridade |
| 4     | Normal        | Verde    | Fila normal        |
| 5     | Não Urgente   | Azul     | Fila normal        |

O fluxograma completo está em [docs/fluxograma.png](docs/fluxograma.png).

## Requisitos

- JDK 25 ou superior.
- [FlatLaf 3.7.2](https://www.formdev.com/flatlaf/) (aspecto visual da GUI), já incluído em [lib/](lib/).

## Como executar

### NetBeans

Abrir a pasta do projecto em *File → Open Project* e executar (*Run Project*, F6). A classe principal é `aplicacao.Aplicacao`.

### Linha de comandos

A partir da raiz do projecto. A pasta `src` entra no classpath de execução porque contém as imagens da interface (`src/gui/images`).

**Windows (PowerShell):**

```powershell
javac -encoding UTF-8 -cp lib\flatlaf-3.7.2.jar -d out (Get-ChildItem -Recurse src,testes -Filter *.java).FullName
java -cp "out;src;lib\flatlaf-3.7.2.jar" aplicacao.Aplicacao
```

**Linux / macOS / Git Bash:**

```bash
javac -encoding UTF-8 -cp lib/flatlaf-3.7.2.jar -d out $(find src testes -name '*.java')
java -cp "out:src:lib/flatlaf-3.7.2.jar" aplicacao.Aplicacao   # no Git Bash usar ";" em vez de ":"
```

## Testes

Os testes estão em [testes/](testes/) e não dependem de bibliotecas externas: cada um é um programa com `main` que verifica os casos e termina com erro se algum falhar. Depois de compilar como acima:

```bash
java -Dstdout.encoding=UTF-8 -cp out TesteFila
java -Dstdout.encoding=UTF-8 -cp out TesteFilaPrioridade
```

| Teste | Casos verificados |
|---|---|
| `TesteFila` | fila inicialmente vazia; inserir um e vários pacientes; `size()`; `peek()` não remove; `toList()` na ordem FIFO; remoção FIFO; remover de fila vazia lança `FilaVaziaException` |
| `TesteFilaPrioridade` | fila inicialmente vazia; `peek()` devolve o mais prioritário sem remover; ordenação por nível com FIFO nos empates; remoção por prioridade; remover de fila vazia lança `FilaVaziaException`; paciente sem triagem é recusado |

## Estrutura do projecto

```text
├── src/
│   ├── aplicacao/   Aplicacao — ponto de entrada (main)
│   ├── estruturas/  IFila, No, Fila, FilaPrioridade
│   ├── modelo/      Paciente, Triagem, Atendimento, NivelTriagem, EstadoAtendimento
│   ├── servicos/    GestorAtendimento — lógica do atendimento
│   ├── excecoes/    FilaVaziaException, PacienteInvalidoException, TriagemPendenteException
│   ├── util/        Validacao — validação dos dados de entrada
│   └── gui/         JanelaPrincipal, DlgNovoPaciente e imagens
├── testes/          TesteFila, TesteFilaPrioridade
├── docs/            relatório, diagrama de classes e fluxograma
├── lib/             flatlaf-3.7.2.jar
└── nbproject/, build.xml   configuração do projecto NetBeans
```

### Arquitectura

- **`estruturas`**: `IFila<T>` define o contrato comum (`enqueue`, `dequeue`, `peek`, `isEmpty`, `size`, `toList`). `Fila<T>` e `FilaPrioridade` implementam-no sobre nós ligados (`No<T>`).
- **`servicos.GestorAtendimento`**: liga as duas filas ao domínio. Regista e tria pacientes, encaminha-os para a fila certa, chama o próximo, finaliza atendimentos e guarda o histórico. Não contém código Swing e funciona sem a GUI.
- **`gui`**: a interface comunica apenas com o `GestorAtendimento` e nunca manipula as filas directamente. Os erros de validação chegam à GUI como excepções e são mostrados ao utilizador.

O diagrama de classes está em [docs/diagrama-classes.png](docs/diagrama-classes.png).

## Complexidade das operações

*n* = número de pacientes na fila.

| Operação  | Fila (FIFO) | Fila de Prioridade                 |
|-----------|:-----------:|:----------------------------------:|
| `enqueue` | O(1)        | O(1) no melhor caso, O(n) no pior  |
| `dequeue` | O(1)        | O(1)                               |
| `peek`    | O(1)        | O(1)                               |
| `isEmpty` | O(1)        | O(1)                               |
| `size`    | O(1)        | O(1)                               |
| `toList`  | O(n)        | O(n)                               |

- **Fila**: mantém referências para o início e o fim, por isso insere no fim e remove do início sem percorrer a lista.
- **Fila de Prioridade**: mantém a lista ordenada no momento da inserção. O `enqueue` percorre os nós enquanto a prioridade do seguinte for menor **ou igual** à do novo paciente, o que o coloca depois dos que têm o mesmo nível e preserva o FIFO nos empates. Em contrapartida, o paciente mais urgente está sempre no início, por isso `dequeue` e `peek` são O(1).

A análise detalhada está no [relatório](docs/relatorio.pdf).

## Decisões de desenho

- **FIFO nos empates**: a ordem de chegada é a ordem de inserção na fila. O campo `horaChegada` do paciente serve apenas para consulta.
- **Paciente triado**: um paciente está triado quando tem nível atribuído (`getNivel() != null`). Um paciente sem triagem é recusado pela Fila de Prioridade e não pode ser atendido (`TriagemPendenteException`).
- **Estado do atendimento**: o `Atendimento` é a fonte do estado (`AGUARDANDO`, `EM_ATENDIMENTO`, `ATENDIDO`); `Paciente.getEstado()` reflecte-o para consulta.
- **Um atendimento de cada vez**: não é possível chamar o próximo paciente enquanto houver um atendimento em curso.

## Limitações

A política de atendimento dá sempre preferência à Fila de Prioridade. Se continuarem a chegar pacientes prioritários, os pacientes da fila normal podem esperar indefinidamente (*starvation*). Trata-se de uma consequência da estratégia de escalonamento escolhida, discutida no relatório, e não de um erro da implementação.

## Documentação

- [Relatório](docs/relatorio.pdf)
- [Diagrama de classes](docs/diagrama-classes.png)
- [Fluxograma do atendimento](docs/fluxograma.png)
