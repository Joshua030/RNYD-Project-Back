package com.rnyd.rnyd.dto.diet;

public class PreferencesAndAllergiesDTO {

    private String preferences;
    private String allergies;

    // Getters and Setters
    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public PreferencesAndAllergiesDTO(String preferences, String allergies) {
        this.preferences = preferences;
        this.allergies = allergies;
    }
}
