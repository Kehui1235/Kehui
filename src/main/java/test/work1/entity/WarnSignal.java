package test.work1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WarnSignal {
    @JsonProperty("Mx")
    private Double Mx;
    @JsonProperty("Mi")
    private Double Mi;
    @JsonProperty("Ix")
    private Double Ix;
    @JsonProperty("Ii")
    private Double Ii;

    // Getters and Setters
    public Double getMx() {
        return Mx;
    }

    public void setMx(Double mx) {
        Mx = mx;
    }

    public Double getMi() {
        return Mi;
    }

    public void setMi(Double mi) {
        Mi = mi;
    }

    public Double getIx() {
        return Ix;
    }

    public void setIx(Double ix) {
        Ix = ix;
    }

    public Double getIi() {
        return Ii;
    }

    public void setIi(Double ii) {
        Ii = ii;
    }

}
