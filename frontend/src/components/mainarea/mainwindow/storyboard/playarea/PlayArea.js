import React, {Component} from 'react';
import {
    CardsUl,
    PlayingArea
} from "../style/VotingAreaStyle";
import Card from "./Card";

class PlayArea extends Component {
    state = {
        data: ["resources/images/0_card.png", "resources/images/half_card.png", "resources/images/1_card.png",
            "resources/images/2_card.png", "resources/images/3_card.png", "resources/images/5_card.png",
            "resources/images/8_card.png", "resources/images/8_card.png", "resources/images/13_card.png",
            "resources/images/20_card.png", "resources/images/40_card.png", "resources/images/100_card.png",
            "resources/images/coffee_card.png", "resources/images/question_card.png"],
        cards: []
    };

    componentDidMount() {
        for (let i=0; i<this.state.data.length; i++){
            this.state.cards.push(<Card source={this.state.data[i]}/>)
        }
    }

    render() {
        return (
            <PlayingArea>
                <CardsUl>
                    {this.state.cards}
                </CardsUl>
            </PlayingArea>
        );
    }
}

export default PlayArea;
