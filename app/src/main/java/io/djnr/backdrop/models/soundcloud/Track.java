package io.djnr.backdrop.models.soundcloud;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track implements Parcelable {

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


    protected Track(Parcel in) {
        kind = in.readString();
        id = in.readByte() == 0x00 ? null : in.readInt();
        createdAt = in.readString();
        userId = in.readByte() == 0x00 ? null : in.readInt();
        duration = in.readByte() == 0x00 ? null : in.readInt();
        byte commentableVal = in.readByte();
        commentable = commentableVal == 0x02 ? null : commentableVal != 0x00;
        state = in.readString();
        originalContentSize = in.readByte() == 0x00 ? null : in.readInt();
        lastModified = in.readString();
        sharing = in.readString();
        tagList = in.readString();
        permalink = in.readString();
        byte streamableVal = in.readByte();
        streamable = streamableVal == 0x02 ? null : streamableVal != 0x00;
        embeddableBy = in.readString();
        byte downloadableVal = in.readByte();
        downloadable = downloadableVal == 0x02 ? null : downloadableVal != 0x00;
        purchaseUrl = (Object) in.readValue(Object.class.getClassLoader());
        labelId = (Object) in.readValue(Object.class.getClassLoader());
        purchaseTitle = (Object) in.readValue(Object.class.getClassLoader());
        genre = in.readString();
        title = in.readString();
        description = in.readString();
        labelName = in.readString();
        release = in.readString();
        trackType = in.readString();
        keySignature = in.readString();
        isrc = in.readString();
        videoUrl = (Object) in.readValue(Object.class.getClassLoader());
        bpm = (Object) in.readValue(Object.class.getClassLoader());
        releaseYear = (Object) in.readValue(Object.class.getClassLoader());
        releaseMonth = (Object) in.readValue(Object.class.getClassLoader());
        releaseDay = (Object) in.readValue(Object.class.getClassLoader());
        originalFormat = in.readString();
        license = in.readString();
        uri = in.readString();
        user = (User) in.readValue(User.class.getClassLoader());
        permalinkUrl = in.readString();
        artworkUrl = in.readString();
        waveformUrl = in.readString();
        streamUrl = in.readString();
        playbackCount = in.readByte() == 0x00 ? null : in.readInt();
        downloadCount = in.readByte() == 0x00 ? null : in.readInt();
        favoritingsCount = in.readByte() == 0x00 ? null : in.readInt();
        commentCount = in.readByte() == 0x00 ? null : in.readInt();
        attachmentsUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(createdAt);
        if (userId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(userId);
        }
        if (duration == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(duration);
        }
        if (commentable == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (commentable ? 0x01 : 0x00));
        }
        dest.writeString(state);
        if (originalContentSize == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(originalContentSize);
        }
        dest.writeString(lastModified);
        dest.writeString(sharing);
        dest.writeString(tagList);
        dest.writeString(permalink);
        if (streamable == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (streamable ? 0x01 : 0x00));
        }
        dest.writeString(embeddableBy);
        if (downloadable == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (downloadable ? 0x01 : 0x00));
        }
        dest.writeValue(purchaseUrl);
        dest.writeValue(labelId);
        dest.writeValue(purchaseTitle);
        dest.writeString(genre);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(labelName);
        dest.writeString(release);
        dest.writeString(trackType);
        dest.writeString(keySignature);
        dest.writeString(isrc);
        dest.writeValue(videoUrl);
        dest.writeValue(bpm);
        dest.writeValue(releaseYear);
        dest.writeValue(releaseMonth);
        dest.writeValue(releaseDay);
        dest.writeString(originalFormat);
        dest.writeString(license);
        dest.writeString(uri);
        dest.writeValue(user);
        dest.writeString(permalinkUrl);
        dest.writeString(artworkUrl);
        dest.writeString(waveformUrl);
        dest.writeString(streamUrl);
        if (playbackCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(playbackCount);
        }
        if (downloadCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(downloadCount);
        }
        if (favoritingsCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(favoritingsCount);
        }
        if (commentCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(commentCount);
        }
        dest.writeString(attachmentsUri);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
}