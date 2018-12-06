package ioc1;

import classesIoc.Article;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GestorEstructuraSGBD {

    private Connection con;
    private String url;
    private String usuari;
    private String password;
    private String driver;

    public GestorEstructuraSGBD(String url, String usuari, String password, String driver) {
        this.url = url;
        this.usuari = usuari;
        this.password = password;
        this.driver = driver;
        this.con = null;
    }

    public void abrirConexion() {
        //Driver driver = null;
        String url = this.url;
        String usuari = this.usuari;
        String password = this.password;
        try {
            // Carreguem el controlador en memòria
            Class.forName(this.driver);

        } catch (ClassNotFoundException ex) {
            System.out.println("No s’ha trobat el controlador JDBC ("
                    + ex.getMessage() + ")");

            return;
        }

        try {
            //Obtenim una connexió des de DriverManager
            this.con = DriverManager.getConnection(url, usuari, password);
            System.out.println("Conexión establecida");
            //con.close();

        } catch (SQLException ex) {
            System.out.println("No se ha podido crear la conexión");
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            System.out.println("No se ha podido cerrar la conexión");
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void crearEstructura() {
        this.crearTaulaArticle();
        this.crearTaulaSector();
        this.crearTaulaArticlePerSector();
        this.crearTaulaComercial();
        this.crearTaulaZona();
        this.crearTaulaClient();
    }

    public void eliminarEstructura() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL = null;

        try {
            statement = this.con.createStatement();

            sentenciaSQL = "DROP TABLE IF EXISTS Client;";
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Tabla Client borrada");

            sentenciaSQL = "DROP TABLE IF EXISTS Zona;";
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Tabla Zona borrada");

            sentenciaSQL = "DROP TABLE IF EXISTS Comercial;";
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Tabla Comercial borrada");

            sentenciaSQL = "DROP TABLE IF EXISTS ArticlePerSector;";
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Tabla ArticlePerSector borrada");

            sentenciaSQL = "DROP TABLE IF EXISTS Sector;";
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Tabla Sector borrada");

            sentenciaSQL = "DROP TABLE IF EXISTS Article;";
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Tabla Article borrada");

        } catch (SQLException ex) {
            System.out.println("No se han podido borrar las tablas");
            System.out.println("Error " + ex.getMessage());

        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    private void crearTaulaArticle() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL;
        try {
            statement = (Statement) con.createStatement();

            sentenciaSQL = "CREATE TABLE IF NOT EXISTS article("
                    + " id INTEGER NOT NULL AUTO_INCREMENT,"
                    + " descripcio VARCHAR(100),"
                    + " CONSTRAINT article_pk PRIMARY KEY (id));";

            statement.executeUpdate(sentenciaSQL);
            System.out.println("Update crearTaulaArticle OK");
        } catch (SQLException ex) {
            System.out.println("No se ha podido crear la tabla Article");
            System.out.println("Error " + ex.getMessage());

        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    private void crearTaulaSector() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL;

        try {
            statement = (Statement) con.createStatement();

            sentenciaSQL = "CREATE TABLE IF NOT EXISTS sector("
                    + " id VARCHAR(10) NOT NULL,"
                    + " descripcio VARCHAR(100),"
                    + " CONSTRAINT sector_pk PRIMARY KEY (id));";

            statement.executeUpdate(sentenciaSQL);
            System.out.println("Update crearTaulaSector OK");
        } catch (SQLException ex) {
            System.out.println("No se ha podido crear la tabla Sector");
            System.out.println("Error " + ex.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    private void crearTaulaArticlePerSector() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL;
        try {
            statement = (Statement) con.createStatement();

            sentenciaSQL = "CREATE TABLE IF NOT EXISTS ArticlePerSector("
                    + " article_id INTEGER NOT NULL,"
                    + " sector_id  VARCHAR(10) NOT NULL ,"
                    + " CONSTRAINT article_pk PRIMARY KEY (article_id, sector_id),"
                    + " CONSTRAINT artXsector_article_fk FOREIGN KEY (article_id)"
                    + " REFERENCES article (id),"
                    + " CONSTRAINT artXsector_sector_fk FOREIGN KEY (sector_id)"
                    + " REFERENCES sector (id));";

            statement.executeUpdate(sentenciaSQL);
            System.out.println("Update crearTaulaArticle OK");
        } catch (SQLException ex) {
            System.out.println("No se ha podido crear la tabla Article");
            System.out.println("Error " + ex.getMessage());

        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    private void crearTaulaComercial() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL;
        try {
            statement = (Statement) con.createStatement();
            // Añadir id como pk? --> id INTEGER NOT NULL AUTO_INCREMENT,
            sentenciaSQL = "CREATE TABLE IF NOT EXISTS comercial("
                    + " nif VARCHAR(15) NOT NULL,"
                    + " nom VARCHAR(255),"
                    + " CONSTRAINT comercial_pk PRIMARY KEY (nif));";

            statement.executeUpdate(sentenciaSQL);
            System.out.println("Update crearTaulaComercial OK");
        } catch (SQLException ex) {
            System.out.println("No se ha podido crear la tabla Comercial");
            System.out.println("Error " + ex.getMessage());

        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    private void crearTaulaZona() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL;
        try {
            statement = (Statement) con.createStatement();

            sentenciaSQL = "CREATE TABLE IF NOT EXISTS zona("
                    + " id VARCHAR(10) NOT NULL,"
                    + " descripcio VARCHAR(50),"
                    + " comercial_nif VARCHAR(15),"
                    + " CONSTRAINT zona_pk PRIMARY KEY (id),"
                    + " CONSTRAINT zona_comercial_fk FOREIGN KEY (comercial_nif) "
                    + " REFERENCES comercial (nif));";

            statement.executeUpdate(sentenciaSQL);
            System.out.println("Update crearTaulaZona OK");

        } catch (SQLException ex) {
            System.out.println("No se ha podido crear la tabla Zona");
            System.out.println("Error " + ex.getMessage());

        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) { // Repasar por qué salta la excepción
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    private void crearTaulaClient() {
        this.abrirConexion();
        Statement statement = null;
        String sentenciaSQL;

        try {
            statement = (Statement) con.createStatement();
            sentenciaSQL = "CREATE TABLE IF NOT EXISTS client("
                    + " id INTEGER NOT NULL AUTO_INCREMENT,"
                    + " nif VARCHAR(15) NOT NULL,"
                    + " nom VARCHAR(255),"
                    + " sector_id VARCHAR(10) NOT NULL,"
                    + " zona_id VARCHAR(10) NOT NULL ,"
                    + " CONSTRAINT client_pk PRIMARY KEY (id),"
                    + " CONSTRAINT client_zona_fk FOREIGN KEY (zona_id) "
                    + " REFERENCES zona (id),"
                    + " CONSTRAINT client_sector_fk FOREIGN KEY (sector_id) "
                    + " REFERENCES sector (id));";

            statement.executeUpdate(sentenciaSQL);
            System.out.println("Update crearTaulaClient OK");
        } catch (SQLException ex) {
            System.out.println("Error al crear la tabla Client");
            System.out.println("Error " + ex.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        this.cerrarConexion();
    }

    public void aplicarSentencia(String sentenciaSQL) {
        this.abrirConexion();
        
        Statement statement = null;

        try {
            statement = con.createStatement();
            statement.executeUpdate(sentenciaSQL);
            System.out.println("Método aplicarSentenciaDML(" + sentenciaSQL + ") OK");

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
            }
        }
        
        this.cerrarConexion();
    }

    public Article getArticleById(int id) {
        this.abrirConexion();
        
        Statement statement = null;
        String sentenciaSQL = null;
        ResultSet rs = null;
        Article article = null;
        
        System.out.println("");
        System.out.println("Artículo con el id " + id + ":");

        try {
            statement = con.createStatement();
            sentenciaSQL = "SELECT * "
                    + " FROM article "
                    + "WHERE id=" + id + ";";
            rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                // Creamos un objeto Article a partir de los datos almacenados en la tabla articles referidos al id pasado por parámetro
                article = new Article(rs.getInt("id"), rs.getString("descripcio"));
                // Imprimo los datos por consola para comprobar que se ha creado el objeto
                System.out.println("ID: " + article.getId());
                System.out.println("Descripció: " + article.getDescripcio());
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
            }
        }
        
        this.cerrarConexion();
        
        return article;
    }
    
    public List<Article> getAllArticles() {
        this.abrirConexion();
        
        List<Article> listaArticulos = new ArrayList();
        Statement statement = null;
        String sentenciaSQL = null;
        ResultSet rs = null;
        Article article = null;
        
        System.out.println("");
        System.out.println("Lista de artículos:");
        try {
            statement = con.createStatement();
            sentenciaSQL = "SELECT * "
                    + " FROM article;";
            rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                // Creamos un objeto Article a partir cada articulo
                article = new Article(rs.getInt("id"), rs.getString("descripcio"));
                listaArticulos.add(article);
                // Imprimo los datos por consola para comprobar que se ha creado el objeto
                System.out.println("ID: " + article.getId());
                System.out.println("Descripció: " + article.getDescripcio());
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
            }
        }
        
        System.out.println("Hay " + listaArticulos.size() + " articulos que cumplen la condición.");
        
        this.cerrarConexion();
        
        return listaArticulos;
    }
    
    public List<Article> obtenirArticlesPerDescripcio(String descripcio) {
        this.abrirConexion();
        
        List<Article> listaArticulos = new ArrayList();
        Statement statement = null;
        String sentenciaSQL = null;
        ResultSet rs = null;
        Article article = null;
        
        System.out.println("");
        System.out.println("Lista de artículos que contienen " + descripcio + " en la descriplción:");
        try {
            statement = con.createStatement();
            sentenciaSQL = "SELECT * "
                    + " FROM article"
                    + " WHERE descripcio LIKE '" + descripcio +"%';";
            rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                // Creamos un objeto Article a partir cada articulo
                article = new Article(rs.getInt("id"), rs.getString("descripcio"));
                listaArticulos.add(article);
                // Imprimo los datos por consola para comprobar que se ha creado el objeto
                System.out.println("ID: " + article.getId());
                System.out.println("Descripció: " + article.getDescripcio());
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
            }
        }
        
        System.out.println("Hay " + listaArticulos.size() + " articulos que cumplen la condición.");
        
        this.cerrarConexion();
        
        return listaArticulos;        
    }

}
