package ru.github.pvtitov.bookshelf.book_details_contract;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("industryIdentifiers")
    @Expose
    private List<IndustryIdentifier> industryIdentifiers = null;
    @SerializedName("readingModes")
    @Expose
    private ReadingModes readingModes;
    @SerializedName("pageCount")
    @Expose
    private int pageCount;
    @SerializedName("printedPageCount")
    @Expose
    private int printedPageCount;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("maturityRating")
    @Expose
    private String maturityRating;
    @SerializedName("allowAnonLogging")
    @Expose
    private boolean allowAnonLogging;
    @SerializedName("contentVersion")
    @Expose
    private String contentVersion;
    @SerializedName("panelizationSummary")
    @Expose
    private PanelizationSummary panelizationSummary;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("previewLink")
    @Expose
    private String previewLink;
    @SerializedName("infoLink")
    @Expose
    private String infoLink;
    @SerializedName("canonicalVolumeLink")
    @Expose
    private String canonicalVolumeLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPrintedPageCount() {
        return printedPageCount;
    }

    public void setPrintedPageCount(int printedPageCount) {
        this.printedPageCount = printedPageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public boolean isAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public PanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

}