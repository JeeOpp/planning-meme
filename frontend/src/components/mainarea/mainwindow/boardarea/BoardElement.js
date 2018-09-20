import React, {Component} from 'react';
import './css/style.css'


const $ = window.jQuery;

class BoardElement extends Component{

    goFromBoardToStory(){
        $('#storyArea').show();
        $('#boardArea').hide();
        window.sessionStorage.setItem('boardName', this.props.name);
        window.sessionStorage.setItem('boardId', this.props.id);
    }

    deleteBoard(){
        $('#boardToDelete').val(this.props.id);
    }
    
    editBoard(){
        $('#boardToEdit').val(this.props.id);
    }

    render() {
        return (
            <tr className="clickable ng-scope">
                <td className="name-td" onClick={() => this.goFromBoardToStory()}>
                    <div>{this.props.name}</div>
                </td>
                <td className="hidden-xs" onClick={() => this.goFromBoardToStory()}>
                    <div>In: <span className="ng-binding ng-scope">{String(this.props.startTime).replace('T', ' / ')}</span></div>
                </td>
                <td className="hidden-xs" onClick={() => this.goFromBoardToStory()}>
                    <div className="of ng-binding ng-scope">{this.props.storiesCount}</div>
                </td>
                <td className="edit-icon"><i onClick={(e) => this.editBoard(e)} data-toggle="modal" data-target="#editBoard"
                                             className="editButton fa fa-edit"/>
                </td>
                <td className="delete-icon"><span data-toggle="modal" data-target="#confirm-delete"
                                                  className="deleteButton"><img onClick={(e) => this.deleteBoard(e)} className="hover deleteImg"
                                                                                src="https://planitpoker.azureedge.net/Content/delete-icon-hover.png"/></span>
                </td>
            </tr>
        );
    }
}

export default BoardElement;