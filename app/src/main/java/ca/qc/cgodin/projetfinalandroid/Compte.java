package ca.qc.cgodin.projetfinalandroid;

public class Compte {
    private String avatar;
    private String name;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Compte(String name, String avatar) {
        this.avatar = avatar;
        this.name = name;
    }

    public Compte() {
    }
}
