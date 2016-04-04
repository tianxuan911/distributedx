package com.tx.controller;

import com.tx.dubbo.demo.DemoService;
import com.tx.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by tianxuan on 16/2/19.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private  TestService testService;
    @Autowired(required = false)
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
    @RequestMapping("/sessiontest")
    public ResponseEntity<String> sessionTest(HttpSession session,@RequestParam("name") String name){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType=new MediaType("text","html", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        ResponseEntity<String> rs = new ResponseEntity(StringUtils.defaultString(session.getAttribute("name")+"", "session中不存在"),headers,HttpStatus.OK);
        session.setAttribute("name",name);
        return  rs;
    }
}
