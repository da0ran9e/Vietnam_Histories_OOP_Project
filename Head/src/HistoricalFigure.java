import java.util.List;

public class HistoricalFigure {
    public String fullName;
    public int dateOfBirth;
    public String placeOfBirth;
    public int dateOfDeath;
    public String placeOfDeath;
    public List<String> descendants;
    public List<Relation> family;
    public List<String> rolesAndContributions;
    public String dynastyOrPeriod;
    public List<EventOrBattle> participated;
    public List<Artifact> relatedArtifacts;
    public List<String> titleAndHonors;

    public HistoricalFigure(String fullName, int dateOfBirth, String placeOfBirth, int dateOfDeath, String placeOfDeath, List<String> descendants, List<Relation> family, List<String> rolesAndContributions, String dynastyOrPeriod, List<EventOrBattle> participated, List<Artifact> relatedArtifacts, List<String> titleAndHonors) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.placeOfDeath = placeOfDeath;
        this.descendants = descendants;
        this.family = family;
        this.rolesAndContributions = rolesAndContributions;
        this.dynastyOrPeriod = dynastyOrPeriod;
        this.participated = participated;
        this.relatedArtifacts = relatedArtifacts;
        this.titleAndHonors = titleAndHonors;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public int getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(int dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public List<String> getDescendants() {
        return descendants;
    }

    public void setDescendants(List<String> descendants) {
        this.descendants = descendants;
    }

    public List<Relation> getFamily() {
        return family;
    }

    public void setFamily(List<Relation> family) {
        this.family = family;
    }

    public List<String> getRolesAndContributions() {
        return rolesAndContributions;
    }

    public void setRolesAndContributions(List<String> rolesAndContributions) {
        this.rolesAndContributions = rolesAndContributions;
    }

    public String getDynastyOrPeriod() {
        return dynastyOrPeriod;
    }

    public void setDynastyOrPeriod(String dynastyOrPeriod) {
        this.dynastyOrPeriod = dynastyOrPeriod;
    }

    public List<EventOrBattle> getParticipated() {
        return participated;
    }

    public void setParticipated(List<EventOrBattle> participated) {
        this.participated = participated;
    }

    public List<Artifact> getRelatedArtifacts() {
        return relatedArtifacts;
    }

    public void setRelatedArtifacts(List<Artifact> relatedArtifacts) {
        this.relatedArtifacts = relatedArtifacts;
    }

    public List<String> getTitleAndHonors() {
        return titleAndHonors;
    }

    public void setTitleAndHonors(List<String> titleAndHonors) {
        this.titleAndHonors = titleAndHonors;
    }
}
