package com.tx.util;

/**
 * Created by tianxuan on 16/3/6.
 */
public class ConsumerMessageListenerTwo{

    public void onMessage(String msg) {
//        if(message instanceof TextMessage){
//            TextMessage textMessage = (TextMessage) message;
//            try {
                System.out.println("ConsumerMessageListenerTwo收到了文本消息：\t" + msg);
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
