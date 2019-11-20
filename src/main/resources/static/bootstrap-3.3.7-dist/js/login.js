const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');


signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});


signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

// $(function () {
//     $('#submitSave').click(function () {
//             $.ajax({
//                 type:"get",
//                 dataType:"json",
//                 url:"/doctor/save",
//                 data:$('#reg').serialize(),
//                 success:function (data) {
//                     if (data == 1){
//                         alert("账户名已被使用!");
//                     }else if (data == 2){
//                         alert("该号码已被注册！");
//                     } else if (data == 3){
//                         alert("该邮箱已被注册！");
//                     }else {
//                         alert("注册成功！");
//                         window.location.reload();
//                     }
//                 }
//             })
//         });
// })