module br.uniceub.sistematizacao.sistematizacao {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.uniceub.sistematizacao.sistematizacao to javafx.fxml;
    exports br.uniceub.sistematizacao.sistematizacao;
}