package pages.api.models;

public class RepoModel {
    private String name;
    private String description;
    private boolean isPrivate;

    public RepoModel(String name, String description, boolean isPrivate){
        this.description = description;
        this.name = name;
        this.isPrivate = isPrivate;
    }
}
