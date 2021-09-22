package com.nps.devassessment.model;

public class placement {

    private Integer id;

    private Long yjb_yp_id;

    private String establishment;

    private String placementComments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getYjb_yp_id() {
        return yjb_yp_id;
    }

    public void setYjb_yp_id(Long yjb_yp_id) {
        this.yjb_yp_id = yjb_yp_id;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getPlacementComments() {
        return placementComments;
    }

    public void setPlacementComments(String placementComments) {
        this.placementComments = placementComments;
    }
}
