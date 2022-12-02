import { numberWithCommas, removeCommas } from "../script/numbeWithComma.js";
import { countDays, dateFormat } from "./countDays.js";

const order_date = document.getElementsByClassName("order_date")
const bill_info_total_money = document.getElementsByClassName("bill_info_total_money")
const point_btn = document.getElementsByClassName("point-btn")
// const pointUp_Btn = document.getElementsByClassName("point-up")

// for(let i = 0 ;i < pointUp_Btn.length ;i++){
//     pointUp_Btn[i].style.display = "none";
// }
const bill_info = document.getElementsByClassName("bill-info")
const bill_rooms = document.getElementsByClassName("bill-rooms")
function changePointBtn(i){
    if(point_btn[i].classList.contains("fa-chevron-down")){
        point_btn[i].classList.remove("fa-chevron-down")
        point_btn[i].classList.add("fa-chevron-up")

        bill_rooms[i].style.display = "block"
    }else{
        point_btn[i].classList.remove("fa-chevron-up")
        point_btn[i].classList.add("fa-chevron-down") 

        bill_rooms[i].style.display = "none"
    }
}
for(let i = 0 ;i < point_btn.length;i++){
    bill_info[i].addEventListener('click', function(){
        changePointBtn(i)
    })
}


const no_cart_list = document.getElementById("no-cart-list")

if(point_btn.length === 0){
    no_cart_list.style.display = "flex"
}else{
    no_cart_list.style.display = "none"
}



import { dateSwap } from "./dateHandle.js";


const checkin_date = document.getElementsByClassName("checkin_date");
const checkout_date = document.getElementsByClassName("checkout_date");
const total_days = document.getElementsByClassName("total_days");

 for(let i = 0 ;i < total_days.length ;i++){
     checkin_date[i].value = dateFormat(checkin_date[i].value)
     checkout_date[i].value = dateFormat(checkout_date[i].value)
 }
  for(let i = 0 ;i < order_date.length ;i++){
     order_date[i].value = dateFormat(order_date[i].value)
     bill_info_total_money[i].innerHTML = numberWithCommas(bill_info_total_money[i].innerHTML)
 }

dateSwap();

function getDays() {
    for (let i = 0; i < total_days.length; i++) {
        total_days[i].innerHTML =
            countDays(checkin_date[i].value,
                checkout_date[i].value);
    }
}
getDays();

const moneys_1_day = document.getElementsByClassName('money-1-day');
const room_total_money = document.querySelectorAll('.room-total-money-text');





function room_total_moneyCal() {
    for (let i = 0; i < moneys_1_day.length; i++) {
        room_total_money[i].innerHTML = numberWithCommas(parseInt(removeCommas(moneys_1_day[i].innerHTML)) * parseInt(total_days[i].innerHTML));
        moneys_1_day[i].innerHTML = numberWithCommas(moneys_1_day[i].innerHTML);
    }
}
room_total_moneyCal();