package fca.unam.mx.services;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class JdbiService {

    private final Jdbi jdbi;


    JdbiService(DataSource defaultDataSource){
        this.jdbi = Jdbi.create(defaultDataSource);
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

    public Jdbi getInstance(){
        return this.jdbi;
    }
}
