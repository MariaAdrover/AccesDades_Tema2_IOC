package classesIoc;

import classesIoc.Article;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe sector modela els sectors productius de les empreses.
 * @author josep
 */
public class Sector implements Serializable {
    private String id;//
    private String descripcio;//
    private List<Article> articles=new ArrayList<Article>();

    /**
     * Costructor per defecte
     */
    protected Sector() {
    }

     /**
     * Constructor que inicialitza l'identificador 
     * @param id
     */
    public Sector(String id) {
        this.id = id;
    }
    
    /**
     * Constructor que inicialitza l'identificador i la descripció
     * @param id
     * @param descripcio
     */
    public Sector(String id, String descripcio) {
        this.id = id;
        this.descripcio = descripcio;
    }
    
    /**
     * Indica si l'objecte on es fa la crida i el que es passa per paràmetre 
     * són iguals
     * @param object a comparar
     * @return cert si són iguals. Fals en cas contrari.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   /**
     * Cadena de caràcters representativa de l'objecte on es fa la crida.
     * @return Cadena de caràcters representativa de l'objecte 
     */
    @Override
    public String toString() {
        return "Sector[ id=" + id + " ]";
    }

    /**
     * Obté el valor identificador de la zona
     * @return valor identificador
     */
    public String getId() {
        return id;
    }

    /**
     * Assigna una cadena identificativa a la zona
     * @param id valor a assignar
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * Obté la descripció de la zona
     * @return the descripcio
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Assigna una descripció a la zona
     * @param descripcio la descripció a assignar
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Obté la llista d'articles associats al sector
     * @return the articles
     */
    public List<Article> getArticles() {
        return articles;
    }

}
