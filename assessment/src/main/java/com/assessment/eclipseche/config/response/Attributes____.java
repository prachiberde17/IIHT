
package com.assessment.eclipseche.config.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contribute_to_branch"
})
public class Attributes____ {

    @JsonProperty("contribute_to_branch")
    private List<String> contributeToBranch = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contribute_to_branch")
    public List<String> getContributeToBranch() {
        return contributeToBranch;
    }

    @JsonProperty("contribute_to_branch")
    public void setContributeToBranch(List<String> contributeToBranch) {
        this.contributeToBranch = contributeToBranch;
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
