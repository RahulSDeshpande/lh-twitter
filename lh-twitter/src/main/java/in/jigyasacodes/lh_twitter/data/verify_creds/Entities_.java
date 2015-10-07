
package in.jigyasacodes.lh_twitter.data.verify_creds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Entities_ {

    @SerializedName("hashtags")
    @Expose
    private List<Object> hashtags = new ArrayList<Object>();
    @SerializedName("symbols")
    @Expose
    private List<Object> symbols = new ArrayList<Object>();
    @SerializedName("user_mentions")
    @Expose
    private List<Object> userMentions = new ArrayList<Object>();
    @SerializedName("urls")
    @Expose
    private List<Url> urls = new ArrayList<Url>();
    @SerializedName("media")
    @Expose
    private List<Medium> media = new ArrayList<Medium>();

    /**
     * 
     * @return
     *     The hashtags
     */
    public List<Object> getHashtags() {
        return hashtags;
    }

    /**
     * 
     * @param hashtags
     *     The hashtags
     */
    public void setHashtags(List<Object> hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * 
     * @return
     *     The symbols
     */
    public List<Object> getSymbols() {
        return symbols;
    }

    /**
     * 
     * @param symbols
     *     The symbols
     */
    public void setSymbols(List<Object> symbols) {
        this.symbols = symbols;
    }

    /**
     * 
     * @return
     *     The userMentions
     */
    public List<Object> getUserMentions() {
        return userMentions;
    }

    /**
     * 
     * @param userMentions
     *     The user_mentions
     */
    public void setUserMentions(List<Object> userMentions) {
        this.userMentions = userMentions;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The media
     */
    public List<Medium> getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(List<Medium> media) {
        this.media = media;
    }

}
