package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-30T14:46:08")
@StaticMetamodel(Retiradas.class)
public class Retiradas_ { 

    public static volatile SingularAttribute<Retiradas, String> cliente;
    public static volatile SingularAttribute<Retiradas, Date> devolvido;
    public static volatile SingularAttribute<Retiradas, String> livro;
    public static volatile SingularAttribute<Retiradas, Date> entrega;
    public static volatile SingularAttribute<Retiradas, Long> id;
    public static volatile SingularAttribute<Retiradas, Boolean> livroDevolvido;
    public static volatile SingularAttribute<Retiradas, Date> retirada;

}