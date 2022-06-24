/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@ViewScoped
public class ClienteController implements Serializable {

    @Inject
    private ClienteDAO clienteDAO;

    private Cliente cliente;
    private List<Cliente> clientes;
    private List<Cliente> clientesSelecionados;

    public ClienteController() {
        cliente = new Cliente();
    }

    @PostConstruct
    public void init() {
        clientes = clienteDAO.buscarTodos();
    }

    public void openNew() {
        this.cliente = new Cliente();
    }

    public void cadastrarCliente() {
        if (!cliente.getNome().equals("")) {
            cliente.setNome(cliente.getNome().trim());

            if (!cliente.getCpf().equals("")) {
                cliente.setDataCadastro(new Date());
                cliente.setStatus(true);

                try {
                    clienteDAO.create(cliente);
                    clientes.add(cliente);
                    this.openNew();
                    Util.addMessageInformation("Cliente Cadastrado");

                } catch (Exception e) {
                    e.printStackTrace();
                    Util.addMessageError("Cliente com CPF semelhante já cadastrado");
                    PrimeFaces.current().ajax().update("form:messages");
                    return;
                }

            } else {
                Util.addMessageWarning("Informe o CPF");
            }
        } else {
            Util.addMessageWarning("Informe o nome");
        }

        PrimeFaces.current().executeScript("PF('createClienteDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
    }

    public void alterarCliente() {
        Cliente c = clienteDAO.buscarPorCpf(cliente.getCpf());

        if (c != null) {
            if (!cliente.getNome().equals("")) {
                if (c.getNome().equals(cliente.getNome())) {
                    Util.addMessageWarning("Dados iguais");
                    return;
                }
                cliente.setNome(cliente.getNome().trim());

                if (!cliente.getCpf().equals("")) {
                    cliente.setDataCadastro(new Date());

                    clienteDAO.edit(cliente);
                    clientes.set(clientes.indexOf(c), cliente);
                    this.openNew();
                    Util.addMessageInformation("Cliente Alterado");
                } else {
                    Util.addMessageWarning("Informe o CPF");
                }
            } else {
                Util.addMessageWarning("Informe o nome");
            }
        } else {
            Util.addMessageWarning("Cliente não Encontrado");
        }

        PrimeFaces.current().executeScript("PF('editClienteDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
    }

    public void desativarCliente() {
        if (cliente.getStatus()) {
            Cliente c = clienteDAO.buscarPorCpf(cliente.getCpf());

            if (c != null) {
                cliente.setStatus(false);
                clientes.set(clientes.indexOf(c), cliente);
                clienteDAO.edit(cliente);
            }

            this.cliente = null;
        } else {
            Util.addMessageWarning("Cliente já está desativado");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
            return;
        }

        Util.addMessageInformation("Cliente Desativado");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
    }
    
    
    public void reativarCliente() {
        if (!cliente.getStatus()) {
            Cliente c = clienteDAO.buscarPorCpf(cliente.getCpf());

            if (c != null) {
                cliente.setStatus(true);
                clientes.set(clientes.indexOf(c), cliente);
                clienteDAO.edit(cliente);
            }

            this.cliente = null;
        } else {
            Util.addMessageWarning("Cliente já está ativo");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
            return;
        }

        Util.addMessageInformation("Cliente Reativado");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> getClientesSelecionados() {
        return clientesSelecionados;
    }

    public void setClientesSelecionados(List<Cliente> clientesSelecionados) {
        this.clientesSelecionados = clientesSelecionados;
    }

}
