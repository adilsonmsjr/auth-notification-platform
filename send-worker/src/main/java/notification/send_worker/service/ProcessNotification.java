package notification.send_worker.service;

import notification.send_worker.dto.NotificationEvent;

public interface ProcessNotification {

    void sendNotification(NotificationEvent event);

}
