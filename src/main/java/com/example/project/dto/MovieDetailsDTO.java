package com.example.project.dto;

public class MovieDetailsDTO {
    private String title;
    private String releaseDate;
    private String genres;

    public MovieDetailsDTO(String title, String releaseDate, String genres) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
   

