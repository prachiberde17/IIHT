
package com.assessment.eclipseche.config.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "goal",
    "previewUrl"
})
public class Attributes______ {

    @JsonProperty("goal")
    private String goal;
    @JsonProperty("previewUrl")
    private String previewUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("goal")
    public String getGoal() {
        return goal;
    }

    @JsonProperty("goal")
    public void setGoal(String goal) {
        this.goal = goal;
    }

    @JsonProperty("previewUrl")
    public String getPreviewUrl() {
        return previewUrl;
    }

    @JsonProperty("previewUrl")
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
