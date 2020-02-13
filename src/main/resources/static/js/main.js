var apiLogin = function(form, callback) {
     var path = '/login'
     ajax('POST', path, form, callback)
}
 var apiRegister = function(form, callback) {
     var path = '/register'
     ajax('POST', path, form, callback)
}
var apiIndex = function(callback) {
    var path = '/index'
    ajax('GET', path, callback)
}
var bindEventLogin = function() {
    var b = e('#login')
    b.addEventListener('click', function(){
        var nameInput = e('.login_name_content')
        var passwordInput = e('.login_password_content')
        var form = {
            name: nameInput.value,
            password: passwordInput.value,
        }
        apiLogin(form, function(message) {
            if (message.code == '0'){
                alert("账号或者密码错误")
            } else {
                redirect(e('#redirect'))
            }
        })
    })
}
var bindEventRegister = function() {
    var b = e('#register')
    b.addEventListener('click', function(){
        var nameInput = e('.register_name_content')
        var passwordInput = e('.register_password_content')
        var form = {
            name: nameInput.value,
            password: passwordInput.value,
        }
        apiRegister(form, function(message) {
            if (message.code == '0'){
                alert("该用户名已存在")
            } else {
                alert("注册成功")
            }
        })
    })
}

bindEventLogin()
bindEventRegister()