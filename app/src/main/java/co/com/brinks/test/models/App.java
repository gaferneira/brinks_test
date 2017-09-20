package co.com.brinks.test.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class App implements Serializable  {

    @SerializedName("im:name")
    private Item name;
    @SerializedName("summary")
    private Item summary;
    @SerializedName("im:price")
    private Item price;
    @SerializedName("im:image")
    private Item[] images;
    @SerializedName("im:contentType")
    private Item contentType;
    @SerializedName("rights")
    private Item rights;
    @SerializedName("title")
    private Item title;
    @SerializedName("link")
    private Item link;
    @SerializedName("id")
    private Item id;
    @SerializedName("im:artist")
    private Item artist;
    @SerializedName("category")
    private Item category;
    @SerializedName("im:releaseDate")
    private Item releaseDate;

    public Item getName() {
        return name;
    }

    public void setName(Item name) {
        this.name = name;
    }

    public Item getSummary() {
        return summary;
    }

    public void setSummary(Item summary) {
        this.summary = summary;
    }

    public Item getPrice() {
        return price;
    }

    public void setPrice(Item price) {
        this.price = price;
    }

    public Item getContentType() {
        return contentType;
    }

    public void setContentType(Item contentType) {
        this.contentType = contentType;
    }

    public Item getRights() {
        return rights;
    }

    public void setRights(Item rights) {
        this.rights = rights;
    }

    public Item getTitle() {
        return title;
    }

    public void setTitle(Item title) {
        this.title = title;
    }

    public Item getLink() {
        return link;
    }

    public void setLink(Item link) {
        this.link = link;
    }

    public Item getId() {
        return id;
    }

    public void setId(Item id) {
        this.id = id;
    }

    public Item getArtist() {
        return artist;
    }

    public void setArtist(Item artist) {
        this.artist = artist;
    }

    public Item getCategory() {
        return category;
    }

    public void setCategory(Item category) {
        this.category = category;
    }

    public Item getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Item releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Item[] getImages() {
        return images;
    }

    public void setImages(Item[] images) {
        this.images = images;
    }

}
