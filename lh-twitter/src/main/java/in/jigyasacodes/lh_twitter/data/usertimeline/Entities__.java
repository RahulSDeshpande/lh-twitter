
package in.jigyasacodes.lh_twitter.data.usertimeline;

import java.util.ArrayList;
import java.util.List;

public class Entities__ {

    private List<Object> hashtags = new ArrayList<Object>();
    private List<Object> symbols = new ArrayList<Object>();
    private List<Object> userMentions = new ArrayList<Object>();
    private List<Url__> urls = new ArrayList<Url__>();
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
    public List<Url__> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url__> urls) {
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
