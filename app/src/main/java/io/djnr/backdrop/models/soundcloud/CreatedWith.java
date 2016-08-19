package io.djnr.backdrop.models.soundcloud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedWith {

    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("external_url")
    @Expose
    private String externalUrl;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kind")
    @Expose
    private String kind;

    /**
     *
     * @return
     * The permalinkUrl
     */
    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    /**
     *
     * @param permalinkUrl
     * The permalink_url
     */
    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The externalUrl
     */
    public String getExternalUrl() {
        return externalUrl;
    }

    /**
     *
     * @param externalUrl
     * The external_url
     */
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    /**
     *
     * @return
     * The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     * The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return
     * The creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     *
     * @param creator
     * The creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     * The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

}