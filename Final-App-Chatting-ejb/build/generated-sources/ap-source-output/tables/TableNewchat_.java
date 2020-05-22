package tables;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-22T21:06:23")
@StaticMetamodel(TableNewchat.class)
public class TableNewchat_ { 

    public static volatile SingularAttribute<TableNewchat, Date> createdAt;
    public static volatile SingularAttribute<TableNewchat, String> name;
    public static volatile SingularAttribute<TableNewchat, Integer> id;
    public static volatile SingularAttribute<TableNewchat, String> message;

}