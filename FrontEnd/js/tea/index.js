// 主页加载时从 localStorage 中获取用户信息并显示在导航栏中
window.addEventListener('load', displayUserInfoInNav);

// 初始化FontAwesome
window.FontAwesomeKitConfig = {
    asyncLoading: {
        enabled: true
    }
};

// 模态框
const createCourseModal = new bootstrap.Modal(document.getElementById('createCourseModal'));

// 悬浮按钮 用于创建新课程
const createCourseBtn = document.getElementById('create-course-btn');
// 点击悬浮按钮显示模态框
createCourseBtn.addEventListener('click', function () {
    createCourseModal.show();
});

// 模态框中的关闭按钮
const closeModalBtn = document.getElementById('close-modal');
// 点击关闭按钮隐藏模态框
closeModalBtn.addEventListener('click', function () {
    createCourseModal.hide();
});

// 课程名
const courseName = document.getElementById('course-name');
// 开始时间
const beginTime = document.getElementById('begin-time');
// 结束时间
const endTime = document.getElementById('end-time');
// 视频文件
const videoFile = document.getElementById('video-file');
// 从 localStorage 中读取教师 ID
const teacherId = JSON.parse(localStorage.getItem('userInfo')).id;


// 模态框中的提交按钮
const submitModalBtn = document.getElementById('submit-modal');
// 点击提交按钮创建新课程
submitModalBtn.addEventListener('click', function () {
    const formData = new FormData();
    // 将课程信息添加到表单数据
    formData.append('name', courseName.value);
    formData.append('beginTime', beginTime.value);
    formData.append('endTime', endTime.value);
    formData.append('teacherId', teacherId);
    formData.append('video', videoFile.files[0]);

    fetch('http://localhost:8080/course/create', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => console.log(error));
});




