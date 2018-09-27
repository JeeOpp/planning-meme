import styled from "styled-components";
import React from "react";

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

export {StoryEditIAttribute, StoryDeleteTdAttribute}