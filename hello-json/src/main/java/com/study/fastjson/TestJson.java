package com.study.fastjson;

import com.alibaba.fastjson.JSON;
import com.study.dto.TextContent;
import com.study.dto.TextInitiativeInfo;
import com.study.jackson.JsonUtil;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/1/18 18:39
 * @Description:
 */
public class TestJson {

    public static void main( String[] args )
    {
        String content = "cccccc  sss  sss  d  d dd 汉字 1234 微软田  书  建[奋斗][骷髅]<a href=\"http://gzadminsit.cnsuning.com/wgg-admin/adminPage/platform/mainel.html\" data-miniprogram-appid=\"wx82dcefe32842671e\" " +
                "data-miniprogram-path=\"/pages/index/index\">小小</a>";
        TextContent text = new TextContent(content);
        TextInitiativeInfo initiativeInfo = new TextInitiativeInfo();
        initiativeInfo.setText(text);
        initiativeInfo.setMsgtype("text");
        initiativeInfo.setTouser("oZOqawcyiLRHzYyAaQNbu_nr9Kg8");

        String sendBody = JSON.toJSONString(initiativeInfo);
        System.out.println(sendBody);
    }
}
