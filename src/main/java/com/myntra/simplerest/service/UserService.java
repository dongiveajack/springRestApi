package com.myntra.simplerest.service;

import com.myntra.simplerest.entity.UserEntity;
import com.myntra.simplerest.model.User;
import org.springframework.cache.annotation.Cacheable;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface UserService {
    @POST
    @Path("/")
    User create(User User);

    @PUT
    @Path("/{id}")
    User update(@PathParam("id") Integer id, User User);

    @GET
    @Path("/{id}")
    @Cacheable(value="userCache")
    User get(@PathParam("id") Integer id);

    @GET
    @Path("/")
    List<User> getAll();

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Integer id);

    @GET
    @Path("/findAllFromDb")
    List<UserEntity> findAllFromDb();
}
