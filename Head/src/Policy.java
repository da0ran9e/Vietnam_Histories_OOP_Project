public class Policy {
    public String policyName;
    public int policyYear;

    public Policy(String policyName, int policyYear) {
        this.policyName = policyName;
        this.policyYear = policyYear;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public int getPolicyYear() {
        return policyYear;
    }

    public void setPolicyYear(int policyYear) {
        this.policyYear = policyYear;
    }
}
