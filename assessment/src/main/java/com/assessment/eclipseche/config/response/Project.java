
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
    "links",
    "name",
    "attributes",
    "type",
    "source",
    "path",
    "description",
    "problems",
    "mixins"
})
public class Project {

    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("attributes")
    private Attributes____ attributes;
    @JsonProperty("type")
    private String type;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("path")
    private String path;
    @JsonProperty("description")
    private String description;
    @JsonProperty("problems")
    private List<Object> problems = null;
    @JsonProperty("mixins")
    private List<String> mixins = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("attributes")
    public Attributes____ getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes____ attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("problems")
    public List<Object> getProblems() {
        return problems;
    }

    @JsonProperty("problems")
    public void setProblems(List<Object> problems) {
        this.problems = problems;
    }

    @JsonProperty("mixins")
    public List<String> getMixins() {
        return mixins;
    }

    @JsonProperty("mixins")
    public void setMixins(List<String> mixins) {
        this.mixins = mixins;
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
