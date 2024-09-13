package br.uniceub.sistematizacao.sistematizacao;

import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {
        ContactList contactList = new ContactList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Buscar contato");
            System.out.println("3. Remover contato");
            System.out.println("4. Listar todos os contatos");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consumir o newline

            switch (choice) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();
                    contactList.addContact(new Contact(name, phoneNumber, email));
                    break;

                case 2:
                    System.out.print("Digite o nome ou telefone para buscar: ");
                    String searchTerm = scanner.nextLine();
                    Contact foundContact = contactList.searchContact(searchTerm);
                    if (foundContact != null) {
                        System.out.println("Contato encontrado: " + foundContact);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome ou telefone para remover: ");
                    String termToRemove = scanner.nextLine();
                    boolean removed = contactList.removeContact(termToRemove);
                    if (removed) {
                        System.out.println("Contato removido com sucesso.");
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Lista de contatos:");
                    contactList.listContacts().forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Encerrando...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
