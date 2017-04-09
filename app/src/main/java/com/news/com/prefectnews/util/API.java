package com.news.com.prefectnews.util;

/**
 * Created by Administrator on 2017/3/19.
 */

public class API {
    //知乎详细内容 拼接id
    public static final String ZHIHU_CONTENT="http://news-at.zhihu.com/api/4/news/";
    //果壳精选列表
    public  static final String GUOLISTURI="http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";
  //果壳精选内容 拼接id
    public static final String GUOQIAO_CONTENT="http://jingxuan.guokr.com/pick/";
    //豆瓣列表 拼接日期 eg:2016-02-15
    public static final String DOUBAN_LIST_URI="https://moment.douban.com/api/stream/date/";
    //豆瓣详细内容 拼接id
    public static final String DOUBAN_CONTENT="https://moment.douban.com/api/post/";
}
