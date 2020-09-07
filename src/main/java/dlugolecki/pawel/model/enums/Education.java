package dlugolecki.pawel.model.enums;

public enum Education {

    LOWER("HIGH SCHOOL EDUCATION"),
    BACHELOR("BACHELOR EDUCATION"),
    MASTER("MASTER EDUCATION"),
    PHD("PHD EDUCATION");


    private String description;

    Education(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}