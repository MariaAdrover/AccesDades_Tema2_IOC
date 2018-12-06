package ioc1;

import classesIoc.Article;
import java.util.List;

public class Ioc1 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sql_local001";
        String usuari = "root";
        String password = "root";
        String driver =  "org.gjt.mm.mysql.Driver";
        //String driver =  "com.mysql.jdbc.Driver";
        
        GestorEstructuraSGBD gestor = new GestorEstructuraSGBD(url, usuari, password, driver);
        
        //gestor.crearEstructura();
        gestor.eliminarEstructura();
        
        // Insertar filas
        /*
        gestor.aplicarSentencia("INSERT INTO article VALUES "
                + "(1, 'Descripcion del articulo');");
        gestor.aplicarSentencia("INSERT INTO article VALUES "
                + "(2, 'Otra descripcion del articulo');");
        
        gestor.aplicarSentencia("INSERT INTO article (descripcio) VALUES "
                + "('fideos grandes');");        
        gestor.aplicarSentencia("INSERT INTO article (descripcio) VALUES "
                + "('fideos medianos');");
        gestor.aplicarSentencia("INSERT INTO article (descripcio) VALUES "
                + "('muchos fideos');");
        gestor.aplicarSentencia("INSERT INTO article (descripcio) VALUES "
                + "('Super fideos grandes');");
       
        */
        // Modificar fila
        /*
        gestor.aplicarSentencia("UPDATE article "
                + "SET descripcio = 'Descripcion modificada'"
                + "WHERE id = 1;");        
        
        */
        // Borrar fila
        /*        
        gestor.aplicarSentencia("DELETE FROM article "
                + "WHERE id = 1");
        */
        
        // Obtenir un objecte Article a partir d'una id
        //Article article = gestor.getArticleById(4);
        
        // Obtenir una llista amb tots els objectes articles creats a partir de totes les files de la taula article
        //List<Article> listaCompletaArticulos = gestor.getAllArticles();
        
        // Obtenir una llista amb tots els objectes articles creats a partir de totes les files de la taula article que contenen la descripció passada com a argument
        //List<Article> listaArticulosSegunDescripcion = gestor.obtenirArticlesPerDescripcio("Fideos");
        
    }
    
}
