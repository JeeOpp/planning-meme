import styled from "styled-components";

const StoryStyle = styled.div`margin-top: 3em;`;

const StoryEditIAttribute = styled.i.attrs({
    'data-toggle': "modal",
    'data-target': "#editStory",
    className: "editStory fa fa-edit"
})``;

const StoryDeleteTdAttribute = styled.td.attrs({
    className: "delete-icon",
    'data-toggle': "modal",
    'data-target': "#deleteStory"
})``;

const StoryPaginationUl = styled.ul.attrs({
    className: "pagination pagination-lg"
})``;

export {
    StoryEditIAttribute, StoryDeleteTdAttribute, StoryStyle, StoryPaginationUl
}