package com.tmsca.sagarwal.tmscaapp.Recycler;

public class Item {
        String title;
        String descp;
        String url;
        public Item(String title, String descp, String url){
            this.title = title;
            this.descp = descp;
            this.url = url;

        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescp() {
            return descp;
        }

        public void setDescp(String descp) {
            this.descp = descp;
        }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
