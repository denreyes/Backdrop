package io.djnr.backdrop.models.soundcloud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("commentable")
    @Expose
    private Boolean commentable;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("original_content_size")
    @Expose
    private Integer originalContentSize;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("sharing")
    @Expose
    private String sharing;
    @SerializedName("tag_list")
    @Expose
    private String tagList;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("streamable")
    @Expose
    private Boolean streamable;
    @SerializedName("embeddable_by")
    @Expose
    private String embeddableBy;
    @SerializedName("downloadable")
    @Expose
    private Boolean downloadable;
    @SerializedName("purchase_url")
    @Expose
    private Object purchaseUrl;
    @SerializedName("label_id")
    @Expose
    private Object labelId;
    @SerializedName("purchase_title")
    @Expose
    private Object purchaseTitle;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("label_name")
    @Expose
    private String labelName;
    @SerializedName("release")
    @Expose
    private String release;
    @SerializedName("track_type")
    @Expose
    private String trackType;
    @SerializedName("key_signature")
    @Expose
    private String keySignature;
    @SerializedName("isrc")
    @Expose
    private String isrc;
    @SerializedName("video_url")
    @Expose
    private Object videoUrl;
    @SerializedName("bpm")
    @Expose
    private Object bpm;
    @SerializedName("release_year")
    @Expose
    private Object releaseYear;
    @SerializedName("release_month")
    @Expose
    private Object releaseMonth;
    @SerializedName("release_day")
    @Expose
    private Object releaseDay;
    @SerializedName("original_format")
    @Expose
    private String originalFormat;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("artwork_url")
    @Expose
    private String artworkUrl;
    @SerializedName("waveform_url")
    @Expose
    private String waveformUrl;
    @SerializedName("stream_url")
    @Expose
    private String streamUrl;
    @SerializedName("playback_count")
    @Expose
    private Integer playbackCount;
    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;
    @SerializedName("favoritings_count")
    @Expose
    private Integer favoritingsCount;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("attachments_uri")
    @Expose
    private String attachmentsUri;

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
     * The commentable
     */
    public Boolean getCommentable() {
        return commentable;
    }

    /**
     *
     * @param commentable
     * The commentable
     */
    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The originalContentSize
     */
    public Integer getOriginalContentSize() {
        return originalContentSize;
    }

    /**
     *
     * @param originalContentSize
     * The original_content_size
     */
    public void setOriginalContentSize(Integer originalContentSize) {
        this.originalContentSize = originalContentSize;
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
     * The downloadable
     */
    public Boolean getDownloadable() {
        return downloadable;
    }

    /**
     *
     * @param downloadable
     * The downloadable
     */
    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
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
     * The genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     * The genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The labelName
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     *
     * @param labelName
     * The label_name
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     *
     * @return
     * The release
     */
    public String getRelease() {
        return release;
    }

    /**
     *
     * @param release
     * The release
     */
    public void setRelease(String release) {
        this.release = release;
    }

    /**
     *
     * @return
     * The trackType
     */
    public String getTrackType() {
        return trackType;
    }

    /**
     *
     * @param trackType
     * The track_type
     */
    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    /**
     *
     * @return
     * The keySignature
     */
    public String getKeySignature() {
        return keySignature;
    }

    /**
     *
     * @param keySignature
     * The key_signature
     */
    public void setKeySignature(String keySignature) {
        this.keySignature = keySignature;
    }

    /**
     *
     * @return
     * The isrc
     */
    public String getIsrc() {
        return isrc;
    }

    /**
     *
     * @param isrc
     * The isrc
     */
    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    /**
     *
     * @return
     * The videoUrl
     */
    public Object getVideoUrl() {
        return videoUrl;
    }

    /**
     *
     * @param videoUrl
     * The video_url
     */
    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     *
     * @return
     * The bpm
     */
    public Object getBpm() {
        return bpm;
    }

    /**
     *
     * @param bpm
     * The bpm
     */
    public void setBpm(Object bpm) {
        this.bpm = bpm;
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
     * The originalFormat
     */
    public String getOriginalFormat() {
        return originalFormat;
    }

    /**
     *
     * @param originalFormat
     * The original_format
     */
    public void setOriginalFormat(String originalFormat) {
        this.originalFormat = originalFormat;
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
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
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
     * The artworkUrl
     */
    public String getArtworkUrl() {
        return artworkUrl;
    }

    /**
     *
     * @param artworkUrl
     * The artwork_url
     */
    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    /**
     *
     * @return
     * The waveformUrl
     */
    public String getWaveformUrl() {
        return waveformUrl;
    }

    /**
     *
     * @param waveformUrl
     * The waveform_url
     */
    public void setWaveformUrl(String waveformUrl) {
        this.waveformUrl = waveformUrl;
    }

    /**
     *
     * @return
     * The streamUrl
     */
    public String getStreamUrl() {
        return streamUrl;
    }

    /**
     *
     * @param streamUrl
     * The stream_url
     */
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    /**
     *
     * @return
     * The playbackCount
     */
    public Integer getPlaybackCount() {
        return playbackCount;
    }

    /**
     *
     * @param playbackCount
     * The playback_count
     */
    public void setPlaybackCount(Integer playbackCount) {
        this.playbackCount = playbackCount;
    }

    /**
     *
     * @return
     * The downloadCount
     */
    public Integer getDownloadCount() {
        return downloadCount;
    }

    /**
     *
     * @param downloadCount
     * The download_count
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     *
     * @return
     * The favoritingsCount
     */
    public Integer getFavoritingsCount() {
        return favoritingsCount;
    }

    /**
     *
     * @param favoritingsCount
     * The favoritings_count
     */
    public void setFavoritingsCount(Integer favoritingsCount) {
        this.favoritingsCount = favoritingsCount;
    }

    /**
     *
     * @return
     * The commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     *
     * @param commentCount
     * The comment_count
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     *
     * @return
     * The attachmentsUri
     */
    public String getAttachmentsUri() {
        return attachmentsUri;
    }

    /**
     *
     * @param attachmentsUri
     * The attachments_uri
     */
    public void setAttachmentsUri(String attachmentsUri) {
        this.attachmentsUri = attachmentsUri;
    }

}