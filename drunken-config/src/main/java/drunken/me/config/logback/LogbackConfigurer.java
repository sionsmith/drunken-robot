package drunken.me.config.logback;

import java.net.URL;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class LogbackConfigurer {

    public static final String CONFIG_RESOURCE_KEY = "configuration";
    public static final String ENVIRONMENT_KEY = "environment";
    public static final String INTRA_OR_INTER_KEY = "intranetOrInternet";

    public static void initLogging(String configResource, String environment, String intranetOrInternet) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            final URL url = ResourceUtils.getURL(configResource);
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            lc.putProperty(ENVIRONMENT_KEY, environment);
            if (intranetOrInternet != null && intranetOrInternet.length() > 0) {
                lc.putProperty(INTRA_OR_INTER_KEY, intranetOrInternet);
            }
            System.out.printf("configuring logback with %s and environment %s\n", url, environment);
            configurator.doConfigure(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
