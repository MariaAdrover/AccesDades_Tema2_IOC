package classesIoc;

/**
 * Aquesta classe modela un client d'una empresa
 * @author josep
 */
public class Client {
    private Integer id=null; //   ok autoincrement
    private String nif;//
    private String nom; //   
    private Zona zona=null;//
    private Sector sector=null;//

    /**
     * Costructor per defecte
     */
    public Client() {
    }

    /**
     * Contructor que inicialitza el nif i el nom de l client
     * @param nif
     * @param nom
     */
    public Client(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
    }
    
    
    /**
     * Obté el codi de client
     * @return el codi de client
     */
    public Integer getId() {
        return id;
    }

    /**
     * Assigna el codi de client
     * @param id codi de client a assignar
     */
    protected void setId(Integer id) {
        this.id = id;
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
     * Obte la zona assignada al client
     * @return the zona
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * Assigna el client a la zona passada per paràmetre
     * @param zona la zona on assignar el client
     */
    public void setZona(Zona zona) {
        if(this.zona!=null){
            this.zona.getClients().remove(this);
        }
        this.zona = zona;
        this.zona.getClients().add(this);
    }

    /**
     * Obté el sector de producció del client
     * @return el sector productiu
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * Assigna el sector productiu del client
     * @param sector el serctor productiu a assignar
     */
    public void setSector(Sector sector) {
        this.sector = sector;
    }

     /**
     * Indica si l'objecte on es fa la crida i el que es passa per paràmetre 
     * són iguals
     * @param object a comparar
     * @return cert si són iguals. Fals en cas contrari.
     */
     @Override
     public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if(this.id==null && other.id!=null){
            return false;
        }
        if (!this.id.equals(other.id)){
            return false;
        }
        return true;
    }
}
