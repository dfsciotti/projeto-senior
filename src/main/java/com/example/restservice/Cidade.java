package com.example.restservice;


public class Cidade {

	private int ibge_id;
    private String uf;
    private String name;
    private boolean capital;
    private double lon;
    private double lat;
    private String no_accents;
    private String alternative_names;
    private String microregion;
    private String mesoregion;

    public Cidade(){ }

    public int getIbgeId(){
        return ibge_id;
    }
    public void setIbgeId(int ibge_id){
        this.ibge_id = ibge_id;
    }

    public String getUf(){
        return uf;
    }
    public void setUf(String uf){
        this.uf = uf;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean getCapital(){
        return capital;
    }
    public void setCapital(boolean capital){
        this.capital = capital;
    }

    public double getLon(){
        return lon;
    }
    public void setLon(double lon){
        this.lon = lon;
    }

    public double getLat(){
        return lat;
    }
    public void setLat(double lat){
        this.lat = lat;
    }

    public String getNoAccents(){
        return no_accents;
    }
    public void setNoAccents(String noaccents){
        this.no_accents = noaccents;
    }

    public String getAlternativeNames(){
        return alternative_names;
    }
    public void setAlternativeNames(String alternative_names){
        this.alternative_names= alternative_names;
    }

    public String getMicroRegion(){
        return microregion;
    }
    public void setMicroRegion(String microregion){
        this.microregion = microregion;
    }

    public String getMesoRegion(){
        return mesoregion;
    }
    public void setMesoRegion(String mesoregion){
        this.mesoregion = mesoregion;
    }
}
