package br.uniceub.sistematizacao.sistematizacao;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ContactManagerGUI extends Application {

    private ContactList contactList = new ContactList(); // Inicializa a lista de contatos

    public static void main(String[] args) {
        launch(args); // Inicia o JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Contatos");

        // Layout da interface (GridPane)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); // Define o padding do layout
        grid.setVgap(8); // Espaçamento vertical entre os elementos
        grid.setHgap(10); // Espaçamento horizontal entre os elementos

        // Labels e campos de texto para entrada de dados
        Label nameLabel = new Label("Nome Completo:");
        GridPane.setConstraints(nameLabel, 0, 0); // Define a posição do label
        TextField nameInput = new TextField(); // Campo de texto para o nome
        GridPane.setConstraints(nameInput, 1, 0); // Define a posição do campo de texto

        Label phoneLabel = new Label("Telefone com DDD:");
        GridPane.setConstraints(phoneLabel, 0, 1);
        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 1);

        Label emailLabel = new Label("E-mail:");
        GridPane.setConstraints(emailLabel, 0, 2);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 2);

        // Adiciona uma barra de pesquisa logo abaixo do campo de email
        Label searchLabel = new Label("Buscar por Nome ou Telefone:");
        GridPane.setConstraints(searchLabel, 0, 3);
        TextField searchInput = new TextField();
        GridPane.setConstraints(searchInput, 1, 3);

        // Botões para as ações (lado a lado)
        Button addButton = new Button("Adicionar Contato");
        GridPane.setConstraints(addButton, 0, 4); // Define a posição do botão

        Button listButton = new Button("Listar Contatos");
        GridPane.setConstraints(listButton, 1, 4); // Coloca ao lado do botão anterior

        Button searchButton = new Button("Buscar Contato");
        GridPane.setConstraints(searchButton, 2, 4); // Coloca ao lado do botão anterior

        Button removeButton = new Button("Remover Contato");
        GridPane.setConstraints(removeButton, 3, 4); // Coloca ao lado do botão anterior

        // Campo de exibição de contatos
        TextArea contactDisplay = new TextArea();
        contactDisplay.setEditable(false); // Impede a edição do campo
        GridPane.setConstraints(contactDisplay, 0, 5, 4, 1); // O campo vai ocupar 4 colunas

        // Ação para o botão "Adicionar Contato"
        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            String phone = phoneInput.getText();
            String email = emailInput.getText();
            // Verifica se todos os campos estão preenchidos
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                contactList.addContact(new Contact(name, phone, email)); // Adiciona o contato à lista
                contactDisplay.appendText("Contato adicionado: " + name + "\n"); // Exibe mensagem
                nameInput.clear(); // Limpa o campo de nome
                phoneInput.clear(); // Limpa o campo de telefone
                emailInput.clear(); // Limpa o campo de email
            } else {
                contactDisplay.appendText("Por favor, preencha todos os campos!\n"); // Alerta de campos vazios
            }
        });

        // Ação para o botão "Listar Contatos"
        listButton.setOnAction(e -> {
            contactDisplay.clear(); // Limpa o campo de exibição
            contactDisplay.appendText("Lista de Contatos:\n");
            // Itera sobre os contatos e os exibe no campo
            contactList.listContacts().forEach(contact ->
                    contactDisplay.appendText(contact.toString() + "\n")
            );
        });

        // Ação para o botão "Buscar Contato"
        searchButton.setOnAction(e -> {
            String searchTerm = searchInput.getText(); // Agora busca pelo campo de pesquisa
            if (!searchTerm.isEmpty()) {
                Contact foundContact = contactList.searchContact(searchTerm);
                if (foundContact != null) {
                    contactDisplay.appendText("Contato encontrado: " + foundContact + "\n");
                } else {
                    contactDisplay.appendText("Contato não encontrado.\n");
                }
            } else {
                contactDisplay.appendText("Por favor, insira um nome ou telefone para busca.\n");
            }
        });

        // Ação para o botão "Remover Contato"
        removeButton.setOnAction(e -> {
            String searchTerm = searchInput.getText(); // Usa o campo de busca para remover
            if (!searchTerm.isEmpty()) {
                boolean removed = contactList.removeContact(searchTerm);
                if (removed) {
                    contactDisplay.appendText("Contato removido: " + searchTerm + "\n");
                    searchInput.clear();
                } else {
                    contactDisplay.appendText("Contato não encontrado para remoção.\n");
                }
            } else {
                contactDisplay.appendText("Por favor, insira um nome ou telefone para remover.\n");
            }
        });

        // Adiciona todos os componentes ao layout
        grid.getChildren().addAll(nameLabel, nameInput, phoneLabel, phoneInput, emailLabel, emailInput, searchLabel, searchInput, addButton, listButton, searchButton, removeButton, contactDisplay);

        // Define a cena e o stage
        Scene scene = new Scene(grid, 600, 400); // Cria a cena com tamanho especificado
        primaryStage.setScene(scene); // Adiciona a cena ao stage
        primaryStage.show(); // Exibe o stage
    }
}
