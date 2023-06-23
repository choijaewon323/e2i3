
const next = document.querySelectorAll('.next');
const prev = document.querySelectorAll('.prev');
const slider = document.querySelectorAll('.slider')
for(let i =0;i<slider.length;i++){
    makeSlider(slider[i],prev[i],next[i]);
}
function makeSlider(element,prev,next){
    next.addEventListener('click',()=>{
        const offsetX = element.offsetWidth;
        element.scrollBy(offsetX,0)
    })
    prev.addEventListener('click',()=>{
        const offsetX = element.offsetWidth;
        element.scrollBy(-offsetX,0)
    })
}


new Swiper('.awards .swiper', {
    direction: 'horizontal',
    autoplay: true,
    loop: true,
    spaceBetween: 30,
    slidesPerView: 5, 
    navigation : {
        prevEl : '.awards .swiper-prev', 
        nextEl : '.awards .swiper-next'
    }
});

let select = 0;
let disabledIdx = 0;

function goLeft(){
    disabledIdx = select;
    select = (select+3)%4;
    let boxs = document.getElementsByClassName("box0");
    let selectedBox = boxs[select];
    let disabledBox = boxs[disabledIdx];
    selectedBox.style['box-shadow'] = "-15px 15px 3px #9b92ff";
    selectedBox.style['width'] = "15rem";
    selectedBox.style['height'] = "15rem";
    disabledBox.style['box-shadow'] = "0px 0px 0px";
    disabledBox.style['width'] = "14rem";
    disabledBox.style['height'] = "14rem";
}

function goRight(){
    disabledIdx = select;
    select = (select+1)%4;
    let boxs = document.getElementsByClassName("box0");
    let selectedBox = boxs[select];
    let disabledBox = boxs[disabledIdx];
    selectedBox.style['box-shadow'] = "-15px 15px 3px #9b92ff";
    selectedBox.style['width'] = "15rem";
    selectedBox.style['height'] = "15rem";
    disabledBox.style['box-shadow'] = "0px 0px 0px";
    disabledBox.style['width'] = "14rem";
    disabledBox.style['height'] = "14rem";
}