package com.tx.controller;

import com.tx.dubbo.demo.DemoService;
import com.tx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tianxuan on 16/2/19.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private  TestService testService;
    @Autowired
    private DemoService demoService;
    @Autowired
    JmsTemplate jmsTemplate;
    @RequestMapping("/testconf")
    public void testConfigure(@RequestParam(value="arg1" ,required=false ,defaultValue = "") String arg1,HttpServletRequest request,HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(demoService.sayHello("田旋"));
            response.setContentType("text/html,charset=utf8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }

    }
    @RequestMapping("/recivemsg")
    public void reciveMsg(@RequestParam(value="arg1" ,required=false ,defaultValue = "") String arg1,HttpServletRequest request,HttpServletResponse response) {
        PrintWriter writer = null;
        Message message = jmsTemplate.receive(jmsTemplate.getDefaultDestination());

        try {
            writer = response.getWriter();
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                writer.write("接收到消息:"+textMessage.getText());
            }
            response.setContentType("text/html,charset=utf8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }

    }
}
