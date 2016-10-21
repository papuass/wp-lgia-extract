
package lv.miga.aiz.pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "location",
    "location_type",
    "viewport"
})
public class Geometry {

    @JsonProperty("location")
    public Location location;
    @JsonProperty("location_type")
    public String locationType;
    @JsonProperty("viewport")
    public Viewport viewport;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Geometry() {
    }

    /**
     * 
     * @param viewport
     * @param location
     * @param locationType
     */
    public Geometry(Location location, String locationType, Viewport viewport) {
        this.location = location;
        this.locationType = locationType;
        this.viewport = viewport;
    }

}
