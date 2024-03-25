package Devops.aplicacaoClieneJava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static CadastroCliente cadastro;

    public static void main(String[] args) {
        cadastro = new CadastroCliente();
        SwingUtilities.invokeLater(() -> {
            exibirTelaBoasVindas();
            criarGUI();
        });
    }

    private static void exibirTelaBoasVindas() {
        JLabel lblMensagem = new JLabel("Bem-vindo à Sala de DevOps!", SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 15)); 

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(lblMensagem, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                null,
                panel,
                "Bem-vindo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void criarGUI() {
        JFrame frame = new JFrame("Cadastro de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); 

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Puc-Go-ADS1231", SwingConstants.CENTER);
        panel.add(lblTitulo); 

        JButton btnAdicionar = new JButton("Adicionar Cliente");
        JButton btnVisualizar = new JButton("Visualizar Clientes");
        JButton btnAtualizar = new JButton("Atualizar Cliente");
        JButton btnExcluir = new JButton("Excluir Cliente");
        JButton btnSair = new JButton("Sair");

        btnAdicionar.addActionListener(e -> adicionarCliente());
        btnVisualizar.addActionListener(e -> visualizarClientes());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        btnSair.addActionListener(e -> sair());

        panel.add(btnAdicionar);
        panel.add(btnVisualizar);
        panel.add(btnAtualizar);
        panel.add(btnExcluir);
        panel.add(btnSair);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void adicionarCliente() {
        JFrame addFrame = new JFrame("Adicionar Cliente");
        JPanel addPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNome = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JTextField txtTelefone = new JTextField(20);

        txtNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEmail.requestFocusInWindow();
            }
        });
    
        txtEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTelefone.requestFocusInWindow(); 
            }
        });
        addPanel.add(new JLabel("Nome:"));
        addPanel.add(txtNome);
        addPanel.add(new JLabel("Email:"));
        addPanel.add(txtEmail);
        addPanel.add(new JLabel("Telefone:"));
        addPanel.add(txtTelefone);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String telefone = txtTelefone.getText();

                if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
                    Cliente cliente = new Cliente(nome, email, telefone);
                    cadastro.addCliente(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
                    addFrame.dispose(); // Fecha a janela após adicionar o cliente
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.dispose(); 
            }
        });

        addPanel.add(btnAdicionar);
        addPanel.add(btnCancelar);

        addFrame.add(addPanel);
        addFrame.pack();
        addFrame.setVisible(true);
    }


    private static void visualizarClientes() {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : cadastro.getClientes()) {
            sb.append(cliente.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Clientes", JOptionPane.PLAIN_MESSAGE);
    }
    private static void atualizarCliente() {
        JFrame updateFrame = new JFrame("Atualizar Cliente");
        JPanel updatePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> clienteComboBox = new JComboBox<>();
        for (Cliente cliente : cadastro.getClientes()) {
            clienteComboBox.addItem(cliente.getNome());
        }

        JTextField txtNome = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JTextField txtTelefone = new JTextField(20);

        txtNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEmail.requestFocusInWindow(); 
            }
        });
    
        txtEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTelefone.requestFocusInWindow(); 
            }
        });
    
        updatePanel.add(new JLabel("Selecione o cliente:"));
        updatePanel.add(clienteComboBox);
        updatePanel.add(new JLabel("Novo nome:"));
        updatePanel.add(txtNome);
        updatePanel.add(new JLabel("Novo email:"));
        updatePanel.add(txtEmail);
        updatePanel.add(new JLabel("Novo telefone:"));
        updatePanel.add(txtTelefone);

        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = clienteComboBox.getSelectedIndex();
                if (selectedIndex != -1) {
                    String nome = txtNome.getText();
                    String email = txtEmail.getText();
                    String telefone = txtTelefone.getText();

                    if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
                        Cliente cliente = cadastro.getClientes().get(selectedIndex);
                        cliente.setNome(nome);
                        cliente.setEmail(email);
                        cliente.setTelefone(telefone);
                        cadastro.updateCliente(selectedIndex, cliente);
                        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                        updateFrame.dispose(); // Fecha a janela após atualizar o cliente
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente para atualizar!");
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame.dispose();
            }
        });

        updatePanel.add(btnAtualizar);
        updatePanel.add(btnCancelar);

        updateFrame.add(updatePanel);
        updateFrame.pack();
        updateFrame.setVisible(true);
    }

    private static void excluirCliente() {
        JFrame removeFrame = new JFrame("Excluir Cliente");
        JPanel removePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        removePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JComboBox<String> clienteComboBox = new JComboBox<>();
        for (Cliente cliente : cadastro.getClientes()) {
            clienteComboBox.addItem(cliente.getNome());
        }
    
        removePanel.add(new JLabel("Selecione o cliente a ser removido:"));
        removePanel.add(clienteComboBox);
    
        JButton btnExcluir = new JButton("Excluir");
        JButton btnCancelar = new JButton("Cancelar");
    
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = clienteComboBox.getSelectedIndex();
                if (selectedIndex != -1) {
                    cadastro.removeCliente(selectedIndex);
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                    removeFrame.dispose(); // Fecha a janela após remover o cliente
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente para remover!");
                }
            }
        });
    
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFrame.dispose(); 
            }
        });
    
        removePanel.add(btnExcluir);
        removePanel.add(btnCancelar);
    
        removeFrame.add(removePanel);
        removeFrame.pack();
        removeFrame.setVisible(true);
    }
    
    private static void sair() {
        System.exit(0);
    }
}
