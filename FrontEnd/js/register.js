const form = document.querySelector('form');

form.addEventListener('submit', (event) => {
//     阻止表单的默认提交行为
    event.preventDefault();

//     获取表单中的数据
    const username = form.username.value;
    const password = form.password.value;
    const role = form.role.value;

    console.log(username);
    console.log(password);
    console.log(role);

//     发送请求
    const url = 'http://localhost:8080/user/register';
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            role: role
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // 注册失败
            if(data.code === 1){
                alert("Register failed. User already exists.");
            }

        //     注册成功，跳转到主页
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
