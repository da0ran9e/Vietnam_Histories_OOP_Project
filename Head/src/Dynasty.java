import java.util.List;

public class Dynasty {
    public String name;
    public int startYear;
    public int endYear;
    public List<Emperor> emperors;
    public List<Event> importantEvents;
    public List<Policy> importantPolicies;
    public String geography;
    public String territorySize;
    public List<CultureAndSociety> cultureAndSociety;

    public Dynasty(String name, int startYear, int endYear, List<Emperor> emperors, List<Event> importantEvents, List<Policy> importantPolicies, String geography, String territorySize, List<CultureAndSociety> cultureAndSociety) {
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.emperors = emperors;
        this.importantEvents = importantEvents;
        this.importantPolicies = importantPolicies;
        this.geography = geography;
        this.territorySize = territorySize;
        this.cultureAndSociety = cultureAndSociety;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public List<Emperor> getEmperors() {
        return emperors;
    }

    public void setEmperors(List<Emperor> emperors) {
        this.emperors = emperors;
    }

    public List<Event> getImportantEvents() {
        return importantEvents;
    }

    public void setImportantEvents(List<Event> importantEvents) {
        this.importantEvents = importantEvents;
    }

    public List<Policy> getImportantPolicies() {
        return importantPolicies;
    }

    public void setImportantPolicies(List<Policy> importantPolicies) {
        this.importantPolicies = importantPolicies;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getTerritorySize() {
        return territorySize;
    }

    public void setTerritorySize(String territorySize) {
        this.territorySize = territorySize;
    }

    public List<CultureAndSociety> getCultureAndSociety() {
        return cultureAndSociety;
    }

    public void setCultureAndSociety(List<CultureAndSociety> cultureAndSociety) {
        this.cultureAndSociety = cultureAndSociety;
    }
}
