package org.cs.dp.radar.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue bsmsKeepalive() {
        return new Queue("bsms_keepalive");
    }

    /**
     * 声明一个名为simple的队列
     */
    @Bean
    public Queue testQueuet() {
        return new Queue("simple");
    }

    /**
     * 声明一个名为topic.message1的队列
     */
    @Bean
    public Queue topicQueue(){
        return new Queue("topic.baio1");
    }

    /**
     * 声明一个名为topic.message2的队列
     */
    @Bean
    public Queue topicQueue2() {
        return new Queue("topic.baio2");
    }

    /**
     * 声明一个名为exchange的交换机
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("baio.exchange");
    }

    /**
     * 将topic.message1的队列绑定到exchange交换机
     */
    @Bean
    public Binding bindMessage1(){
        return BindingBuilder.bind(topicQueue()).to(exchange()).with("topic.baio1");
    }

    /**
     * 将topic.message2的队列绑定到exchange交换机
     */
    @Bean
    public Binding bindMessage2() {
        return BindingBuilder.bind(topicQueue2()).to(exchange()).with("topic.baio2");
    }

    /**
     * 声明一个名为exchange的交换机
     */
    @Bean
    public FanoutExchange baioAllexchange(){
        return new FanoutExchange("baio.exchangete.all");
    }

    @Bean
    public FanoutExchange brsAllexchange(){
        return new FanoutExchange("brs.exchangete.all");
    }

    @Bean
    public TopicExchange brsTopicexchange(){
        return new TopicExchange("brs.exchangete.topic");
    }

    @Bean
    public Queue brstopicQueuea() {
        return new Queue("abcde123456");
    }
    @Bean
    public Binding bindMessagebrsa(){
        return BindingBuilder.bind(brstopicQueuea()).to(brsTopicexchange()).with("abcde123456");
    }


    /**
     * 声明一个名为topic.message2的队列
     */
    @Bean
    public Queue brstopicQueue1() {
        return new Queue("topic.brs1");
    }

    /**
     * 声明一个名为exchange的交换机
     */
    @Bean
    public TopicExchange brsexchange(){
        return new TopicExchange("brs.exchange");
    }

    /**
     * 将topic.message1的队列绑定到exchange交换机
     */
    @Bean
    public Binding bindMessagebrs1(){
        return BindingBuilder.bind(brstopicQueue1()).to(brsexchange()).with("topic.brs1");
    }



    /**
     * 声明一个名为fanout.1的队列
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.1");
    }
    /**
     * 声明一个名为fanout.2的队列
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.2");
    }
    /**
     * 声明一个名为fanout.3的队列
     */
    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout.3");
    }

    /**
     * 声明一个名为fanoutExchange的转发器
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列fanoutQueue1绑定到fanout转发器
     */
    @Bean
    public Binding bindFanout1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    /**
     * 将队列fanoutQueue1绑定到fanout转发器
     */
    @Bean
    public Binding bindFanout2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     * 将队列fanoutQueue1绑定到fanout转发器
     */
    @Bean
    public Binding bindFanout3() {
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }


}
