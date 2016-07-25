package com.stone.beautyshare.config;

import java.util.List;

/**
 * Created by windows7 on 2015/7/21.
 */

public class Classify {
    public  String name;
    public  List<Photos> photos;

    public static class Photos{
        public String name;
        public String baseUrl;
        public int isnew;
        public int isgather;
        public List<SinglePic> pics;
    }

    public static class SinglePic {
        public String url;
    }
}
