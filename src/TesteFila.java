public static void main(String[] args) throws PacienteInvalidoException {

        Fila fila = new Fila();

        Paciente p1 = new Paciente(0, "Joao", 18);
        Paciente p2 = new Paciente(1, "Maria", 30);
        Paciente p3 = new Paciente(2, "Afonso", 30);


        fila.enqueue(p1);
        fila.enqueue(p2);
        fila.enqueue(p3);


        System.out.println("Tamanho: " + fila.size());

        try {
            System.out.println("Primeiro: " + fila.peek());

            System.out.println("Saiu: " + fila.dequeue());
            System.out.println("Saiu: " + fila.dequeue());

            System.out.println("Tamanho: " + fila.size());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
