package com.tx.controller;

import com.tx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianxuan on 16/2/19.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private  TestService testService;

    @Autowired
    JmsTemplate jmsTemplate;

    @RequestMapping("/testconf")
    public void testConfigure(@RequestParam(value="arg1" ,required=false ,defaultValue = "") String arg1,HttpServletRequest request,HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(testService.getConfigure());
            response.setContentType("text/html,charset=utf8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }

    }
    @RequestMapping("/sendmsg")
    public void sendMsg(@RequestParam(value="arg1" ,required=false ,defaultValue = "") String arg1,HttpServletRequest request,HttpServletResponse response) {
        final String message = "服务商提供者1在发布消息"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        PrintWriter writer = null;
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        try {
            writer = response.getWriter();
            writer.write(message);
            response.setContentType("text/html,charset=utf8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }

    }
}
