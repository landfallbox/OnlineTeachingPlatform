// 加载导航栏中的用户信息
function displayUserInfoInNav() {
    // 获取用户信息
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));

    // 检查用户信息是否存在
    if (userInfo) {
        // 导航栏中的用户名
        const username = document.getElementById('username');
        username.innerText = userInfo.username;

        // 下拉菜单中的内容
        const id = document.getElementById('id');
        id.innerText = '用户账号: ' + userInfo.id;

        const name = document.getElementById('name');
        name.innerText = '用户姓名: ' + userInfo.username;

        const role = document.getElementById('role');
        switch (userInfo.role) {
            case 0:
                role.innerText = '用户身份: 管理员';
                break;
            case 1:
                role.innerText = '用户身份: 学生';
                break;
            case 2:
                role.innerText = '用户身份: 教师';
                break;
            default:
                role.innerText = '用户身份: 未知';
        }
    }
}

// 显示课程列表
function displayCourseList(courseList, page) {
    // 获取课程列表容器
    const courseListContainer = document.querySelector('.course-list');

    // 遍历课程列表
    courseList.forEach(function (course) {
        // 创建课程卡片元素
        const courseCard = document.createElement('div');
        courseCard.classList.add('course-card');

        // 创建课程标题元素
        const courseTitle = document.createElement('h3');
        courseTitle.innerText = course.name;

        // 创建课程教师元素
        const courseTeacher = document.createElement('p');
        courseTeacher.innerText = '教师: ' + course.teacherName;

        // 课程开始时间元素
        const formattedBeginTime = new Date(course.beginTime).toLocaleString();
        const beginTime = document.createElement('p');
        beginTime.innerText = '开始时间: ' + formattedBeginTime;

        // 课程结束时间元素
        const formattedEndTime = new Date(course.endTime).toLocaleString();
        const endTime = document.createElement('p');
        endTime.innerText = '结束时间: ' + formattedEndTime;

        // 创建选退课按钮
        const Button = document.createElement('button');
        Button.setAttribute('course-id', course.id);
        Button.setAttribute('selected', course.selected);
        Button.classList.add('btn');

        // 根据课程是否已选设置按钮样式和文本
        if (course.selected === 0) {
            Button.classList.add('select-btn');
            Button.innerText = 'select';
        } else {
            Button.classList.add('deselect-btn');
            Button.innerText = 'deselect';
        }

        // 将课程标题、教师、开始时间、结束时间和选课按钮添加到课程卡片中
        courseCard.appendChild(courseTitle);
        courseCard.appendChild(courseTeacher);
        courseCard.appendChild(beginTime);
        courseCard.appendChild(endTime);
        courseCard.appendChild(Button);

        // 在查看已选课程的页面中除显示选退课按钮外还要显示跳转到相应课程学习页面的按钮
        if (page === true) {
            const coursePageButton = document.createElement('button');
            coursePageButton.innerText = 'study';
            coursePageButton.classList.add('btn');
            coursePageButton.classList.add('course-page-btn');
            coursePageButton.setAttribute('course-id', course.id);
            courseCard.appendChild(coursePageButton);
        }

        // 将课程卡片添加到课程列表容器中
        courseListContainer.appendChild(courseCard);
    });

    // 课程列表容器添加点击事件监听器，通过事件委托处理按钮点击事件
    courseListContainer.addEventListener('click', function (event) {
        const target = event.target;

        // 检查点击的元素是否为选课按钮
        if (target.classList.contains('select-btn')) {
            // 获取课程ID
            const courseId = target.getAttribute('course-id');

            // 获取用户信息
            const userInfo = JSON.parse(localStorage.getItem('userInfo'));
            const stuId = userInfo.id;

            console.log("stuId: " + stuId);
            console.log("courseId: " + courseId);

            fetch('http://localhost:8080/course/select', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: stuId,
                    courseId: courseId
                }),
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);

                    // 选课成功
                    if (data.code === 6) {
                        // 更改按钮样式
                        target.classList.remove('select-btn');
                        target.classList.add('deselect-btn');
                        target.innerText = 'deselect';

                        // 更新 localStorage 中的信息
                        const course = courseList.find(c => c.id === courseId);
                        if (course) {
                            course.selected = 1;
                        }
                    }
                    // 选课失败
                    else if (data.code === 7) {
                        alert("select failed");
                    }
                    // 其他错误
                    else {
                        alert("Unexpected problem");
                    }
                })
                .catch(error => console.log(error));
        }

        // 检查点击的元素是否为退课按钮
        if (target.classList.contains('deselect-btn')) {
            // 获取课程ID
            const courseId = target.getAttribute('course-id');

            // 获取用户信息
            const userInfo = JSON.parse(localStorage.getItem('userInfo'));
            const stuId = userInfo.id;

            console.log("stuId: " + stuId);
            console.log("courseId: " + courseId);

            fetch('http://localhost:8080/course/deselect', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: stuId,
                    courseId: courseId
                }),
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);

                    // 退课成功
                    if (data.code === 8) {
                        // 改变按钮样式
                        target.classList.remove('deselect-btn');
                        target.classList.add('select-btn');
                        target.innerText = 'select';

                        // 更新 localStorage 中的信息
                        const course = courseList.find(c => c.id === courseId);
                        if (course) {
                            course.selected = 0;
                        }
                    } else if (data.code === 9) {
                        alert("deselect failed");
                    } else {
                        alert("Unexpected problem");
                    }
                })
                .catch(error => console.log(error));
        }

        // 检查点击的元素是否为跳转到课程学习页面的按钮
        if (target.classList.contains('course-page-btn')) {
            // 获取课程ID
            const courseId = target.getAttribute('course-id');

            // 跳转到课程学习页面
            window.location.href = 'study.html?courseId=' + courseId;
        }
    });
}
