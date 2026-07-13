package notification.send_worker.service;

import org.springframework.stereotype.Service;

import notification.send_worker.dto.NotificationEvent;

@Service
public class SendWorkerService {

    private final ProcessNotification processNotification;

    public SendWorkerService(ProcessNotification processNotification) {
        this.processNotification = processNotification;
    }

    public void processNotification(NotificationEvent event){

        processNotification.sendNotification(event);

    }


}
