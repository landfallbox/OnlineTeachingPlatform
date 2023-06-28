// 页面加载时执行
window.onload = function () {
    // 从 localStorage 中获取用户信息并显示在导航栏中
    displayUserInfoInNav();

    // 从 localStorage 中获取用户信息
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    const stuId = userInfo.id;

    // 向后端请求得到课程列表并保存到localStorage中
    fetch('http://localhost:8080/course/list/all?stuId=' + stuId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const courseList = data.data;

            // 检查课程列表是否存在
            if (courseList && courseList.length > 0) {
                // 显示课程信息
                displayCourseList(courseList, false);
            }
})};
