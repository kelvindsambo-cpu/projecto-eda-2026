# Projecto EDA — Sistema de Gestão de Atendimento Hospitalar

## 1. Visão geral do projecto

### Tema
Desenvolver, em **Java**, uma aplicação académica para simular a gestão de atendimento de pacientes num hospital.

### Objectivo principal
O projecto deve demonstrar, de forma prática, a utilização de:

- **Fila (FIFO)** para pacientes de atendimento normal;
- **Fila de Prioridade** para pacientes prioritários;
- Programação orientada a objectos;
- Análise de complexidade das operações;
- Integração das estruturas de dados numa aplicação funcional.

> **Importante:** o projecto é uma simulação académica. Não pretende reproduzir um sistema médico real nem tomar decisões clínicas.

---

# 2. Modelo simplificado de triagem

Será utilizada uma adaptação académica, inspirada nos níveis do Sistema de Triagem de Manchester, mas **sem implementar os seus fluxogramas clínicos reais**.

| Nível | Categoria | Cor | Tratamento no sistema |
|---:|---|---|---|
| 1 | Emergência | Vermelho | Fila de Prioridade |
| 2 | Muito urgente | Laranja | Fila de Prioridade |
| 3 | Urgente | Amarelo | Fila de Prioridade |
| 4 | Normal | Verde | Fila Normal |
| 5 | Não urgente | Azul | Fila Normal |

A categoria é seleccionada pelo utilizador/funcionário durante a triagem.

O sistema **não deve diagnosticar doenças nem calcular automaticamente uma prioridade clínica**.

---

# 3. Regras fundamentais do sistema

## Fila Normal

Pacientes dos níveis **4 e 5** entram numa fila FIFO.

Exemplo:

```text
A → B → C → D
```

Se A chegou primeiro, A é atendido primeiro.

Operações esperadas:

```text
enqueue()
dequeue()
peek()
isEmpty()
size()
```

---

## Fila de Prioridade

Pacientes dos níveis **1, 2 e 3** entram numa fila ordenada por prioridade.

A ordem é:

```text
Nível 1 → Nível 2 → Nível 3
```

Em caso de empate, aplica-se FIFO.

Exemplo:

```text
A → nível 3
B → nível 1
C → nível 2
D → nível 1
E → nível 3
```

Resultado:

```text
B(1) → D(1) → C(2) → A(3) → E(3)
```

Ou seja:

1. Primeiro compara-se o nível de prioridade;
2. Se os níveis forem iguais, compara-se a ordem de chegada.

---

# 4. Política de atendimento

Na versão inicial:

```text
Existe paciente prioritário?
        │
   ┌────┴────┐
  SIM        NÃO
   │           │
   ▼           ▼
Fila de      Fila
Prioridade   Normal
```

Portanto:

- Se a fila prioritária não estiver vazia, atende-se o primeiro paciente prioritário.
- Se estiver vazia, atende-se o primeiro paciente da fila normal.

### Extensão opcional

Se o grupo quiser aprofundar EDA, pode implementar uma política de fairness:

> Depois de 3 pacientes prioritários consecutivos, atender 1 paciente normal, caso exista.

Isto permite discutir o problema de **starvation**.

Esta extensão é opcional e não deve comprometer a implementação principal.

---

# 5. Estrutura geral do projecto

A estrutura recomendada é:

```text
Hospital-EDA/
│
├── src/
│   ├── estruturas/
│   │   ├── No.java
│   │   ├── Fila.java
│   │   └── FilaPrioridade.java
│   │
│   ├── modelo/
│   │   ├── Paciente.java
│   │   ├── Triagem.java
│   │   └── Atendimento.java
│   │
│   ├── servicos/
│   │   └── GestorAtendimento.java
│   │
│   ├── gui/
│   │   ├── JanelaPrincipal.java
│   │   ├── JanelaRegisto.java
│   │   ├── JanelaTriagem.java
│   │   └── JanelaAtendimento.java
│   │
│   └── Main.java
│
├── testes/
│   ├── TesteFila.java
│   ├── TesteFilaPrioridade.java
│   └── TesteSistema.java
│
├── docs/
│   ├── DiagramaClasses
│   ├── Fluxograma
│   └── Relatorio.md
│
└── README.md
```

A estrutura pode ser simplificada se o docente não exigir packages.

---

# 6. Divisão das tarefas pelos 6 membros

## MEMBRO 1 — Fila FIFO

### Responsabilidade
Implementar a estrutura de dados da **Fila Normal**.

### Classes

```text
estruturas/
├── No.java
└── Fila.java
```

### `No.java`

Deve representar um nó ligado ao próximo nó.

Conceito:

```text
[P1] → [P2] → [P3] → null
```

### `Fila.java`

Implementar pelo menos:

```java
enqueue(Paciente paciente)
dequeue()
peek()
isEmpty()
size()
```

### Requisitos

- Não utilizar `Queue`/`LinkedList` do Java para substituir a implementação.
- Garantir comportamento FIFO.
- Tratar correctamente a fila vazia.
- Testar inserção e remoção de vários pacientes.

### Testes mínimos

```text
✓ fila inicialmente vazia
✓ inserir um paciente
✓ inserir vários pacientes
✓ remover respeitando FIFO
✓ peek não remove
✓ remover de fila vazia
✓ size()
```

### Entrega

- `No.java`
- `Fila.java`
- `TesteFila.java`
- Pequena explicação da complexidade.

---

# MEMBRO 2 — Fila de Prioridade

### Responsabilidade
Implementar a **Fila de Prioridade**.

### Classe principal

```text
estruturas/
└── FilaPrioridade.java
```

### Regra

Prioridade:

```text
1 > 2 > 3
```

Quanto menor o número, maior a prioridade.

Empates:

```text
mesma prioridade → quem chegou primeiro sai primeiro
```

### Exemplo

Entrada:

```text
A(3)
B(1)
C(2)
D(1)
E(3)
```

Saída:

```text
B(1)
D(1)
C(2)
A(3)
E(3)
```

### Operações

Implementar pelo menos:

```java
enqueue(Paciente paciente)
dequeue()
peek()
isEmpty()
size()
```

### Atenção

A fila de prioridade deve ser implementada pelo grupo, e não substituída directamente por:

```java
PriorityQueue<Paciente>
```

O objectivo é demonstrar a estrutura de dados.

### Testes mínimos

```text
✓ inserir prioridades diferentes
✓ inserir prioridades iguais
✓ FIFO dentro da mesma prioridade
✓ remover em ordem correcta
✓ peek
✓ fila vazia
```

### Entrega

- `FilaPrioridade.java`
- testes
- análise de complexidade.

---

# MEMBRO 3 — Modelo do Hospital

### Responsabilidade
Criar as classes que representam os elementos do domínio.

### Classes sugeridas

```text
modelo/
├── Paciente.java
├── Triagem.java
└── Atendimento.java
```

## `Paciente`

Possíveis atributos:

```java
private int id;
private String nome;
private int idade;
private String numeroUtente;
private NivelTriagem nivel;
private long ordemChegada;
```

Não é necessário criar dezenas de atributos.

### `NivelTriagem`

Pode ser um `enum`:

```java
public enum NivelTriagem {
    EMERGENCIA(1, "Vermelho"),
    MUITO_URGENTE(2, "Laranja"),
    URGENTE(3, "Amarelo"),
    NORMAL(4, "Verde"),
    NAO_URGENTE(5, "Azul");
}
```

A implementação exacta pode ser adaptada pelo grupo.

## `Triagem`

Responsável por representar o processo académico de classificação.

Não deve diagnosticar o paciente.

Pode simplesmente receber/guardar o nível seleccionado pelo funcionário.

## `Atendimento`

Pode representar o estado do atendimento:

```text
AGUARDANDO
EM_ATENDIMENTO
ATENDIDO
```

### Entrega

- `Paciente.java`
- `NivelTriagem.java`
- `Triagem.java`
- `Atendimento.java`
- documentação das classes.

---

# MEMBRO 4 — Gestor de Atendimento

### Responsabilidade
Implementar a lógica que liga as duas filas ao hospital.

### Classe

```text
servicos/
└── GestorAtendimento.java
```

Deve possuir algo semelhante a:

```java
private Fila filaNormal;
private FilaPrioridade filaPrioridade;
```

### Responsabilidades

Implementar métodos para:

```text
registar/adicionar paciente
encaminhar paciente para a fila correcta
chamar próximo paciente
finalizar atendimento
consultar estado das filas
```

### Regra principal

```text
Níveis 1–3 → FilaPrioridade
Níveis 4–5 → Fila
```

### `chamarProximo()`

Deve aplicar a política:

```text
se FilaPrioridade não estiver vazia:
    retirar da FilaPrioridade
senão:
    retirar da Fila Normal
```

### Não fazer

Não colocar código Swing nesta classe.

A lógica do hospital deve funcionar mesmo que a GUI seja removida.

### Entrega

- `GestorAtendimento.java`
- testes de integração da lógica.

---

# MEMBRO 5 — Interface Gráfica

### Responsabilidade
Criar a interface em Java Swing.

A GUI deve utilizar as classes dos outros membros e **não implementar novamente as filas**.

### Janelas/painéis sugeridos

```text
gui/
├── JanelaPrincipal.java
├── JanelaRegisto.java
├── JanelaTriagem.java
└── JanelaAtendimento.java
```

## Janela Principal

Pode conter:

```text
=================================
    ATENDIMENTO HOSPITALAR
=================================

[ Registar Paciente ]

[ Fazer Triagem ]

[ Ver Filas ]

[ Chamar Próximo ]

[ Finalizar Atendimento ]

[ Estatísticas ]
```

## Registo

Campos:

```text
Nome
Idade
Número de utente
```

## Triagem

Mostrar:

```text
1 — Emergência — Vermelho
2 — Muito urgente — Laranja
3 — Urgente — Amarelo
4 — Normal — Verde
5 — Não urgente — Azul
```

## Visualização das filas

Exemplo:

```text
FILA PRIORITÁRIA

1. João — Nível 1
2. Maria — Nível 1
3. Carlos — Nível 2


FILA NORMAL

1. Ana — Nível 4
2. Pedro — Nível 5
```

## Atendimento

Mostrar:

```text
PRÓXIMO PACIENTE

Nome: João
Nível: Emergência

[ CHAMAR ]
```

### Regra importante

A GUI deve chamar métodos como:

```java
gestor.chamarProximo();
```

e não manipular directamente:

```java
fila.dequeue();
```

A lógica deve permanecer no `GestorAtendimento`.

### Entrega

- todas as classes GUI;
- integração com `GestorAtendimento`;
- interface funcional.

---

# MEMBRO 6 — Testes, Integração e Documentação

### Responsabilidade

Este membro trabalha transversalmente com todos os outros.

Não deve esperar até ao final para começar.

## Testes

Criar:

```text
TesteFila.java
TesteFilaPrioridade.java
TesteSistema.java
```

### Casos importantes

#### Caso 1 — Só pacientes normais

```text
N1
N2
N3
```

Atendimento:

```text
N1 → N2 → N3
```

#### Caso 2 — Só prioritários

```text
P3
P1
P2
P1
```

Atendimento:

```text
P1 → P1 → P2 → P3
```

Mantendo FIFO nos empates.

#### Caso 3 — Mistura

```text
N1
P3
N2
P1
P3
P2
```

Verificar se a política produz a ordem esperada.

#### Caso 4 — Filas vazias

Verificar:

```text
dequeue()
peek()
chamarProximo()
```

quando não existem pacientes.

## Documentação

Preparar:

- Diagrama de classes;
- fluxograma;
- descrição das estruturas;
- análise de complexidade;
- casos de teste;
- README;
- organização do relatório.

---

# 7. Dependências entre os membros

A ordem de desenvolvimento recomendada é:

```text
             MEMBRO 3
          Modelo Hospital
                │
        ┌───────┴───────┐
        ▼               ▼
   MEMBRO 1         MEMBRO 2
     Fila        Fila Prioridade
        │               │
        └───────┬───────┘
                ▼
             MEMBRO 4
       Gestor Atendimento
                │
                ▼
             MEMBRO 5
                GUI
                │
                ▼
             MEMBRO 6
     Testes + Integração
```

Na prática, os membros 1, 2 e 3 podem trabalhar em paralelo.

---

# 8. Contratos entre membros

Para evitar conflitos no GitHub, definam primeiro as interfaces dos componentes.

Por exemplo, ambos os tipos de fila devem ter operações equivalentes:

```java
enqueue(...)
dequeue()
peek()
isEmpty()
size()
```

O membro 4 deve saber apenas como utilizar essas operações.

Assim, se a implementação interna mudar, o `GestorAtendimento` não precisa de ser refeito.

---

# 9. Fluxo principal da aplicação

O fluxo esperado é:

```text
1. Registar paciente
        ↓
2. Fazer triagem
        ↓
3. Obter nível 1–5
        ↓
4. Encaminhar automaticamente
        ↓
   ┌────┴────┐
   ↓         ↓
1–3        4–5
   ↓         ↓
Prioridade Normal
   │         │
   └────┬────┘
        ↓
5. Chamar próximo
        ↓
6. Atendimento
        ↓
7. Finalizar
```

---

# 10. O que NÃO fazer

Para manter o projecto adequado à cadeira de EDA:

- Não criar um sistema hospitalar gigante.
- Não implementar consultas, farmácia, internamento, pagamentos, seguros, etc., salvo se o docente exigir.
- Não utilizar `PriorityQueue` para substituir a estrutura de prioridade.
- Não utilizar `Queue`/`LinkedList` para substituir a Fila que vocês devem implementar.
- Não colocar toda a lógica dentro da GUI.
- Não fazer diagnóstico médico.
- Não criar um algoritmo clínico real de Manchester.
- Não adicionar funcionalidades apenas para aumentar o número de classes.

O foco deve permanecer:

```text
POO
 +
Fila
 +
Fila de Prioridade
 +
Algoritmos
 +
Análise de complexidade
 +
Aplicação prática
```

---

# 11. Exemplo de cenário completo

Suponhamos que chegam:

```text
08:00 — Ana     — nível 4
08:02 — João    — nível 2
08:03 — Maria   — nível 1
08:05 — Pedro   — nível 4
08:06 — Carlos  — nível 2
08:08 — Sara    — nível 3
```

As estruturas ficam:

```text
FILA PRIORITÁRIA

Maria  — nível 1
João   — nível 2
Carlos — nível 2
Sara   — nível 3


FILA NORMAL

Ana
Pedro
```

Atendimento:

```text
1. Maria
2. João
3. Carlos
4. Sara
5. Ana
6. Pedro
```

Se for implementada a política opcional de fairness, a ordem poderá ser diferente conforme a regra escolhida.

---

# 12. Objectivo académico

O relatório deve conseguir responder:

### Por que usar uma fila?

Porque pacientes normais devem ser atendidos segundo a ordem de chegada.

### Por que usar uma fila de prioridade?

Porque pacientes com diferentes níveis de urgência não devem necessariamente ser atendidos apenas pela ordem de chegada.

### Por que manter FIFO dentro da mesma prioridade?

Para preservar a ordem de chegada quando dois pacientes possuem o mesmo nível.

### Qual é a complexidade das operações?

Deve ser analisada de acordo com a implementação efectivamente utilizada.

### O que acontece quando chegam continuamente pacientes prioritários?

Pode surgir **starvation** dos pacientes normais. Isto pode ser discutido e, opcionalmente, resolvido através de uma política de fairness.

---

# 13. Regra de ouro para o grupo

Cada membro deve conseguir explicar:

1. O que a sua classe faz;
2. Por que ela existe;
3. Como funciona internamente;
4. Qual é a complexidade das operações;
5. Como ela comunica com as outras classes.

O projecto não deve ser dividido de forma que cada pessoa conheça apenas o seu código.

**Todos devem compreender especialmente `Fila`, `FilaPrioridade` e `GestorAtendimento`, pois são o núcleo do projecto de EDA.**
