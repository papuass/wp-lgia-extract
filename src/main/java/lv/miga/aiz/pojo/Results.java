
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
    "results",
    "status"
})
public class Results {

    @JsonProperty("results")
    public List<Result> results = new ArrayList<Result>();
    @JsonProperty("status")
    public String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Results() {
    }

    /**
     * 
     * @param results
     * @param status
     */
    public Results(List<Result> results, String status) {
        this.results = results;
        this.status = status;
    }

}
