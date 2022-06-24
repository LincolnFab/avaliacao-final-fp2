package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Conta;
import model.OperacaoPK;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-06-24T08:19:36")
@StaticMetamodel(Operacao.class)
public class Operacao_ { 

    public static volatile SingularAttribute<Operacao, Integer> agenciaIdDestino;
    public static volatile SingularAttribute<Operacao, Date> data;
    public static volatile SingularAttribute<Operacao, OperacaoPK> operacaoPK;
    public static volatile SingularAttribute<Operacao, Double> valor;
    public static volatile SingularAttribute<Operacao, Conta> conta;
    public static volatile SingularAttribute<Operacao, String> tipoOperacao;
    public static volatile SingularAttribute<Operacao, Integer> contaIdDestino;
    public static volatile SingularAttribute<Operacao, Boolean> status;

}