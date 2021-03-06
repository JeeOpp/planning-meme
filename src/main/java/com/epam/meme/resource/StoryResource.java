package com.epam.meme.resource;

import com.epam.meme.dto.StoryUpdateDto;
import com.epam.meme.dtoconverter.StoryConverter;
import com.epam.meme.dto.StoryDto;
import com.epam.meme.entity.Story;
import com.epam.meme.service.BoardService;
import com.epam.meme.service.StoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "/stories", description = "Manage stories")
public class StoryResource {

    @Autowired
    private StoryService storyService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private StoryConverter storyConverter;

    @POST
    @ApiOperation(value = "Save story")
    public Response create(@PathParam("boardId") Long boardId,
                           @Valid StoryDto storyDto,
                           @Context UriInfo uriInfo) {
        Story story = storyConverter.convertToEntity(storyDto);
        story.setBoard(boardService.findById(boardId).orElseThrow(NotFoundException::new));
        storyService.create(story);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "boardResource")
                .path(BoardResource.class, "storyResource")
                .resolveTemplate("boardId", story.getBoard().getId())
                .path(Long.toString(story.getId()))
                .build();

        return Response.created(uri).build();
    }

    @GET
    @ApiOperation(value = "Find stories in range")
    public List<StoryDto> findAll(@PathParam("boardId")   Long boardId,
                                  @QueryParam("page")     int page,
                                  @QueryParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return storyService.findAllByBoardId(boardId, pageable)
                .getContent()
                .stream()
                .map(this.storyConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @GET
    @ApiOperation(value = "Find story by id")
    @Path("/{storyId}")
    public Story findById(@PathParam("storyId") Long storyId) {
        return storyService.findById(storyId).orElseThrow(NotFoundException::new);
    }

    @PUT
    @ApiOperation(value = "Update story by id")
    @Path("/{storyId}")
    public void update(@PathParam("storyId") Long storyId, StoryUpdateDto storyDto) {
        // Check for presence in data store
        storyService.findById(storyId).orElseThrow(NotFoundException::new);

        Story story = storyConverter.convertToEntityForUpdate(storyDto);
        story.setId(storyId);
        if (storyDto.isSetStartTime()) {
            story.setStartTime(LocalDateTime.now());
        } else if (storyDto.isSetFinishTime()) {
            story.setFinishTime(LocalDateTime.now());
        }
        log.info("Estimation = {}", story.getEstimation());
        storyService.update(story);
    }

    @DELETE
    @ApiOperation(value = "Delete story by id")
    @Path("/{storyId}")
    public void delete(@PathParam("storyId") Long storyId) {
        storyService.deleteById(storyId);
    }

    @Path("/{storyId}/votes")
    public Class<VoteResource> voteResource(){
        return VoteResource.class;
    }

}
