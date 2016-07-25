package com.stone.beautyshare.config;

import android.util.Log;
import android.util.Xml;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.stone.beautyshare.PreloaderActivity;
import com.utils.singleton.SingleTonVolley;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ClassifyConfig {

    private static List<Classify> classifies = null;
    private static List<String>urls;
    static {
        urls = new ArrayList<>();
        urls.add("http://7xkfzx.com1.z0.glb.clouddn.com/vi_data_n.xml");
        urls.add("http://7xkfzx.com1.z0.glb.clouddn.com/vi_data_s.xml");
    }

    public ClassifyConfig() {

    }

    public static void getDataByVolley(final PreloaderActivity activity){
        //? what is the context for
        final List<Classify> res = null;
        String url = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 18 ? urls.get(0) : urls.get(1);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        try {
                            classifies = readXML(new ByteArrayInputStream(response.getBytes("UTF-8")));
                            for(Classify classify : classifies){
                                Log.d("TAG", classify.name);
                            }
                            activity.loadComplete();

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        SingleTonVolley.getInstance(activity).addToRequestQueue(stringRequest);
    }

    private static List<Classify> readXML(InputStream inStream) {
        XmlPullParser parser = Xml.newPullParser();
        List<Classify> classifies = null;
        try {
            parser.setInput(inStream, "UTF-8");// 设置数据源编码
            int eventType = parser.getEventType();// 获取事件类型
            Classify classify = null;
            Classify.Photos currentPhotos = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        classifies = new ArrayList<Classify>();// 实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("classify")) {
                            classify = new Classify();
                            classify.name = parser.getAttributeValue("", "name");
                            classify.photos = new ArrayList<>();
                        } else if (classify != null) {
                            if (name.equalsIgnoreCase("photos")) {
                                currentPhotos = new Classify.Photos();
                                currentPhotos.name = parser.getAttributeValue("", "name");
                                currentPhotos.baseUrl =parser.getAttributeValue("", "baseurl");
                                currentPhotos.isnew =  Integer.parseInt(parser.getAttributeValue("", "isnew"));
                                currentPhotos.isgather = Integer.parseInt(parser.getAttributeValue("", "isgather"));
                                currentPhotos.pics = new ArrayList<>();
                            } else if (name.equalsIgnoreCase("singlepic")) {
                                Classify.SinglePic pic = new Classify.SinglePic();
                                pic.url = parser.getAttributeValue("", "url");
                                currentPhotos.pics.add(pic);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        //读完一个classify，可以将其添加到集合类中
                        if(parser.getName().equalsIgnoreCase("photos") && currentPhotos != null){
                            classify.photos.add(currentPhotos);
                            currentPhotos = null;
                        }else
                        if (parser.getName().equalsIgnoreCase("classify")&& classify != null) {
                            classifies.add(classify);
                            classify = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            inStream.close();
            return classifies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Classify> getClassifies() {
        return classifies;
    }
}