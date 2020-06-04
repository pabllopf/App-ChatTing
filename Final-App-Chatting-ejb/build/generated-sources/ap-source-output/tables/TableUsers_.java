package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.TableUserChat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T19:04:39")
@StaticMetamodel(TableUsers.class)
public class TableUsers_ { 

    public static volatile SingularAttribute<TableUsers, String> password;
    public static volatile SingularAttribute<TableUsers, TableUserChat> userChat;
    public static volatile SingularAttribute<TableUsers, String> chat;
    public static volatile SingularAttribute<TableUsers, String> name;
    public static volatile SingularAttribute<TableUsers, Integer> id;

}