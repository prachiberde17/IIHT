
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
    "tomcat8-debug",
    "tomcat8"
})
public class Servers {

    @JsonProperty("tomcat8-debug")
    private Tomcat8Debug tomcat8Debug;
    @JsonProperty("tomcat8")
    private Tomcat8 tomcat8;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tomcat8-debug")
    public Tomcat8Debug getTomcat8Debug() {
        return tomcat8Debug;
    }

    @JsonProperty("tomcat8-debug")
    public void setTomcat8Debug(Tomcat8Debug tomcat8Debug) {
        this.tomcat8Debug = tomcat8Debug;
    }

    @JsonProperty("tomcat8")
    public Tomcat8 getTomcat8() {
        return tomcat8;
    }

    @JsonProperty("tomcat8")
    public void setTomcat8(Tomcat8 tomcat8) {
        this.tomcat8 = tomcat8;
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
