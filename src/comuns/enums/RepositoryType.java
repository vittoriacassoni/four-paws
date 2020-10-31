package comuns.enums;

public enum RepositoryType {
    SQLSERVER("SqlServer");

    private String description;

    RepositoryType(String description) { this.description = description; }

    public String getDescription() { return description; }
}
