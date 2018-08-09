function nextArrowBtnHandler(evt) {
    evt.preventDefault();
    let currentPromotion;
    let promotionArr = $("#promotion-img-box").querySelectorAll("li");
    // for of는 break문이 동작, foreach는 안멈춤
    for(let promotion of promotionArr) {
        if(promotion.classList.contains("current")) {
            promotion.classList.remove("current");
            currentPromotion = promotion.nextElementSibling || promotion.parentElement.firstElementChild;
            currentPromotion.classList.add("current");
            break;
        }
    }
    highlightNextDot();
}

function prevArrowBtnHandler(evt) {
    evt.preventDefault();
    let currentPromotion;
    let promotionArr = $("#promotion-img-box").querySelectorAll("li");
    // for of는 break문이 동작, foreach는 안멈춤
    for(let promotion of promotionArr) {
        if(promotion.classList.contains("current")) {
            promotion.classList.remove("current");
            currentPromotion = promotion.previousElementSibling || promotion.parentElement.lastElementChild;
            currentPromotion.classList.add("current");
            break;
        }
    }
    highlightPrevDot();
}

function highlightNextDot() {
    let currentDot;
    let dotArr = $("#dot-btn-box").querySelectorAll("a");
    for(let dot of dotArr) {
        if(dot.classList.contains("on")) {
            dot.classList.remove("on");
            currentDot = dot.nextElementSibling || dot.parentElement.firstElementChild;
            currentDot.classList.add("on");
            break;
        }
    }
}

function highlightPrevDot() {
    let currentDot;
    let dotArr = $("#dot-btn-box").querySelectorAll("a");
    for(let dot of dotArr) {
        if(dot.classList.contains("on")) {
            dot.classList.remove("on");
            currentDot = dot.previousElementSibling || dot.parentElement.lastElementChild;
            currentDot.classList.add("on");
            break;
        }
    }
}

function removeHighlightDot() {
    let dotArr = $("#dot-btn-box").querySelectorAll("a");
    for(let dot of dotArr) {
        if(dot.classList.contains("on")) {
            dot.classList.remove("on");
            break;
        }
    }
}

function removeCurrentPromotion() {
    let promotionArr = $("#promotion-img-box").querySelectorAll("li");
    for(let promotion of promotionArr) {
        if(promotion.classList.contains("current")) {
            promotion.classList.remove("current");
            break;
        }
    }
}

function changeClickedPromotion(targetPosition) {
    $("#promotion-img-box").querySelectorAll("li")[targetPosition].classList.add("current");
}

function isNotDot(target) {
    return $("#dot-btn-box").isEqualNode(target);
}

function moveClickedDotHandler(evt) {
    evt.preventDefault();
    if(isNotDot(evt.target) === true) {
        return false;
    }
    removeHighlightDot();
    evt.target.classList.add("on");
    removeCurrentPromotion();
    changeClickedPromotion(Array.from($("#dot-btn-box").querySelectorAll("a")).indexOf(evt.target));
}

function setEventListener() {
    $("#btnPrevArrow").addEventListener("click", prevArrowBtnHandler);
    $("#btnNextArrow").addEventListener("click", nextArrowBtnHandler);
    $("#dot-btn-box").addEventListener("click", moveClickedDotHandler);
}

document.addEventListener("DOMContentLoaded", () => {
    setEventListener();
});

import {$, fetchManager} from "../utils.js";