class SearchTextBox {
    constructor() {
        $("#searching_text").addEventListener("keyup", this.keyboardEventHandler.bind(this));
        $("#search-text-list-box").addEventListener("click", this.mouseClickEventHandler.bind(this));
        this.varInitialize();
    }

    varInitialize() {
        this.KEYCODE = {
            down_arrow: {val: 40, handler: this.downArrowHandler.bind(this)},
            up_arrow: {val: 38, handler: this.upArrowHandler.bind(this)},
            press_enter: {val: 13, handler: this.pressEnterHandler.bind(this)},
            press_key: this.getSearchTextList.bind(this)
        }
        this.CURRENT_HIGHLIGHT_INDEX = -1;
    }

    isEmptySearchText() {
        return $("#searching_text").value === "";
    }

    renderSearchResults(result) {
        let searchResultBox = '';
        for(let resultText of result) {
            searchResultBox += '<li class="">' + resultText.name + '</li>';
        }
        $("#search-text-list-box").querySelector('ul').innerHTML = searchResultBox;
    }

    removeHighlight () {
        $("#search-text-list-box").querySelectorAll("li").forEach(function (searchResultText) {
            if(searchResultText.classList.contains("on")) {
                searchResultText.classList.remove("on");
            }
        });
    }

    renderHighlight() {
        if(this.CURRENT_HIGHLIGHT_INDEX <= -1 || this.CURRENT_HIGHLIGHT_INDEX === $("#search-text-list-box").querySelectorAll("li").length) {
            this.removeHighlight();
            this.closeSearchTextListBox();
            this.CURRENT_HIGHLIGHT_INDEX = -1;
            return false;
        }
        $("#search-text-list-box").querySelectorAll("li")[this.CURRENT_HIGHLIGHT_INDEX].classList.add("on");
    }

    closeSearchTextListBox() {
        $("#search-text-list-box").querySelector('ul').innerHTML = '';
    }

    downArrowHandler() {
        if($("#search-text-list-box").querySelectorAll("li").length === 0) {
            this.getSearchTextList();
            return false;
        }
        this.removeHighlight();
        this.CURRENT_HIGHLIGHT_INDEX++;
        this.renderHighlight();
    }

    upArrowHandler() {
        this.removeHighlight();
        this.CURRENT_HIGHLIGHT_INDEX--;
        this.renderHighlight();
    }

    getSearchTextList() {
        let queryString = "?searchText=".concat($("#searching_text").value);
        fetchManager({
            url: '/api/side/dishes'.concat(queryString),
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: this.renderSearchResults.bind(this)
        });
    }

    pressEnterHandler() {
        $("#search-text-list-box").querySelectorAll("li").forEach(function (searchResultText) {
            if(searchResultText.classList.contains("on")) {
                $("#searching_text").value = searchResultText.textContent;
            }
        });
    }

    keyboardEventHandler(evt) {
        evt.preventDefault();
        if(this.isEmptySearchText() === true){
            return false;
        }
        switch (evt.keyCode) {
            case this.KEYCODE.down_arrow.val : this.KEYCODE.down_arrow.handler(); break;
            case this.KEYCODE.up_arrow.val : this.KEYCODE.up_arrow.handler(); break;
            case this.KEYCODE.press_enter.val : this.KEYCODE.press_enter.handler(); break;
            default : this.KEYCODE.press_key();
        }
    }

    mouseClickEventHandler(evt) {
        evt.preventDefault();
        $("#searching_text").value = evt.target.textContent;
        this.removeHighlight();
        this.CURRENT_HIGHLIGHT_INDEX = Array.prototype.indexOf.call($("#search-text-list-box").querySelectorAll("li"), evt.target);
        this.renderHighlight();
        $("#searching_text").focus();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    new SearchTextBox();
})


// export된 외부 기능을 가져옴
import {$, fetchManager} from "/js/utils.js";

