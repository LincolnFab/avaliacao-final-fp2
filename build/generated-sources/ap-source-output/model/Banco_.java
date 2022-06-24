package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Agencia;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-06-24T08:19:36")
@StaticMetamodel(Banco.class)
public class Banco_ { 

    public static volatile CollectionAttribute<Banco, Agencia> agenciaCollection;
    public static volatile SingularAttribute<Banco, String> nome;
    public static volatile SingularAttribute<Banco, Integer> id;

}