const form = document.querySelector('form');

form.addEventListener('submit', (event) => {
//     阻止表单的默认提交行为
    event.preventDefault();

//     获取表单中的数据
    const id = form.id.value;
    const password = form.password.value;

//     发送请求
    const url = 'http://localhost:8080/user/login';
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            password: password
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // 登录失败
            if(data.data.code === 3){
                alert("Login failed. Check id and password.");
            }
            // 登录成功
            else{
                // 保存用户信息
                localStorage.setItem("userInfo", JSON.stringify(data.data));

                // 跳转到主页
                switch (data.data.role) {
                    case 0:
                        window.location.href = "../admin/index.html";
                        break;
                    case 1:
                        window.location.href = "../stu/index.html";
                        break;
                    case 2:
                        window.location.href = "../tea/index.html";
                        break;
                    default:
                        alert("Something wrong. Please try again.");
                }
            }
        })
        .catch(error => console.log(error));
});
