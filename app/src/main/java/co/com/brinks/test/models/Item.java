package co.com.brinks.test.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Item implements Serializable {

    @SerializedName("label")
    private String label;

    @SerializedName("attributes")
    private Attribute attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return label;
    }
}
