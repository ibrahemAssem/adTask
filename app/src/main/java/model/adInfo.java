package model;


/**
 *
 * this the model for each Ad
 *
 */


public class adInfo implements Comparable <adInfo> {

    private String title;
    private String url;
    private String picture;
    private int    order;


    public adInfo (String title, String url,String picture ,int order ) {
        this.title = title;
        this.url = url;
        this.picture = picture;
        this.order = order;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String Url ) {
        this.url = Url;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int compareTo(adInfo compareFruit) {

        int compareQuantity = ((adInfo) compareFruit).getOrder();

        //this way to return the list in ascending order
        return this.order - compareQuantity;



    }
}
