package com.covid19.covid19tracker.model;

public class RegionWiseStatistics {
    private String country;
    private String state;
    private int totalActiveCases;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalActiveCases() {
        return totalActiveCases;
    }

    public void setTotalActiveCases(int totalActiveCases) {
        this.totalActiveCases = totalActiveCases;
    }

    @Override
    public String toString() {
        return "RegionWiseStatistics{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", totalActiveCases=" + totalActiveCases +
                '}';
    }
}
