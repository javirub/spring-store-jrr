package com.laberit.sina.bootcamp.modulo3.spring_web.model;

public class ProductDetail {
    private Long id;
    private String name;
    private String description;
    private String lang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ProductDetail(Long id, String name, String description, String lang) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lang = lang;
    }
}
