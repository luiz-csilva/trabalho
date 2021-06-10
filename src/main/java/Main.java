package main.java;

import main.java.enums.TipoFornecedor;
import main.java.models.*;
import main.java.models.cliente.Cliente;
import main.java.models.cliente.PessoaFisica;
import main.java.models.cliente.PessoaJuridica;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.enums.TipoFornecedor.*;

public class Main {
    public static void main(String[] args) {
        try {
            List<Fornecedor> fornecedores = new ArrayList<>();
            List<Cliente> clientes = new ArrayList<>();
            List<Produto> produtos = new ArrayList<>();
            List<Pedido> pedidos = new ArrayList<>();
            cadastrarFornedor(fornecedores);
            cadastrarCliente(clientes);
            cadastrarProdutos(produtos);
            cadastrarPedidos(pedidos, produtos, fornecedores, clientes);
            entregarPedidos(pedidos);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void entregarPedidos(List<Pedido> pedidos) {
        boolean continuar = true;
        try {
            while (continuar) {
                List<String> clienteNome = pedidos
                        .stream()
                        .map(Pedido::getClienteNome)
                        .collect(Collectors.toList());
                String[] clientesExibicao = new String[clienteNome.size()];
                clientesExibicao = clienteNome.toArray(clientesExibicao);
                String clienteEscolhido = JOptionPane.showInputDialog(null, "O pedido de qual cliente que você deseja entregar?", "Cadastro", JOptionPane.PLAIN_MESSAGE, null, clientesExibicao, "0").toString();
                for (Pedido pedido : pedidos) {
                    if (pedido.getClienteNome().equals(clienteEscolhido)) {
                        JOptionPane.showMessageDialog(null, "Pedido entregue com sucesso.\n" + pedido);
                        return;
                    }
                }
                String desejaContinuar =  JOptionPane.showInputDialog(null, "Deseja continuar entregando produtos?", "Continuar cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Sim", "Não"}, "0").toString();
                if (desejaContinuar.equals("Não")) {
                    continuar = false;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void cadastrarPedidos(List<Pedido> pedidos, List<Produto> produtos, List<Fornecedor> fornecedores, List<Cliente> clientes) {
        boolean continuar = true;
        try {
            while (continuar) {
                produtos = selecionarProdutos(produtos);
                Fornecedor fornecedor = selecionarFornecedor(fornecedores);
                Cliente cliente = selecionarCliente(clientes);
                JOptionPane.showMessageDialog(null, "Digite o endereço de entrega na próxima janela");
                Endereco endereco = cadastrarEndereco();
                pedidos.add(new Pedido(cliente, endereco, produtos, fornecedor));
                String desejaContinuar =  JOptionPane.showInputDialog(null, "Deseja continuar cadastrando pedidos?", "Continuar cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Sim", "Não"}, "0").toString();
                if (desejaContinuar.equals("Não")) {
                    continuar = false;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static List<Produto> selecionarProdutos(List<Produto> produtos) {
        boolean continuar = true;
        List<Produto> produtosEscolhidos = new ArrayList<>();
        try {
            while (continuar) {
                List<String> produtosNome = produtos
                        .stream()
                        .map(Produto::getNome)
                        .collect(Collectors.toList());
                String[] produtosExibicao = new String[produtosNome.size()];
                produtosExibicao = produtosNome.toArray(produtosExibicao);
                String produtoEscolhidoNome = JOptionPane.showInputDialog(null, "Selecione o produto que deseja adicionar ao pedido", "Cadastro", JOptionPane.PLAIN_MESSAGE, null, produtosExibicao, "0").toString();
                Integer quantidadeProdutoEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade do produto escolhido"));
                for (Produto produto : produtos) {
                    if (produto.getNome().equals(produtoEscolhidoNome)){
                        produto.setQuantidade(quantidadeProdutoEscolhido);
                        produtosEscolhidos.add(produto);
                        break;
                    }
                }
                String desejaContinuar =  JOptionPane.showInputDialog(null, "Deseja adicionar outro produto?", "Continuar cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Sim", "Não"}, "0").toString();
                if (desejaContinuar.equals("Não")) {
                    continuar = false;
                }
            }
            return produtosEscolhidos;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
            return produtosEscolhidos;
        }
    }

    private static Cliente selecionarCliente(List<Cliente> clientes) {
        try {
            List<String> clienteNomes = clientes
                    .stream()
                    .map(cliente -> {
                        if (cliente instanceof PessoaJuridica) {
                            return ((PessoaJuridica) cliente).getRazaoSocial();
                        }
                        return ((PessoaFisica) cliente).getNome();
                    })
                    .collect(Collectors.toList());
            String[] clientesExibicao = new String[clienteNomes.size()];
            clientesExibicao = clienteNomes.toArray(clientesExibicao);
            String clienteEscolhidoNome = JOptionPane.showInputDialog(null, "Selecione o cliente que deseja adicionar ao pedido", "Cadastro", JOptionPane.PLAIN_MESSAGE, null, clientesExibicao, "0").toString();
            for (Cliente cliente : clientes) {
                if (cliente instanceof PessoaJuridica) {
                    if (((PessoaJuridica) cliente).getRazaoSocial().equals(clienteEscolhidoNome)) {
                        return cliente;
                    }
                }
                if (cliente instanceof PessoaFisica) {
                    if (((PessoaFisica) cliente).getNome().equals(clienteEscolhidoNome)) {
                        return cliente;
                    }
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private static Fornecedor selecionarFornecedor(List<Fornecedor> fornecedores) {
        try {
            List<String> fornecedoresNome = fornecedores
                    .stream()
                    .map(Fornecedor::getNome)
                    .collect(Collectors.toList());
            String[] fornecedoresExibicao = new String[fornecedoresNome.size()];
            fornecedoresExibicao = fornecedoresNome.toArray(fornecedoresExibicao);
            String fornecedorEscolhidoNome = JOptionPane.showInputDialog(null, "Selecione o fornecedor que deseja adicionar ao pedido", "Cadastro", JOptionPane.PLAIN_MESSAGE, null, fornecedoresExibicao, "0").toString();
            for (Fornecedor fornecedor : fornecedores) {
                if (fornecedor.getNome().equals(fornecedorEscolhidoNome)){
                    return fornecedor;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private static void cadastrarProdutos(List<Produto> produtos) {
        boolean continuar = true;
        try {
            while (continuar) {
                String nome = JOptionPane.showInputDialog("Insira o nome do produto");
                Double preco = Double.parseDouble(JOptionPane.showInputDialog("Insira a o preço do produto"));
                produtos.add(new Produto(nome, preco));
                String desejaContinuar =  JOptionPane.showInputDialog(null, "Deseja continuar cadastrando produtos?", "Continuar cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Sim", "Não"}, "0").toString();
                if (desejaContinuar.equals("Não")) {
                    continuar = false;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static Endereco cadastrarEndereco() {
        String cep = JOptionPane.showInputDialog("Insira o cep");
        String rua = JOptionPane.showInputDialog("Insira a rua");
        Integer numero = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero da residência"));
        return new Endereco(cep, rua, numero);
    }

    private static void cadastrarCliente(List<Cliente> clientes) {
        boolean continuar = true;
        try {
            while (continuar) {
                String[] tiposPessoa = { "Pessoa Fisica", "Pessoa Juridica" };
                String tipoPessoa = JOptionPane.showInputDialog(null, "Qual o tipo do cliente?", "Cadastro", JOptionPane.PLAIN_MESSAGE, null, tiposPessoa, "0").toString();
                String cpfCnpj;
                if (tipoPessoa.equals("Pessoa Juridica")) {
                    cpfCnpj = JOptionPane.showInputDialog("Insira o cnpj");
                } else {
                    cpfCnpj = JOptionPane.showInputDialog("Insira o cpf");
                }
                String nome = JOptionPane.showInputDialog("Insira o nome");
                Contato contato = cadastrarContato();
                Endereco endereco = cadastrarEndereco();
                if (tipoPessoa.equals("Pessoa Juridica")) {
                    clientes.add(new PessoaJuridica(cpfCnpj, nome, endereco, contato));
                }
                clientes.add(new PessoaFisica(cpfCnpj, nome, endereco, contato));
                String desejaContinuar =  JOptionPane.showInputDialog(null, "Deseja continuar cadastrando clientes?", "Continuar cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Sim", "Não"}, "0").toString();
                if (desejaContinuar.equals("Não")) {
                    continuar = false;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }

    }

    private static Contato cadastrarContato() {
        String telefone = JOptionPane.showInputDialog("Insira o telefone para contato");
        String email = JOptionPane.showInputDialog("Insira o email para contato");
        return new Contato(telefone, email);
    }

    private static void cadastrarFornedor(List<Fornecedor> fornecedores) {
        boolean continuar = true;
        try {
            while (continuar) {
                String tipoCadastro = JOptionPane.showInputDialog(null, "Qual o tipo do fornecedor?", "Cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{ "Loja", "Distribuidora", "Transportadora" }, "0").toString();
                TipoFornecedor tipoFornecedor = LOJA;
                if (tipoCadastro.equals("Distribuidora")){
                    tipoFornecedor = DISTRIBUIDORA;
                }
                if (tipoCadastro.equals("Transportadora")){
                    tipoFornecedor = TRANSPORTADORA;
                }
                String nome = JOptionPane.showInputDialog("Insira o nome do fornecedor");
                Contato contato = cadastrarContato();
                Endereco endereco = cadastrarEndereco();
                fornecedores.add(new Fornecedor(nome, endereco, contato, tipoFornecedor));
                String desejaContinuar =  JOptionPane.showInputDialog(null, "Deseja continuar cadastrando fornecedores?", "Continuar cadastro", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Sim", "Não"}, "0").toString();
                if (desejaContinuar.equals("Não")) {
                    continuar = false;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operação finalizada", "Encerrado", JOptionPane.WARNING_MESSAGE);
        }
    }
}
