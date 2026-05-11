package com.example;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {

    @GET
    public List<Note> list() {
        return Note.listAll();
    }

    @POST
    @Transactional
    public Response create(Note note) {
        note.id = null;
        note.createdAt = LocalDateTime.now();
        note.persist();
        return Response.status(Response.Status.CREATED).entity(note).build();
    }
}
