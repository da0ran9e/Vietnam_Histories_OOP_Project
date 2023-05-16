public class Emperor {
    public String name;
    public String reign;
    public String capital;

    public Emperor(String name, String reign, String capital) {
        this.name = name;
        this.reign = reign;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReign() {
        return reign;
    }

    public void setReign(String reign) {
        this.reign = reign;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
