public class Location {
    public String locationName;
    public GeographicalLocation geographicalLocation;

    public Location(String locationName, GeographicalLocation geographicalLocation) {
        this.locationName = locationName;
        this.geographicalLocation = geographicalLocation;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public GeographicalLocation getGeographicalLocation() {
        return geographicalLocation;
    }

    public void setGeographicalLocation(GeographicalLocation geographicalLocation) {
        this.geographicalLocation = geographicalLocation;
    }
}
