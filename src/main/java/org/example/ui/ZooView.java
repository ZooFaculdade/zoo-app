package org.example.ui;

import org.example.controllers.AnimalControllers.CreateAnimalController;
import org.example.controllers.AnimalControllers.DeleteAnimalController;
import org.example.controllers.AnimalControllers.GetAnimalsController;
import org.example.controllers.AnimalControllers.UpdateAnimalController;
import org.example.controllers.EnclosureControllers.DeleteEnclosureController;
import org.example.controllers.EnclosureControllers.GetEnclosuresController;
import org.example.controllers.EnclosureControllers.UpdateEnclosureController;
import org.example.model.Animal;
import org.example.model.Enclosure;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ZooView {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zoológico");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 180);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel title = new JLabel("Bem-vindo ao zoológico!", JLabel.CENTER);
            JLabel label = new JLabel("Escolha uma opção:", JLabel.CENTER);

            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel buttonPanel = new JPanel();
            JButton verAnimaisBtn = new JButton("Ver animais");
            JButton adicionarAnimalBtn = new JButton("Adicionar animal");
            JButton adicionarJaulaAnimalBtn = new JButton("Criar cela");
            JButton verJaulasBtn = new JButton("Ver celas");
            buttonPanel.add(verAnimaisBtn);
            buttonPanel.add(adicionarAnimalBtn);
            buttonPanel.add(adicionarJaulaAnimalBtn);
            buttonPanel.add(verJaulasBtn);

            adicionarJaulaAnimalBtn.addActionListener(e -> abrirJanelaCadastroCela());


            verJaulasBtn.addActionListener(e -> {
                GetEnclosuresController controller = new GetEnclosuresController();
                List<Enclosure> enclosures = controller.getEnclosures();

                if (enclosures.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nenhuma cela encontrada.", "Celas", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JFrame enclosureFrame = new JFrame("Lista de Celas");
                enclosureFrame.setSize(500, 400);
                enclosureFrame.setLocationRelativeTo(null);

                JPanel enclosurePanel = new JPanel();
                enclosurePanel.setLayout(new BoxLayout(enclosurePanel, BoxLayout.Y_AXIS));

                for (Enclosure enclosure : enclosures) {
                    JPanel enclosureRow = new JPanel(new BorderLayout());
                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

                    infoPanel.add(new JLabel("Nome: " + enclosure.getName()));
                    infoPanel.add(new JLabel("Capacidade: " + enclosure.getCapacity()));
                    infoPanel.add(new JLabel("Ocupação atual: " + enclosure.getCurrentOccupancy()));
                    infoPanel.add(new JLabel("Ordens aceitas: " + enclosure.getOrders()));
                    infoPanel.add(new JLabel("Número de cela: " + enclosure.getId()));

                    JPanel buttonActions = new JPanel();
                    JButton editarBtn = new JButton("Editar");
                    JButton deletarBtn = new JButton("Deletar");

                    deletarBtn.addActionListener(ev -> {
                        int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Deseja deletar a cela \"" + enclosure.getName() + "\"?",
                                "Confirmação de deleção",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            DeleteEnclosureController deleteController = new DeleteEnclosureController();
                            boolean deleted = deleteController.deleteEnclosure(enclosure.getId());

                            if (deleted) {
                                JOptionPane.showMessageDialog(null, "Cela deletada com sucesso!");
                                enclosureFrame.dispose(); // fecha a janela atual
                                verJaulasBtn.doClick();   // reabre a lista de celas atualizada
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao deletar a cela.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });


                    editarBtn.addActionListener(ev -> abrirJanelaEdicaoCela(enclosure));

                    buttonActions.add(editarBtn);
                    buttonActions.add(deletarBtn);

                    enclosureRow.add(infoPanel, BorderLayout.CENTER);
                    enclosureRow.add(buttonActions, BorderLayout.EAST);
                    enclosureRow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    enclosurePanel.add(enclosureRow);
                    enclosurePanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }

                JScrollPane scrollPane = new JScrollPane(enclosurePanel);
                enclosureFrame.add(scrollPane);
                enclosureFrame.setVisible(true);
            });

            verAnimaisBtn.addActionListener(e -> {
                GetAnimalsController controller = new GetAnimalsController();
                List<Animal> animals = controller.getAnimals();

                if (animals.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nenhum animal encontrado.", "Animais", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JFrame animalFrame = new JFrame("Lista de Animais");
                animalFrame.setSize(500, 400);
                animalFrame.setLocationRelativeTo(null);

                JPanel animalPanel = new JPanel();
                animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));

                for (Animal animal : animals) {
                    JPanel animalRow = new JPanel(new BorderLayout());
                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

                    infoPanel.add(new JLabel("Nome: " + animal.getName()));
                    infoPanel.add(new JLabel("Apelido: " + animal.getNickname()));
                    infoPanel.add(new JLabel("Idade: " + animal.getAge()));
                    infoPanel.add(new JLabel("Espécie: " + animal.getSpecies()));
                    infoPanel.add(new JLabel("Classe: " + animal.getBioClass()));
                    infoPanel.add(new JLabel("Ordem: " + animal.getBioOrder()));
                    infoPanel.add(new JLabel("Número de cela: " + animal.getEnclosureId()));

                    JPanel buttonActions = new JPanel();
                    JButton editarBtn = new JButton("Editar");
                    JButton deletarBtn = new JButton("Deletar");

                    editarBtn.addActionListener(ev -> abrirJanelaEdicao(animal));

                    deletarBtn.addActionListener(ev -> {
                        int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Deseja deletar \"" + animal.getName() + "\"?",
                                "Confirmação de deleção",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.YES_OPTION) {
                            DeleteAnimalController deleteController = new DeleteAnimalController();
                            boolean deleted = deleteController.deleteAnimal(animal.getId());
                            if (deleted) {
                                JOptionPane.showMessageDialog(null, "Animal deletado com sucesso!");
                                animalFrame.dispose();
                                verAnimaisBtn.doClick();
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao deletar o animal.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    buttonActions.add(editarBtn);
                    buttonActions.add(deletarBtn);

                    animalRow.add(infoPanel, BorderLayout.CENTER);
                    animalRow.add(buttonActions, BorderLayout.EAST);
                    animalRow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    animalPanel.add(animalRow);
                    animalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }

                JScrollPane scrollPane = new JScrollPane(animalPanel);
                animalFrame.add(scrollPane);
                animalFrame.setVisible(true);
            });

            adicionarAnimalBtn.addActionListener(e -> abrirJanelaCadastro());

            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(title);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(label);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
            panel.add(buttonPanel);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void abrirJanelaEdicao(Animal animal) {
        JTextField nameField = new JTextField(animal.getName());
        JTextField nicknameField = new JTextField(animal.getNickname());
        JTextField ageField = new JTextField(String.valueOf(animal.getAge()));
        JTextField speciesField = new JTextField(animal.getSpecies());
        JTextField bioClassField = new JTextField(animal.getBioClass());
        JTextField bioOrderField = new JTextField(animal.getBioOrder());
        JTextField enclosureIdField = new JTextField(animal.getEnclosureId());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Apelido:"));
        panel.add(nicknameField);
        panel.add(new JLabel("Idade:"));
        panel.add(ageField);
        panel.add(new JLabel("Espécie:"));
        panel.add(speciesField);
        panel.add(new JLabel("Classe:"));
        panel.add(bioClassField);
        panel.add(new JLabel("Ordem:"));
        panel.add(bioOrderField);
        panel.add(new JLabel("Número de cela:"));
        panel.add(enclosureIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Animal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int age = parseInt(ageField.getText().trim());

                UpdateAnimalController controller = new UpdateAnimalController();
                boolean updated = controller.updateAnimal(
                        animal.getId(),
                        nameField.getText().trim(),
                        nicknameField.getText().trim(),
                        age,
                        speciesField.getText().trim(),
                        bioClassField.getText().trim(),
                        bioOrderField.getText().trim(),
                        enclosureIdField.getText().trim()
                );

                if (updated) {
                    JOptionPane.showMessageDialog(null, "Animal atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar o animal.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "A idade deve ser um número inteiro.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void abrirJanelaCadastro() {
        JTextField nameField = new JTextField();
        JTextField nicknameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField speciesField = new JTextField();
        JTextField bioClassField = new JTextField();
        JTextField bioOrderField = new JTextField();
        JTextField enclosureIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Apelido:"));
        panel.add(nicknameField);
        panel.add(new JLabel("Idade:"));
        panel.add(ageField);
        panel.add(new JLabel("Espécie:"));
        panel.add(speciesField);
        panel.add(new JLabel("Classe:"));
        panel.add(bioClassField);
        panel.add(new JLabel("Ordem:"));
        panel.add(bioOrderField);
        panel.add(new JLabel("Número da Cela:"));
        panel.add(enclosureIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Novo Animal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String nickname = nicknameField.getText().trim();
                int age = parseInt(ageField.getText().trim());
                String species = speciesField.getText().trim();
                String bioClass = bioClassField.getText().trim();
                String bioOrder = bioOrderField.getText().trim();
                int enclosureId = parseInt(enclosureIdField.getText().trim());

                if (name.isEmpty() || nickname.isEmpty() || species.isEmpty() || bioClass.isEmpty() || bioOrder.isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
                }

                CreateAnimalController controller = new CreateAnimalController();
                boolean created = controller.createAnimal(name, nickname, age, species, bioClass, bioOrder, String.valueOf(enclosureId));

                if (created) {
                    JOptionPane.showMessageDialog(null, "Animal criado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao criar o animal.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Idade e número da cela devem ser números inteiros.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void abrirJanelaCadastroCela() {
        JTextField nameField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField currentOccupancy = new JTextField();
        JTextField ordersField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Capacidade:"));
        panel.add(capacityField);
        panel.add(new JLabel("Ocupação atual:"));
        panel.add(currentOccupancy);
        panel.add(new JLabel("Ordens aceitas (separadas por vírgula):"));
        panel.add(ordersField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Criar Nova Cela", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int capacity = Integer.parseInt(capacityField.getText().trim());
                int currentOccupancyInt = Integer.parseInt(currentOccupancy.getText().trim());
                List<String> orders = Arrays.asList(ordersField.getText().trim().split("\\s*,\\s*"));

                if (name.isEmpty() || orders.isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
                }

                var controller = new org.example.controllers.EnclosureControllers.CreateEnclosureController();
                boolean created = controller.createEnclosure(currentOccupancyInt, capacity, name, orders);

                if (created) {
                    JOptionPane.showMessageDialog(null, "Cela criada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao criar a cela.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "A capacidade deve ser um número inteiro.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void abrirJanelaEdicaoCela(Enclosure enclosure) {
        JTextField nameField = new JTextField(enclosure.getName());
        JTextField capacityField = new JTextField(String.valueOf(enclosure.getCapacity()));
        JTextField ordersField = new JTextField(String.join(",", enclosure.getOrders()));

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Capacidade:"));
        panel.add(capacityField);
        panel.add(new JLabel("Ordens aceitas (separadas por vírgula):"));
        panel.add(ordersField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Cela", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int capacity = parseInt(capacityField.getText().trim());
                String name = nameField.getText().trim();
                List<String> orders = Arrays.asList(ordersField.getText().trim().split("\\s*,\\s*"));

                UpdateEnclosureController controller = new UpdateEnclosureController();
                controller.updateEnclosure(enclosure.getId(), capacity, name, orders);
                JOptionPane.showMessageDialog(null, "Cela atualizada com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Capacidade deve ser um número inteiro.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
