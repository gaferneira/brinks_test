package co.com.brinks.test.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attribute implements Serializable {

    @SerializedName("im:id")
    private String id;

    @SerializedName("term")
    private String term;

    @SerializedName("scheme")
    private String scheme;

    @SerializedName("label")
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
