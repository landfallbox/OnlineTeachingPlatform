window.addEventListener('load', function (){
    // 从 localStorage 中获取用户信息显示在导航栏中
    displayUserInfoInNav();

    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    const stuId = userInfo.id;

    // 向后端请求得到已选课程列表并保存到localStorage中
    fetch('http://localhost:8080/course/list/selected?stuId=' + stuId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const courseList = data.data;

            // 显示课程信息
            displayCourseList(courseList, true);
        })
        .catch(error => console.log(error));
});
