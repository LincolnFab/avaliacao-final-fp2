package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Banco;
import model.Conta;
import model.Funcionario;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-06-24T08:19:36")
@StaticMetamodel(Agencia.class)
public class Agencia_ { 

    public static volatile CollectionAttribute<Agencia, Conta> contaCollection;
    public static volatile SingularAttribute<Agencia, Banco> bancoId;
    public static volatile CollectionAttribute<Agencia, Funcionario> funcionarioCollection;
    public static volatile SingularAttribute<Agencia, String> nome;
    public static volatile SingularAttribute<Agencia, Integer> id;

}