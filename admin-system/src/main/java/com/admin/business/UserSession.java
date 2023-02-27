//package com.admin.business;
//
//import com.admin.Language;
//import com.admin.business.entity.Notification;
//import com.admin.business.entity.User;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.annotation.PreDestroy;
//import javax.enterprise.context.SessionScoped;
//import javax.enterprise.event.Observes;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.Serializable;
//import java.security.Principal;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
///**
// * *************************
// ********** AFCSOFT ******** *************************
// */
//@Setter
//@Getter
//@Named
//@SessionScoped
//public class UserSession implements Serializable {
//
//
//
//    private Optional<User> currentUser;
//
//    private List<String> grantedUrls;
//
//    private List<Module> grantedModules;
//
//    private Language language;
//
//    private List<Notification> notificationList;
//
//    private int unReadeNotificationCount = 0;
//
//    private int notificationID;
//
//    private String currentModule;
//
//    private Module currentModuleObject;
//
//
//    @Getter
//    private String userFullName;
//
//
//
//
//
//
//
//    public String getDirection() {
//        return language.getDirection();
//    }
//
//    public String getDirectionClass() {
//        return getDirection().equals("rtl") ? "special-string" : "";
//    }
//
//    public List<Notification> getNotificationList() {
//
//        return this.notificationList;
//    }
//
//    @PreDestroy
//    public void destroySession() {
//        Logger.getLogger(UserSession.class.getSimpleName()).log(Level.INFO, () -> "Session destroyed");
//    }
//
//
//
//
//
//
//}
