
package lv.miga.aiz.pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "northeast",
    "southwest"
})
public class Viewport {

    @JsonProperty("northeast")
    public Northeast northeast;
    @JsonProperty("southwest")
    public Southwest southwest;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Viewport() {
    }

    /**
     * 
     * @param southwest
     * @param northeast
     */
    public Viewport(Northeast northeast, Southwest southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }

}
