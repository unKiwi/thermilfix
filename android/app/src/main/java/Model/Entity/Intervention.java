package Model.Entity;

public class Intervention {

    private int id;
    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String marqueChaudiere;
    private String modelChaudiere;
    private String dateDeMiseEnService;
    private String dateItervention;
    private String numeroDeSerie;
    private String descriptionIntervention;
    private String tempsPasse;

    public Intervention(int id, String nomClient, String prenomClient, String adresseClient, String marqueChaudiere, String modelChaudiere, String dateDeMiseEnService, String dateItervention, String numeroDeSerie, String descriptionIntervention, String tempsPasse) {
        this.id = id;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.marqueChaudiere = marqueChaudiere;
        this.modelChaudiere = modelChaudiere;
        this.dateDeMiseEnService = dateDeMiseEnService;
        this.dateItervention = dateItervention;
        this.numeroDeSerie = numeroDeSerie;
        this.descriptionIntervention = descriptionIntervention;
        this.tempsPasse = tempsPasse;
    }

    public Intervention(int id, String nomClient) {
        this.id = id;
        this.nomClient = nomClient;
        this.prenomClient = "";
        this.adresseClient = "";
        this.marqueChaudiere = "";
        this.modelChaudiere = "";
        this.dateDeMiseEnService = "";
        this.dateItervention = "";
        this.numeroDeSerie = "";
        this.descriptionIntervention = "";
        this.tempsPasse = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getMarqueChaudiere() {
        return marqueChaudiere;
    }

    public void setMarqueChaudiere(String marqueChaudiere) {
        this.marqueChaudiere = marqueChaudiere;
    }

    public String getModelChaudiere() {
        return modelChaudiere;
    }

    public void setModelChaudiere(String modelChaudiere) {
        this.modelChaudiere = modelChaudiere;
    }

    public String getDateDeMiseEnService() {
        return dateDeMiseEnService;
    }

    public void setDateDeMiseEnService(String dateDeMiseEnService) {
        this.dateDeMiseEnService = dateDeMiseEnService;
    }

    public String getDateItervention() {
        return dateItervention;
    }

    public void setDateItervention(String dateItervention) {
        this.dateItervention = dateItervention;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getDescriptionIntervention() {
        return descriptionIntervention;
    }

    public void setDescriptionIntervention(String descriptionIntervention) {
        this.descriptionIntervention = descriptionIntervention;
    }

    public String getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(String tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public void log() {
        System.out.println("nomClient");
        System.out.println(getNomClient());
        System.out.println("prenomClient");
        System.out.println(getPrenomClient());
        System.out.println("adresseClient");
        System.out.println(getAdresseClient());
        System.out.println("marqueChaudiere");
        System.out.println(getMarqueChaudiere());
        System.out.println("modelChaudiere");
        System.out.println(getModelChaudiere());
        System.out.println("numeroDeSerie");
        System.out.println(getNumeroDeSerie());
        System.out.println("dateItervention");
        System.out.println(getDateItervention());
        System.out.println("dateDeMiseEnService");
        System.out.println(getDateDeMiseEnService());
        System.out.println("tempsPasse");
        System.out.println(getTempsPasse());
        System.out.println("descriptionIntervention");
        System.out.println(getDescriptionIntervention());
    }
}