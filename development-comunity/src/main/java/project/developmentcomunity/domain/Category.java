package project.developmentcomunity.domain;

import java.util.List;

public class Category {

    private long categoryId;
    private String categoryName;
    private String linkPageUrl;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLinkPageUrl() {
        return linkPageUrl;
    }

    public void setLinkPageUrl(String linkPageUrl) {
        this.linkPageUrl = linkPageUrl;
    }
}
