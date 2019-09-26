package com.trello.qa.model;

public class BoardData {
    private String boardTitle;

//    public BoardData(String boardTitle) {
//        this.boardTitle = boardTitle;
//    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public BoardData withBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
        return this;
    }
}
