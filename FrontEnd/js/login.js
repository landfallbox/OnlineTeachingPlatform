const form = document.querySelector('form');

form.addEventListener('submit', (event) => {
//     阻止表单的默认提交行为
    event.preventDefault();

//     获取表单中的数据
    const id = form.id.value;
    const password = form.password.value;

//     发送请求
    const url = 'http://localhost:8080/user/login?id=' + id + '&password=' + password;
    fetch(url, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // 登录失败
            if(data.code === 3){
                alert("Login failed. Check id and password.");
            }

        //     登录成功，跳转到主页
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
        })
        .catch(error => console.log(error));
});
