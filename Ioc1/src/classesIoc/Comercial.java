package classesIoc;

/**
 * Amb aquesta classe es modelen els comercials
 * @author josep
 */
public class Comercial {
    private String nif;//
    private String nom;//
    private Zona zona=null;//NO esta en zon la 

    /**
     * Costructor per defecte
     */
    public Comercial() {
    }

    /**
     * Contructor que inicialitza el nif i el nom del comercial
     * @param nif
     * @param nom
     */
    public Comercial(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
    }
    
 
    /**
     * Obté el nif del client
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * Assigna el nif al client
     * @param nif a assignar 
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Obté el nom del client.
     * @return nom del client
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigna el nom al client
     * @param nom el nom del client a assignar.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

     /**
     * Obté la zona que se li ha assignat
     * @return la zona
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * S'assigna una zona a un comercial. Cada comercial nomé pot tenir una 
     * única zona assignada.
     * @param zona la zona a assignar
     */
    public void setZona(Zona zona) {
        if(this.zona!=null){
            this.zona.setComercial(null);
        }
        this.zona = zona;
        if(!zona.getComercial().equals(this)){
            zona.setComercial(this);
        }
    }
    
    /**
     * Indica si l'objecte on es fa la crida i el que es passa per paràmetre 
     * són iguals
     * @param object a comparar
     * @return cert si són iguals. Fals en cas contrari.
     */
    @Override
    public boolean equals(Object object) {
        if (object==null || !(object instanceof Comercial)) {
            return false;
        }
        Comercial other = (Comercial) object;
        if (this.nif==null && other.nif!=null || !this.nif.equals(other.nif)) {
            return false;
        }
        return true;
    }
}
