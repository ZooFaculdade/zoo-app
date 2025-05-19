package org.example.ui;

import org.example.controllers.AnimalControllers.CreateAnimalController;
import org.example.controllers.AnimalControllers.DeleteAnimalController;
import org.example.controllers.AnimalControllers.GetAnimalsController;
import org.example.controllers.AnimalControllers.UpdateAnimalController;
import org.example.model.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class AnimalView extends JFrame {

    private JTextField txtId = new JTextField(5);
    private JTextField txtName = new JTextField(20);
    private JTextField txtNickname = new JTextField(20);
    private JTextField txtAge = new JTextField(5);
    private JTextField txtSpecies = new JTextField(20);
    private JTextField txtBioClass = new JTextField(20);
    private JTextField txtBioOrder = new JTextField(20);
    private JTextField txtEnclosureId = new JTextField(10);

    private JTextArea areaLista = new JTextArea(15, 50);

    private JButton btnCriar = new JButton("Criar");
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnDeletar = new JButton("Deletar");
    private JButton btnListar = new JButton("Listar Todos");

    // Controllers
    private final CreateAnimalController createController = new CreateAnimalController();
    private final UpdateAnimalController updateController = new UpdateAnimalController();
    private final DeleteAnimalController deleteController = new DeleteAnimalController();
    private final GetAnimalsController listController = new GetAnimalsController();

    public AnimalView() {
        super("Gerenciamento de Animais");

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(9, 2));
        formPanel.add(new JLabel("ID (para Atualizar/Deletar):")); formPanel.add(txtId);
        formPanel.add(new JLabel("Nome:")); formPanel.add(txtName);
        formPanel.add(new JLabel("Apelido:")); formPanel.add(txtNickname);
        formPanel.add(new JLabel("Idade:")); formPanel.add(txtAge);
        formPanel.add(new JLabel("Espécie:")); formPanel.add(txtSpecies);
        formPanel.add(new JLabel("Classe Biológica:")); formPanel.add(txtBioClass);
        formPanel.add(new JLabel("Ordem Biológica:")); formPanel.add(txtBioOrder);
        formPanel.add(new JLabel("ID do Recinto:")); formPanel.add(txtEnclosureId);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnCriar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnDeletar);
        buttonPanel.add(btnListar);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(areaLista), BorderLayout.SOUTH);

        btnCriar.addActionListener(e -> criarAnimal());
        btnAtualizar.addActionListener(e -> atualizarAnimal());
        btnDeletar.addActionListener(e -> deletarAnimal());
        btnListar.addActionListener(e -> listarAnimais());

        areaLista.setEditable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void criarAnimal() {
        try {
            createController.createAnimal(
                    txtName.getText(),
                    txtNickname.getText(),
                    Integer.parseInt(txtAge.getText()),
                    txtSpecies.getText(),
                    txtBioClass.getText(),
                    txtBioOrder.getText(),
                    txtEnclosureId.getText()
            );
            limparCampos();
            listarAnimais();
        } catch (Exception ex) {
            mostrarErro("Erro ao criar: " + ex.getMessage());
        }
    }

    private void atualizarAnimal() {
        try {
            boolean success = updateController.updateAnimal(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtNickname.getText(),
                    Integer.parseInt(txtAge.getText()),
                    txtSpecies.getText(),
                    txtBioClass.getText(),
                    txtBioOrder.getText(),
                    txtEnclosureId.getText()
            );
            if (success) {
                mostrarMensagem("Animal atualizado com sucesso.");
                limparCampos();
                listarAnimais();
            } else {
                mostrarErro("Falha ao atualizar animal.");
            }
        } catch (Exception ex) {
            mostrarErro("Erro: " + ex.getMessage());
        }
    }

    private void deletarAnimal() {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean success = deleteController.deleteAnimal(id);
            if (success) {
                mostrarMensagem("Animal deletado com sucesso.");
                limparCampos();
                listarAnimais();
            } else {
                mostrarErro("Falha ao deletar animal.");
            }
        } catch (Exception ex) {
            mostrarErro("Erro: " + ex.getMessage());
        }
    }

    private void listarAnimais() {
        try {
            List<Animal> animais = listController.getAnimals();
            areaLista.setText("");
            for (Animal a : animais) {
                areaLista.append(a.toString() + "\n");
            }
        } catch (Exception ex) {
            mostrarErro("Erro ao listar animais: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtName.setText("");
        txtNickname.setText("");
        txtAge.setText("");
        txtSpecies.setText("");
        txtBioClass.setText("");
        txtBioOrder.setText("");
        txtEnclosureId.setText("");
    }

    private void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void mostrarErro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnimalView::new);
    }
}