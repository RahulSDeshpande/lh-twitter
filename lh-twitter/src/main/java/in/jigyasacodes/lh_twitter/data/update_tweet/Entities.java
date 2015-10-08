
package in.jigyasacodes.lh_twitter.data.update_tweet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities {

    @SerializedName("description")
    @Expose
    private Description description;

    /**
     * 
     * @return
     *     The description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Description description) {
        this.description = description;
    }

}
