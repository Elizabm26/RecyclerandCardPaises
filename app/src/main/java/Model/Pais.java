package Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Pais {
    private  String Titulo_Pais;
    private String Capital;
    private  String Prefijo;
    private String CodPais;
    private  String UrlBandera;

    public String getTitulo_Pais() {
        return Titulo_Pais;
    }

    public void setTitulo_Pais(String titulo_Pais) {
        Titulo_Pais = titulo_Pais;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getPrefijo() {
        return Prefijo;
    }

    public void setPrefijo(String prefijo) {
        Prefijo = prefijo;
    }

    public String getCodPais() {
        return CodPais;
    }

    public void setCodPais(String codPais) {
        CodPais = codPais;
    }

    public String getUrlBandera() {
        return UrlBandera;
    }

    public void setUrlBandera(String urlBandera) {
        UrlBandera = urlBandera;
    }

    public Pais(JSONObject a) throws JSONException {
        Titulo_Pais = a.getString("Name").toString();
        JSONObject countryCode = a.getJSONObject("CountryCodes");
        CodPais = countryCode.getString("iso2");
        UrlBandera = "http://www.geognos.com/api/en/countries/flag/"+CodPais+".png";
        if (!a.isNull("Capital")){
            JSONObject countryCapital = a.getJSONObject("Capital");
            Capital = countryCapital.getString("Name").toString();
        } else Capital = "Este pais no tiene una capital";
        Prefijo = a.getString("TelPref").toString();
    }

}
