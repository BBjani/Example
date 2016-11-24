package com.myapp.rest.services;

import com.myapp.services.InfrastructureException;
import com.myapp.services.UserManagerService;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Path("UserManagerRsService")
@Slf4j
public class UserManagerRsService {

    private final UserManagerService ums = UserManagerService.getInstance();

    /*
    @PUT
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(@Context Request request, Person person) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put("RequestId", requestId);
        try {
            ums.createPerson(person);
            return Response.status(Response.Status.OK).entity(person).build();
        } catch (ValidationException ve) {
            log.error("unable to create person", ve);
            return Response.status(Response.Status.BAD_REQUEST).entity(ve.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (InfrastructureException ie) {
            log.error("unable to create person", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (RuntimeException re) {
            log.error("critical error - unable to create person", re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        }
    }

    @POST
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put("RequestId", requestId);
        try {
            ums.updatePerson(person);
            return Response.status(Response.Status.OK).entity(person).build();
        } catch (ValidationException ve) {
            log.error("unable to update person", ve);
            return Response.status(Response.Status.BAD_REQUEST).entity(ve.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (DuplicateException de) {
            log.error("unable to create person", de);
            return Response.status(Response.Status.BAD_REQUEST).entity(de.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (InfrastructureException ie) {
            log.error("unable to update person", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (RuntimeException re) {
            log.error("critical error - unable to update person", re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        }
    }

    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getPersonById(@PathParam("id") Integer id) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put("RequestId", requestId);
        try {
            Person person = ums.getPersonById(id);
            if (null == person) {
                return Response.status(Response.Status.NOT_FOUND).entity("Person not found for ID: " + id).
                        type(MediaType.TEXT_PLAIN).build();
            }
            return Response.status(Response.Status.OK).entity(person).build();
        } catch (InfrastructureException ie) {
            log.error("unable to execute query", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (RuntimeException re) {
            log.error("critical error - unable to create person", re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        }
    }
     */
    @GET
    @Path("/person/list")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getPeople() {
        final String requestId = UUID.randomUUID().toString();
        MDC.put("RequestId", requestId);
        try {
            return Response.status(Response.Status.OK).entity(ums.getPeople()).build();
        } catch (InfrastructureException ie) {
            log.error("unable to execute query", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (RuntimeException re) {
            log.error("critical error - unable to create person", re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        }
    }
    /*
    @DELETE
    @Path("/person/{id}")
    public Response deletePerson(@PathParam("id") Integer id) {
        final String requestId = UUID.randomUUID().toString();
        MDC.put("RequestId", requestId);
        try {
            ums.deletePerson(id);
            return Response.noContent().build();
        } catch (InfrastructureException ie) {
            log.error("unable to delete person", ie);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ie.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        } catch (RuntimeException re) {
            log.error("critical error - unable to create person", re);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(re.getMessage()).
                    type(MediaType.TEXT_PLAIN).header("X-Request-Id", requestId).build();
        }
    }
    /*
    @PUT
    @Path("/company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCompany(Company company) {
        ums.createCompany(company);
    }

    @POST
    @Path("/company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCompany(Company company) {
        ums.updateCompany(company);
    }

    @GET
    @Path("/company/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company getCompanyById(@PathParam("id") Integer id) {
        return ums.getCompanyById(id);
    }

    @GET
    @Path("/company/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getCompanies() {
        return ums.getCompanies();
    }

    @DELETE
    @Path("/company/{id}")
    public void deleteCompany(@PathParam("id") Integer id) {
        ums.deleteCompany(id);
    }
     */
}
