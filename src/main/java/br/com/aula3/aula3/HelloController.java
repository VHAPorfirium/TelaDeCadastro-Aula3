package br.com.aula3.aula3;

import br.com.aula3.aula3.dao.ClienteDAO;
import br.com.aula3.aula3.model.Cliente;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Button bt_limpar;

    @FXML
    private Button bt_sair;

    @FXML
    private Button bt_salvar;

    @FXML
    private TextField tx_cpf;

    @FXML
    private TextField tx_endereco;

    @FXML
    private TextField tx_nome;

    @FXML
    private TextField tx_telefone;

    @FXML
    void limpar(ActionEvent event) {
        tx_nome.clear();
        tx_cpf.clear();
        tx_telefone.clear();
        tx_endereco.clear();
    }

    @FXML
    void sair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void salvar(ActionEvent event) {
        // Validação simples: verifica se todos os campos foram preenchidos
        if (tx_nome.getText().isEmpty() ||
                tx_cpf.getText().isEmpty() ||
                tx_telefone.getText().isEmpty() ||
                tx_endereco.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Campos Vazios");
            alert.setContentText("Por favor, preencha todos os campos!");
            alert.showAndWait();
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(tx_nome.getText());
        cliente.setCpf(tx_cpf.getText());
        cliente.setTelefone(tx_telefone.getText());
        cliente.setEndereco(tx_endereco.getText());

        // Instancia o ClienteDAO e salva o cliente no banco de dados
        ClienteDAO dao = new ClienteDAO();
        try {
            dao.create(cliente);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Cliente salvo com sucesso!");
            alert.showAndWait();

            // Após salvar, limpa os campos
            limpar(null);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar o cliente");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
