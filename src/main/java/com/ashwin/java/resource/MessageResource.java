package com.ashwin.java.resource;

import com.ashwin.java.LogFileApplication;
import com.ashwin.java.model.Message;
import com.ashwin.java.model.User;
import com.codahale.metrics.annotation.Timed;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/logfile")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private static final String TAG = MessageResource.class.getName();
    private static Log LOG = LogFactory.getLog(LogFileApplication.LOGGER);

    private String template;
    private final AtomicLong counter;
    private static final String defaultName = "user";

    public MessageResource(String template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @Path("/greet")
    public Message greet(@QueryParam("name") Optional<String> name) {
        LOG.debug(TAG + " | GET | Greeting name: " + name);

        final String msg = String.format(template, name.orElse(defaultName));
        LOG.debug(TAG + " | GET | Greeting msg: " + msg);

        return new Message(counter.incrementAndGet(), msg);
    }

    @POST
    @Timed
    @Path("/greet")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message greet(@NotNull @Valid final User user) {
        LOG.debug(TAG + " | POST | Greeting user: " + user);

        Optional<String> name = Optional.of(user.getName());

        final String msg = String.format(template, name.orElse(defaultName));
        LOG.debug(TAG + " | POST | Greeting msg: " + msg);

        return new Message(user.getId(), msg);
    }
}
