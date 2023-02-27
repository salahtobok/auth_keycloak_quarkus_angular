package com.admin;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * See https://antoniogoncalves.org/2019/11/07/configuring-a-quarkus-application-with-profiles/ for more information
 */
@ApplicationScoped
public class ApplicationLifeCycle {

    @Inject
    Logger logger;

    void onStart(@Observes StartupEvent ev) {
        logger.info("The application is starting with profile " + ProfileManager.getActiveProfile());
    }
}