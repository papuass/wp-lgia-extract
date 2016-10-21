
package lv.miga.aiz.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "address_components",
    "formatted_address",
    "geometry",
    "partial_match",
    "place_id",
    "types"
})
public class Result {

    @JsonProperty("address_components")
    public List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
    @JsonProperty("formatted_address")
    public String formattedAddress;
    @JsonProperty("geometry")
    public Geometry geometry;
    @JsonProperty("partial_match")
    public Boolean partialMatch;
    @JsonProperty("place_id")
    public String placeId;
    @JsonProperty("types")
    public List<String> types = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param placeId
     * @param formattedAddress
     * @param types
     * @param partialMatch
     * @param addressComponents
     * @param geometry
     */
    public Result(List<AddressComponent> addressComponents, String formattedAddress, Geometry geometry, Boolean partialMatch, String placeId, List<String> types) {
        this.addressComponents = addressComponents;
        this.formattedAddress = formattedAddress;
        this.geometry = geometry;
        this.partialMatch = partialMatch;
        this.placeId = placeId;
        this.types = types;
    }

}
