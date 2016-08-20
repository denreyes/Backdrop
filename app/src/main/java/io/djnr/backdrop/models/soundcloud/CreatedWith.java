package io.djnr.backdrop.models.soundcloud;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedWith implements Parcelable {

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


    protected CreatedWith(Parcel in) {
        permalinkUrl = in.readString();
        name = in.readString();
        externalUrl = in.readString();
        uri = in.readString();
        creator = in.readString();
        id = in.readByte() == 0x00 ? null : in.readInt();
        kind = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(permalinkUrl);
        dest.writeString(name);
        dest.writeString(externalUrl);
        dest.writeString(uri);
        dest.writeString(creator);
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(kind);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CreatedWith> CREATOR = new Parcelable.Creator<CreatedWith>() {
        @Override
        public CreatedWith createFromParcel(Parcel in) {
            return new CreatedWith(in);
        }

        @Override
        public CreatedWith[] newArray(int size) {
            return new CreatedWith[size];
        }
    };
}