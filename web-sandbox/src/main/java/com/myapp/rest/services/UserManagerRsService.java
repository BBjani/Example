package com.myapp.rest.services;

import com.myapp.model.Person;
import com.myapp.services.InfrastructureException;
import com.myapp.services.UserManagerService;
import com.myapp.services.ValidationException;
import java.util.UUID;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 *
 * 
 */
@RolesAllowed("users")
@Path("UserManagerRsService")
@Slf4j
public class UserManagerRsService {

    private final UserManagerService ums = UserManagerService.getInstance();
    private static final String X_REQUEST_ID = "X-Request-Id";
    private static final String CRITICAL_ERROR = "critical error";
    private static final String REQUEST_ID = "RequestId";
    private static final String CHARACTER_ENCODING = ";charset=utf-8";

    /**
     *
     * @param person
     * @return
     */
    @PUT
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON + CHARACTER_ENCODING)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(final Person person) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        try {
            ums.createPerson(person);
            return Response.status(Response.Status.OK).entity(person).build();
        } catch (ValidationException ve) {
            log.error("unable to create person", ve);
            return Response.status(Response.Status.BAD_REQUEST).entity(ve.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (InfrastructureException ie) {
            log.error("unable to create person", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (RuntimeException re) {
            log.error(CRITICAL_ERROR, re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        }
    }

    /**
     *
     * @param person
     * @return
     */
    @POST
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON + CHARACTER_ENCODING)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(final Person person) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        try {
            ums.updatePerson(person);
            return Response.status(Response.Status.OK).entity(person).build();
        } catch (ValidationException ve) {
            log.error("unable to update person", ve);
            return Response.status(Response.Status.BAD_REQUEST).entity(ve.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (InfrastructureException ie) {
            log.error("unable to update person", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (RuntimeException re) {
            log.error(CRITICAL_ERROR, re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON + CHARACTER_ENCODING)
    public Response getPersonById(@PathParam("id") final Integer id) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        try {
            final Person person = ums.getPersonById(id);
            if (null == person.getName()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Person not found for ID: " + id)
                        .type(MediaType.TEXT_PLAIN).build();
            }
            return Response.status(Response.Status.OK).entity(person).build();
        } catch (InfrastructureException ie) {
            log.error("unable to execute query", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (RuntimeException re) {
            log.error(CRITICAL_ERROR, re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        }
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/person/list")
    @Produces(MediaType.APPLICATION_JSON + CHARACTER_ENCODING)
    public Response getPeople() {
        final String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        try {
            return Response.status(Response.Status.OK).entity(ums.getPeople()).build();
        } catch (InfrastructureException ie) {
            log.error("unable to execute query", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (RuntimeException re) {
            log.error(CRITICAL_ERROR, re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/person/{id}")
    public Response deletePerson(@PathParam("id") final Integer id) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        try {
            ums.deletePerson(id);
            return Response.noContent().build();
        } catch (InfrastructureException ie) {
            log.error("unable to delete person", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        } catch (RuntimeException re) {
            log.error(CRITICAL_ERROR, re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage())
                    .type(MediaType.TEXT_PLAIN).header(X_REQUEST_ID, requestId).build();
        }
    }
}
