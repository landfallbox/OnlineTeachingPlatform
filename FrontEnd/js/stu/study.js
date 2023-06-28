window.addEventListener('load', function () {
    displayUserInfoInNav();

    // URL中的参数部分
    const urlParams = new URLSearchParams(window.location.search);
    // 课程ID
    const courseId = urlParams.get('courseId');

    // 向后端发送请求获取课程视频URL
    fetch('http://localhost:8080/video?courseId=' + courseId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // 课程视频URL
            const videoUrl = data.data;
            // 视频播放器
            const videoPlayer = document.getElementById('video');
            // 装载视频
            videoPlayer.src = videoUrl;

        })
        .catch(error => console.log(error));

    // 加载课程信息
    fetch('http://localhost:8080/course/info?courseId=' + courseId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // 课程信息
            const courseInfo = data.data;

            // 课程名称
            const courseName = document.getElementById('course-name');
            // 授课教师名称
            const teacherName = document.getElementById('teacher-name');
            // 开始时间
            const beginTime = document.getElementById('begin-time');
            // 结束时间
            const endTime = document.getElementById('end-time');

            // 显示课程信息
            courseName.innerText = courseInfo.name;
            teacherName.innerText = "Teacher name: " + courseInfo.teacherName;
            beginTime.innerText = "Begin time: " + courseInfo.beginTime;
            endTime.innerText = "End time: " + courseInfo.endTime;
        })
        .catch(error => console.log(error));

    // 加载评论
    displayComments(courseId);

    // 从 localStorage 中获取用户信息
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    // 学生ID
    const stuId = userInfo.id;
    // 加载已选的其他课程
    displayOtherCourses(stuId, courseId);
});

// 按钮绑定点击事件提交输入框内容
const submitButton = document.getElementById('submit-button');
submitButton.addEventListener('click', function () {
    // 从 localStorage 中获取用户信息
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    // 学生ID
    const stuId = userInfo.id;

    // URL中的参数部分
    const urlParams = new URLSearchParams(window.location.search);
    // 课程ID
    const courseId = urlParams.get('courseId');

    //  评论内容
    const comment = document.getElementById('comment-input').value;
    // 评论时间
    const commentTime = new Date().toISOString();

    fetch('http://localhost:8080/comment/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            stuId: stuId,
            courseId: courseId,
            content: comment,
            time: commentTime
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            displayComments(courseId);

            // 清空输入框
            document.getElementById('comment-input').value = '';
        })
        .catch(error => console.log(error));
});

// 加载评论区
function displayComments(courseId){
    fetch('http://localhost:8080/comment/list/all?courseId=' + courseId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const commentList = data.data;
            const commentListContainer = document.getElementById('comment-list');

            // Clear previous comments
            commentListContainer.innerHTML = '';

            commentList.forEach(comment => {
                // Create elements for each comment
                const commentContainer = document.createElement('div');
                commentContainer.classList.add('comment');

                const commentInfo = document.createElement('div');
                commentInfo.classList.add('comment-info');
                commentInfo.innerText = `${comment.stuName} at ${comment.time}`;

                const commentContent = document.createElement('div');
                commentContent.classList.add('comment-content');
                commentContent.innerText = comment.content;

                // Append comment elements to the comment list container
                commentContainer.appendChild(commentInfo);
                commentContainer.appendChild(commentContent);
                commentListContainer.appendChild(commentContainer);
            });
        })
        .catch(error => console.log(error));
}

// 加载已选的其他课程
function displayOtherCourses(stuId, courseId){
    fetch('http://localhost:8080/course/list/selected/other?stuId=' + stuId + '&courseId=' + courseId, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const selectedCoursesContainer = document.getElementById('selected-courses-container');

            // 清空已选课程列表
            selectedCoursesContainer.innerHTML = '';

            data.data.forEach(course => {
                // 创建课程卡片元素
                const courseCard = document.createElement('div');
                courseCard.classList.add('course-card');

                // 设置课程信息
                courseCard.innerHTML = `
                    <h4>${course.name}</h4>
                    <p>教师: ${course.teacherName}</p>
                `;

                // 将课程卡片添加到已选课程列表容器中
                selectedCoursesContainer.appendChild(courseCard);
            });

            // 标题绑定点击事件实现课程页面动态切换
            const courseCards = document.getElementsByClassName('course-card');
            for (let i = 0; i < courseCards.length; i++) {
                courseCards[i].addEventListener('click', function () {
                    // 跳转到课程页面
                    window.location.href = 'study.html?courseId=' + data.data[i].id;
                });
            }
        })
        .catch(error => console.log(error));
}
