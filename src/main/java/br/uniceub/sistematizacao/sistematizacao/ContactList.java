package br.uniceub.sistematizacao.sistematizacao;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private Node head;

    public ContactList() {
        this.head = null;
    }

    // Adiciona um novo contato
    public void addContact(Contact contact) {
        Node newNode = new Node(contact);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // Busca por nome ou número de telefone
    public Contact searchContact(String searchTerm) {
        Node current = head;
        while (current != null) {
            Contact contact = current.getContact();
            if (contact.getName().equalsIgnoreCase(searchTerm) || contact.getPhone().equals(searchTerm)) {
                return contact;
            }
            current = current.getNext();
        }
        return null;
    }

    // Remove um contato pelo nome ou telefone
    public boolean removeContact(String searchTerm) {
        if (head == null) {
            return false;
        }

        if (head.getContact().getName().equalsIgnoreCase(searchTerm) || head.getContact().getPhone().equals(searchTerm)) {
            head = head.getNext();
            return true;
        }

        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getContact().getName().equalsIgnoreCase(searchTerm) ||
                    current.getNext().getContact().getPhone().equals(searchTerm)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    // Lista todos os contatos e retorna uma lista de contatos
    public List<Contact> listContacts() {
        List<Contact> contactList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            contactList.add(current.getContact());
            current = current.getNext();
        }
        return contactList;
    }
}
