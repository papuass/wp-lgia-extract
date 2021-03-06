
package lv.miga.aiz.pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "lat",
    "lng"
})
public class Southwest {

    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lng")
    public Double lng;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Southwest() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Southwest(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
