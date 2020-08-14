package com.ashwin.java;

import com.ashwin.java.health.TemplateHealthCheck;
import com.ashwin.java.resource.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogFileApplication extends Application<LogFileConfiguration> {
    private static final String TAG = LogFileApplication.class.getName();
    public static final String LOGGER = "demo-logger";
    private static Log LOG = LogFactory.getLog(LOGGER);

    public static void main(String[] args) throws Exception {
        new LogFileApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<LogFileConfiguration> bootstrap) {
        LOG.info(TAG + ": initialize");
    }

    @Override
    public String getName() {
        return "log-file";
    }

    @Override
    public void run(LogFileConfiguration config, Environment env) throws Exception {
        LOG.info(TAG + ": run");

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(config.getTemplate());
        env.healthChecks().register("template", healthCheck);

        final MessageResource helloWorldResource = new MessageResource(config.getTemplate());
        env.jersey().register(helloWorldResource);
    }
}
