module db {
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires common;

    exports db;
}