package org.ehmsoft;

import org.ehmsoft.factory.AgentFactory;
import org.ehmsoft.agents.IAgent;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        String prompt = "";

        while (opcion != 6) {
            System.out.println("Seleccione el agente deseado");
            System.out.println("1. Resumidor de texto");
            System.out.println("2. Generador de texto");
            System.out.println("3. Traductor de textos ingles-español");
            System.out.println("4. Recomendador Ecommerce");
            System.out.println("5. Analizador de Feedback");
            System.out.println("6. Exit");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Ingrese el texto a resumir:");
                        prompt = scanner.nextLine();

                        IAgent agent =
                                AgentFactory.create(opcion);

                        String result =
                                agent.execute(prompt);

                        System.out.println("Agente: " + result);
                    }
                    case 2 -> {
                        System.out.println("Ingrese el tema del articulo que se desea generar:");
                        prompt = scanner.nextLine();

                        IAgent agent =
                                AgentFactory.create(opcion);

                        String result =
                                agent.execute(prompt);

                        System.out.println("Agente: " + result);
                    }
                    case 3 -> {
                        System.out.println("""
                                Ingrese el texto.
                                Finalice escribiendo:FIN
                                """);

                        StringBuilder sb = new StringBuilder();

                        while (true) {
                            String line = scanner.nextLine();

                            if ("FIN".equalsIgnoreCase(line)) {
                                break;
                            }

                            sb.append(line)
                                    .append("\n");
                        }

                        prompt = sb.toString();

                        IAgent agent =
                                AgentFactory.create(opcion);

                        String result =
                                agent.execute(prompt);

                        System.out.println("Agente: " + result);
                    }
                    case 4 -> {
                        System.out.println("Que producto desea consultar?");
                        prompt = scanner.nextLine();

                        IAgent agent =
                                AgentFactory.create(opcion);

                        String result =
                                agent.execute(prompt);

                        System.out.println("Agente: " + result);
                    }

                    case 5 -> {
                        System.out.println("Ingrese el feedback del cliente: ");
                        prompt = scanner.nextLine();

                        IAgent agent =
                                AgentFactory.create(opcion);

                        String result =
                                agent.execute(prompt);

                        System.out.println("Agente: " + result);
                    }
                    case 6 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, por favor ingrese un número.");
            }
        }
    }
}