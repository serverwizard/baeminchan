function nextArrowBtnHandler(evt) {
    evt.preventDefault();
    let currentPromotion;
    let promotionArr = $("#promotion-img-box").querySelectorAll("li");
    // for of는 break문이 동작, foreach는 안
    for(let promotion of promotionArr) {
        if(promotion.classList.contains("current")) {
            promotion.classList.remove("current");
            currentPromotion = promotion.nextElementSibling || promotion.parentElement.firstElementChild;
            currentPromotion.classList.add("current");
            return false;
        }
    }
}

function prevArrowBtnHandler(evt) {
    evt.preventDefault();
    let currentPromotion;
    let promotionArr = $("#promotion-img-box").querySelectorAll("li");
    // for of는 break문이 동작, foreach는 안
    for(let promotion of promotionArr) {
        if(promotion.classList.contains("current")) {
            promotion.classList.remove("current");
            currentPromotion = promotion.previousElementSibling || promotion.parentElement.lastElementChild;
            currentPromotion.classList.add("current");
            return false;
        }
    }
}

function setEventListener() {
    $("#btnPrevArrow").addEventListener("click", prevArrowBtnHandler);
    $("#btnNextArrow").addEventListener("click", nextArrowBtnHandler);
}

document.addEventListener("DOMContentLoaded", () => {
    setEventListener();
});

import {$, fetchManager} from "../utils.js";
