const sidebar_item = document.getElementsByClassName("sidebar_item")

function sidebar_itemHide(){
    for(let i = 1;i < sidebar_item.length;i++){
        sidebar_item[i].classList.remove('active')
    }
}
function sidebar_itemClick(i){
    sidebar_itemHide()
    sidebar_item[i].classList.toggle('active')
}
for(let i = 1 ;i < sidebar_item.length;i++){
    sidebar_item[i].addEventListener('click', function(){
        sidebar_itemClick(i)
    })
}