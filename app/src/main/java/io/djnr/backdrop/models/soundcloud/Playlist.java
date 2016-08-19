package io.djnr.backdrop.models.soundcloud;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("release_day")
    @Expose
    private Object releaseDay;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("genre")
    @Expose
    private Object genre;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("purchase_url")
    @Expose
    private Object purchaseUrl;
    @SerializedName("release_month")
    @Expose
    private Object releaseMonth;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("label_name")
    @Expose
    private Object labelName;
    @SerializedName("tag_list")
    @Expose
    private String tagList;
    @SerializedName("release_year")
    @Expose
    private Object releaseYear;
    @SerializedName("track_count")
    @Expose
    private Integer trackCount;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("tracks")
    @Expose
    private List<Track> tracks = new ArrayList<Track>();
    @SerializedName("playlist_type")
    @Expose
    private Object playlistType;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("downloadable")
    @Expose
    private Object downloadable;
    @SerializedName("sharing")
    @Expose
    private String sharing;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("release")
    @Expose
    private Object release;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("purchase_title")
    @Expose
    private Object purchaseTitle;
    @SerializedName("created_with")
    @Expose
    private CreatedWith createdWith;
    @SerializedName("artwork_url")
    @Expose
    private Object artworkUrl;
    @SerializedName("ean")
    @Expose
    private Object ean;
    @SerializedName("streamable")
    @Expose
    private Boolean streamable;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("embeddable_by")
    @Expose
    private String embeddableBy;
    @SerializedName("label_id")
    @Expose
    private Object labelId;

    /**
     *
     * @return
     * The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The releaseDay
     */
    public Object getReleaseDay() {
        return releaseDay;
    }

    /**
     *
     * @param releaseDay
     * The release_day
     */
    public void setReleaseDay(Object releaseDay) {
        this.releaseDay = releaseDay;
    }

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
     * The genre
     */
    public Object getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     * The genre
     */
    public void setGenre(Object genre) {
        this.genre = genre;
    }

    /**
     *
     * @return
     * The permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     *
     * @param permalink
     * The permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     *
     * @return
     * The purchaseUrl
     */
    public Object getPurchaseUrl() {
        return purchaseUrl;
    }

    /**
     *
     * @param purchaseUrl
     * The purchase_url
     */
    public void setPurchaseUrl(Object purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    /**
     *
     * @return
     * The releaseMonth
     */
    public Object getReleaseMonth() {
        return releaseMonth;
    }

    /**
     *
     * @param releaseMonth
     * The release_month
     */
    public void setReleaseMonth(Object releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    /**
     *
     * @return
     * The description
     */
    public Object getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(Object description) {
        this.description = description;
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
     * The labelName
     */
    public Object getLabelName() {
        return labelName;
    }

    /**
     *
     * @param labelName
     * The label_name
     */
    public void setLabelName(Object labelName) {
        this.labelName = labelName;
    }

    /**
     *
     * @return
     * The tagList
     */
    public String getTagList() {
        return tagList;
    }

    /**
     *
     * @param tagList
     * The tag_list
     */
    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    /**
     *
     * @return
     * The releaseYear
     */
    public Object getReleaseYear() {
        return releaseYear;
    }

    /**
     *
     * @param releaseYear
     * The release_year
     */
    public void setReleaseYear(Object releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     *
     * @return
     * The trackCount
     */
    public Integer getTrackCount() {
        return trackCount;
    }

    /**
     *
     * @param trackCount
     * The track_count
     */
    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    /**
     *
     * @return
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     *
     * @param lastModified
     * The last_modified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     *
     * @return
     * The license
     */
    public String getLicense() {
        return license;
    }

    /**
     *
     * @param license
     * The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     *
     * @return
     * The tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     *
     * @param tracks
     * The tracks
     */
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     *
     * @return
     * The playlistType
     */
    public Object getPlaylistType() {
        return playlistType;
    }

    /**
     *
     * @param playlistType
     * The playlist_type
     */
    public void setPlaylistType(Object playlistType) {
        this.playlistType = playlistType;
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
     * The downloadable
     */
    public Object getDownloadable() {
        return downloadable;
    }

    /**
     *
     * @param downloadable
     * The downloadable
     */
    public void setDownloadable(Object downloadable) {
        this.downloadable = downloadable;
    }

    /**
     *
     * @return
     * The sharing
     */
    public String getSharing() {
        return sharing;
    }

    /**
     *
     * @param sharing
     * The sharing
     */
    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The release
     */
    public Object getRelease() {
        return release;
    }

    /**
     *
     * @param release
     * The release
     */
    public void setRelease(Object release) {
        this.release = release;
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

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The type
     */
    public Object getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(Object type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The purchaseTitle
     */
    public Object getPurchaseTitle() {
        return purchaseTitle;
    }

    /**
     *
     * @param purchaseTitle
     * The purchase_title
     */
    public void setPurchaseTitle(Object purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    /**
     *
     * @return
     * The createdWith
     */
    public CreatedWith getCreatedWith() {
        return createdWith;
    }

    /**
     *
     * @param createdWith
     * The created_with
     */
    public void setCreatedWith(CreatedWith createdWith) {
        this.createdWith = createdWith;
    }

    /**
     *
     * @return
     * The artworkUrl
     */
    public Object getArtworkUrl() {
        return artworkUrl;
    }

    /**
     *
     * @param artworkUrl
     * The artwork_url
     */
    public void setArtworkUrl(Object artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    /**
     *
     * @return
     * The ean
     */
    public Object getEan() {
        return ean;
    }

    /**
     *
     * @param ean
     * The ean
     */
    public void setEan(Object ean) {
        this.ean = ean;
    }

    /**
     *
     * @return
     * The streamable
     */
    public Boolean getStreamable() {
        return streamable;
    }

    /**
     *
     * @param streamable
     * The streamable
     */
    public void setStreamable(Boolean streamable) {
        this.streamable = streamable;
    }

    /**
     *
     * @return
     * The user
     */
    public User_ getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User_ user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The embeddableBy
     */
    public String getEmbeddableBy() {
        return embeddableBy;
    }

    /**
     *
     * @param embeddableBy
     * The embeddable_by
     */
    public void setEmbeddableBy(String embeddableBy) {
        this.embeddableBy = embeddableBy;
    }

    /**
     *
     * @return
     * The labelId
     */
    public Object getLabelId() {
        return labelId;
    }

    /**
     *
     * @param labelId
     * The label_id
     */
    public void setLabelId(Object labelId) {
        this.labelId = labelId;
    }

}