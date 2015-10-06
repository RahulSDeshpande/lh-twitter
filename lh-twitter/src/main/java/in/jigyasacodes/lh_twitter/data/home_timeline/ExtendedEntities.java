
package in.jigyasacodes.lh_twitter.data.home_timeline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ExtendedEntities {

    @SerializedName("media")
    @Expose
    private List<Medium> media = new ArrayList<Medium>();

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
