package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Agencia;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-16T18:25:08")
@StaticMetamodel(Banco.class)
public class Banco_ { 

    public static volatile CollectionAttribute<Banco, Agencia> agenciaCollection;
    public static volatile SingularAttribute<Banco, String> nome;
    public static volatile SingularAttribute<Banco, Integer> id;

}