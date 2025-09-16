package com.chatservice.users.events.supplier;

import com.chatservice.users.events.model.UserChangeModel;
import com.chatservice.users.utils.ActionEnum;
import com.chatservice.users.utils.UserContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

@Component
public class UserServiceSupplier {

    private final Logger logger = LogManager.getLogger(UserServiceSupplier.class);
    private final Queue<UserChangeModel> eventQueue = new ConcurrentLinkedQueue<>();

    public void publishUserServiceChange(ActionEnum action, String personalId){
        logger.debug("\nSending Kafka Message {} for User Personal Id: {}", action, personalId);
        UserChangeModel change = new UserChangeModel(
                UserChangeModel.class.getTypeName(),
                action.toString(),
                personalId,
                UserContext.getCorrelationId()
        );
        eventQueue.offer(change);
    }

    //  T A K E S  T H E  P L A C E  O F  T H E  S O U R C E
    @Bean
    public Supplier<UserChangeModel> userChangePublisher(){
        return () -> {
            UserChangeModel model = eventQueue.poll();
            if (model != null) {
                logger.debug("\nPublishing event: {}", model);
            }
            return model;
        };
    }
//  - - - - - - - - - - - - - - - T H I S  I S  D E P R E C A T E D ! ! !

//    private Source source;
//
//    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);
//
//    public SimpleSourceBean(Source source){
//        this.source = source;
//    }
//
//    public void publishOrganizationChange(ActionEnum action, String organizationId){
//        logger.debug("Sending Kafka message {} for Organization Id: {}", action, organizationId);
//        OrganizationChangeModel change =  new OrganizationChangeModel(
//                OrganizationChangeModel.class.getTypeName(),
//                action.toString(),
//                organizationId,
//                UserContext.getCorrelationId());
//
//        source.output().send(MessageBuilder.withPayload(change).build());
//    }
}
