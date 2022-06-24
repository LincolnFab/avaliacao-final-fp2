package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Agencia;
import model.Cliente;
import model.ContaPK;
import model.Operacao;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-06-24T08:19:36")
@StaticMetamodel(Conta.class)
public class Conta_ { 

    public static volatile SingularAttribute<Conta, String> senha;
    public static volatile SingularAttribute<Conta, Cliente> clienteCpf;
    public static volatile CollectionAttribute<Conta, Operacao> operacaoCollection;
    public static volatile SingularAttribute<Conta, Double> saldo;
    public static volatile SingularAttribute<Conta, Double> limite;
    public static volatile SingularAttribute<Conta, ContaPK> contaPK;
    public static volatile SingularAttribute<Conta, String> tipoConta;
    public static volatile SingularAttribute<Conta, Agencia> agencia;
    public static volatile SingularAttribute<Conta, Date> dataAbertura;
    public static volatile SingularAttribute<Conta, Boolean> status;

}