package com.study.anything;

import com.jd.xbp.client.XbpClient;
import com.jd.xbp.client.request.ticket.CreateParam;
import com.jd.xbp.client.service.ProcessService;
import com.jd.xbp.client.service.TicketService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestXbp {

    private static final String API_USER="bx_vehicle";

    private static final String API_SIGN="d50d6b362c";


    public static void main(String[] args) {
        XbpClient client = new XbpClient(API_USER, API_SIGN, XbpClient.ENV.TEST);
        TicketService service = new TicketService(client);
// 请求参数
        CreateParam param = new CreateParam();
// 设置申请人
        param.setUsername("tianshujian1");
// 指定流程
        param.setProcessId(6192);
// 构造表单信息
        Map<String, Object> info = new HashMap<>();
        info.put("车牌号", "京A123K");
        info.put("订单号", "1234567");
        info.put("订单金额", "950.00");
        info.put("保司", "平安");
        info.put("退款时间", "2025-04-07");
        info.put("申请原因", "已线下退款");


        param.setApplicationInfo(info);
//// 构造自选审批人、可选审批人、接口获取等需要传入审批人的参数
//        Map<String, List<String>> approvers = new HashMap<>();
//        List<String> approver1 = new ArrayList<>();
//        approver1.add("tianshujian1");
//        List<String> approver2 = new ArrayList<>();
//        approver2.add("tianshujian1");
//        approvers.put("账号负责人", approver1);
//        approvers.put("系统负责人", approver2);
//        param.setApprovers(approvers);

        try {
            int ticketId = service.create(param);
            log.info("ticket id: {}", ticketId);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
