package in.jigyasacodes.lh_twitter.data.usertimeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medium____ {

	private Integer id;
	private String idStr;
	private List<Integer> indices = new ArrayList<Integer>();
	private String mediaUrl;
	private String mediaUrlHttps;
	private String url;
	private String displayUrl;
	private String expandedUrl;
	private String type;
	private Sizes__ sizes;
	private Integer sourceStatusId;
	private String sourceStatusIdStr;
	private Integer sourceUserId;
	private String sourceUserIdStr;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return The idStr
	 */
	public String getIdStr() {
		return idStr;
	}

	/**
	 * @param idStr The id_str
	 */
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	/**
	 * @return The indices
	 */
	public List<Integer> getIndices() {
		return indices;
	}

	/**
	 * @param indices The indices
	 */
	public void setIndices(List<Integer> indices) {
		this.indices = indices;
	}

	/**
	 * @return The mediaUrl
	 */
	public String getMediaUrl() {
		return mediaUrl;
	}

	/**
	 * @param mediaUrl The media_url
	 */
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	/**
	 * @return The mediaUrlHttps
	 */
	public String getMediaUrlHttps() {
		return mediaUrlHttps;
	}

	/**
	 * @param mediaUrlHttps The media_url_https
	 */
	public void setMediaUrlHttps(String mediaUrlHttps) {
		this.mediaUrlHttps = mediaUrlHttps;
	}

	/**
	 * @return The url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return The displayUrl
	 */
	public String getDisplayUrl() {
		return displayUrl;
	}

	/**
	 * @param displayUrl The display_url
	 */
	public void setDisplayUrl(String displayUrl) {
		this.displayUrl = displayUrl;
	}

	/**
	 * @return The expandedUrl
	 */
	public String getExpandedUrl() {
		return expandedUrl;
	}

	/**
	 * @param expandedUrl The expanded_url
	 */
	public void setExpandedUrl(String expandedUrl) {
		this.expandedUrl = expandedUrl;
	}

	/**
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return The sizes
	 */
	public Sizes__ getSizes() {
		return sizes;
	}

	/**
	 * @param sizes The sizes
	 */
	public void setSizes(Sizes__ sizes) {
		this.sizes = sizes;
	}

	/**
	 * @return The sourceStatusId
	 */
	public Integer getSourceStatusId() {
		return sourceStatusId;
	}

	/**
	 * @param sourceStatusId The source_status_id
	 */
	public void setSourceStatusId(Integer sourceStatusId) {
		this.sourceStatusId = sourceStatusId;
	}

	/**
	 * @return The sourceStatusIdStr
	 */
	public String getSourceStatusIdStr() {
		return sourceStatusIdStr;
	}

	/**
	 * @param sourceStatusIdStr The source_status_id_str
	 */
	public void setSourceStatusIdStr(String sourceStatusIdStr) {
		this.sourceStatusIdStr = sourceStatusIdStr;
	}

	/**
	 * @return The sourceUserId
	 */
	public Integer getSourceUserId() {
		return sourceUserId;
	}

	/**
	 * @param sourceUserId The source_user_id
	 */
	public void setSourceUserId(Integer sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	/**
	 * @return The sourceUserIdStr
	 */
	public String getSourceUserIdStr() {
		return sourceUserIdStr;
	}

	/**
	 * @param sourceUserIdStr The source_user_id_str
	 */
	public void setSourceUserIdStr(String sourceUserIdStr) {
		this.sourceUserIdStr = sourceUserIdStr;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
