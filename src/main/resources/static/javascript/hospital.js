var img_data = { // 图片基本数据
        img: ['/lunbo/guke.jpg','/lunbo/gangchang.png','/lunbo/jinshenke.jpg'],
        total: 3
    },
    img_dom = document.getElementById('img_box'); //轮播的元素
    curren_img = '/lunbo/guke.jpg' // 当前轮播到的图片
    window.onload = function () {
        solid(img_data,img_dom)
    }
function solid(data,dom) {
    let i = 0
    setInterval(() => {
        if (i >= data.total) {
            i = 0
        }
        dom.setAttribute('src', data.img[i]);
        curren_img = data.img[i]
        i ++
    }, 5000)
}
var ulogin = document.getElementById("ulogin")
var svg_i = document.getElementById("svg_i")
var svg_o = document.getElementById("svg_o")
// login
function onver(){
    ulogin.style.display="block"
}
function onout(){
    ulogin.style.display="none"
}
function clickI(){
    document.getElementById("svg_o").style.display="block"
    document.getElementsByClassName("navs")[0].style.animation="h_nav2 1s"
    // 动画最终显示为动画完成后最终状态
    document.getElementsByClassName("navs")[0].style.animationFillMode="forwards"
    document.getElementsByClassName("navr")[0].style.animation="h_nav 1s"
    document.getElementsByClassName("navr")[0].style.animationFillMode="forwards";
    svg_i.style.display="none"
}
function clickO(){
    document.getElementById("svg_i").style.display="block"
    document.getElementsByClassName("navs")[0].style.animation="h_nav3 1s"
    document.getElementsByClassName("navs")[0].style.animationFillMode="forwards"
    document.getElementsByClassName("navr")[0].style.animation="h_nav1 1s"
    document.getElementsByClassName("navr")[0].style.animationDirection="forwards"
    svg_o.style.display="none"
}
// active 
$(function(){
    $(".navs a").each(function () {
        var href = $(this).attr(href);
        if(location.pathname==$(this).attr('href')){
            $('.navs a').removeClass('active');
            $(this).addClass('active');
        }
    })
});
$(function () {
    $(".navs a").click(function () {
        console.log(1)
    })
})
// 游客界面
$(function () {
    var hide = document.getElementsByClassName("hide")[0]
    var navs_li = document.getElementsByClassName("navs_li")[0]
    navs_li.onmouseover = function(){
        hide.style.display = "block"
    }
    navs_li.onmouseout = function(){
        hide.style.display = "none"
    }
    hide.onmouseover = function(){
        hide.style.display="block"
    }
    hide.onmouseout = function(){
        hide.style.display = "none"
    }
})


$(function () {
    $('#select1').blur(function () {
        var index = select1.selectedIndex;
        var value = select1.options[index].innerText;
        $.get("/user/option?department="+value,{},function(data){
            $('#select2').empty();
            $.each(data,function (index,data) {
                $('#select2').append('<option >'+data.nickname+'</option>')
            });
        });
    })
})
function submit1(){
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth()+1;
    var day = now.getDate();
    var riqi = year + "-" + month + "-" + day;
    var date = $('#orderTime').val();
    if(date >=  riqi) {
        $.ajax({
            type: "get",
            dataType: "json",
            url: "/order/save",
            data: $('#form1').serialize(),
            success: function (result) {
                if (result == true) {
                    alert("预订成功");
                } else if (result == false) {
                    alert("您已有订单未处理！");
                }
            },
            error: function () {
                alert("系统异常！");
            }
        });
    }else{
        alert("请选择未来的时间！");
    }
}
var pid=0;
function modela(id){
    pid = id;
    $("#modelst").css("display","block");
}
function yizhu(){
    $.get("/doctor/charged?pId="+pid+"&charged="+$('#yizhu').val(),{},function (data) {
           var message = confirm("病人已出院！已自动生成病历，是否查看病历表？");
            if (message == true){
                $('#ciiBtn').click();
                $('#modal_body').append('<p>患者id:'+data.pId+'</p>')
                $('#modal_body').append('<p>患者姓名:'+data.name+'</p>')
                $('#modal_body').append('<p>入院时间:'+data.hospitalTime+'</p>')
                $('#modal_body').append('<p>出院时间:'+data.outTime+'</p>')
                $('#modal_body').append('<p>病症:'+data.disease+'</p>')
                $('#modal_body').append('<p>病床id:'+data.hId+'</p>')
                $('#modal_body').append('<p>房间:'+data.room+'</p>')
                $('#modal_body').append('<p>床位号:'+data.bed+'</p>')
                $('#modal_body').append('<p>处方:'+data.chuFang+'</p>')
                $('#modal_body').append('<p>医生id:'+data.doctorId+'</p>')
                $('#modal_body').append('<p>医嘱:'+data.charged+'</p>')
            }else{
                window.location.reload();
            }
              //
    })
}
function close1() {
    $("#modelst").css("display","none");
}
function xiugai() {
    $('#reset').click();
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/doctor/modify",
        data: $('#xiugai1').serialize(),

        success: function (result) {

            if (result == 1) {
                alert("前后密码不一致");
                $('#close').click();
            } else if (result == 2) {
                alert("修改成功");
                $('#close').click();
            }else{
                alert("密码错误");
                $('#close').click();
            }
        },
        error: function () {
            alert("系统异常！");
        }
    });
}
function btn(data) {
    alert(data);
}


