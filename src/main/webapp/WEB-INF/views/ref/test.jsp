<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management Portal</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
        }
        .header {
            width: 100%;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .profile {
            display: flex;
            align-items: center;
        }
        .profile img {
            border-radius: 50%;
            margin-right: 10px;
        }
        .profile-info {
            text-align: left;
        }
        .profile-info .name {
            font-size: 16px;
            font-weight: bold;
        }
        .profile-info .rank {
            font-size: 14px;
            color: #888;
        }
        .logout {
            color: #000;
            text-decoration: none;
            font-size: 14px;
        }
        .content {
            display: flex;
            width: 100%;
            max-width: 1200px;
            margin-top: 20px;
        }
        .sidebar {
            width: 200px;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            margin-bottom: 10px;
        }
        .sidebar ul li a {
            text-decoration: none;
            color: #000;
            font-size: 14px;
        }
        .main {
            flex: 1;
            padding: 20px;
        }
        .status-box {
            display: flex;
            justify-content: space-between;
            background-color: #eaf6ff;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .status-box table {
            width: 100%;
            border-collapse: collapse;
        }
        .status-box table, .status-box th, .status-box td {
            border: 1px solid #d6e9ff;
        }
        .status-box th, .status-box td {
            padding: 10px;
            text-align: center;
        }
        .calendar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .calendar .month {
            font-size: 18px;
            font-weight: bold;
        }
        .calendar-table {
            width: 100%;
            border-collapse: collapse;
        }
        .calendar-table th, .calendar-table td {
            border: 1px solid #d6e9ff;
            padding: 10px;
            text-align: center;
        }
        .calendar-table th {
            background-color: #eaf6ff;
        }
        .notes {
            display: flex;
            justify-content: space-between;
        }
        .note {
            width: 48%;
            padding: 20px;
            background-color: #ffecb3;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .note.green {
            background-color: #d9f7be;
        }
        footer {
            text-align: center;
            padding: 10px;
            background-color: #fff;
            border-top: 1px solid #eee;
        }
        footer a {
            color: #888;
            text-decoration: none;
            font-size: 14px;
            margin: 0 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="profile">
                <img src="profile_picture_url" alt="Profile Picture" width="50" height="50">
                <div class="profile-info">
                    <div class="name">Java Park</div>
                    <div class="rank">Lv.5</div>
                    <div>Java Park님 환영합니다.</div>
                </div>
            </div>
            <a href="#" class="logout">로그아웃</a>
        </div>
        <div class="content">
            <div class="sidebar">
                <ul>
                    <li><a href="#">통합업무</a></li>
                    <li><a href="#">게시판</a></li>
                    <li><a href="#">익명게시판</a></li>
                    <li><a href="#">전자결재</a></li>
                    <li><a href="#">직원관리</a></li>
                    <li><a href="#">팀장전자결재</a></li>
                    <li><a href="#">캘린더</a></li>
                    <li><a href="#">권한관리</a></li>
                </ul>
            </div>
            <div class="main">
                <div class="status-box">
                    <table>
                        <tr>
                            <th>구분</th>
                            <th>기준</th>
                            <th>사용</th>
                            <th>잔여</th>
                        </tr>
                        <tr>
                            <td>연차</td>
                            <td>11일</td>
                            <td>2일</td>
                            <td>5일</td>
                        </tr>
                        <tr>
                            <td>출근</td>
                            <td>11일</td>
                            <td>2일</td>
                            <td>0일</td>
                        </tr>
                    </table>
                </div>
                <div class="calendar">
                    <div class="month">September, 2024</div>
                    <div>일정관리</div>
                </div>
                <table class="calendar-table">
                    <thead>
                        <tr>
                            <th>Mon</th>
                            <th>Tue</th>
                            <th>Wed</th>
                            <th>Thu</th>
                            <th>Fri</th>
                            <th>Sat</th>
                            <th>Sun</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>29</td>
                            <td>30</td>
                            <td>31</td>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <td style="background-color: #bae7ff;">5</td>
                            <td style="background-color: #bae7ff;">6</td>
                            <td style="background-color: #bae7ff;">7</td>
                            <td style="background-color: #bae7ff;">8</td>
                            <td style="background-color: #bae7ff;">9</td>
                            <td>10</td>
                            <td>11</td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td>13</td>
                            <td>14</td>
                            <td>15</td>
                            <td>16</td>
                            <td>17</td>
                            <td>18</td>
                        </tr>
                        <tr>
                            <td>19</td>
                            <td>20</td>
                            <td>21</td>
                            <td>22</td>
                            <td>23</td>
                            <td>24</td>
                            <td>25</td>
                        </tr>
                        <tr>
                            <td>26</td>
                            <td>27</td>
                            <td>28</td>
                            <td>29</td>
                            <td>30</td>
                            <td>1</td>
                            <td>2</td>
                        </tr>
                    </tbody>
                </table>
                <div class="notes">
                    <div class="note">
                        12:20 부장님과 점심<br>
                        14:50 미팅
                    </div>
                    <div class="note green">
                        Add notes
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div>
                <a href="#">© 2022 Brand, Inc.</a>
                <a href="#">Privacy</a>
                <a href="#">Terms</a>
                <a href="#">Sitemap</a>
            </div>
            <div>
                <a href="#">USD</a>
                <a href="#">Language</a>
                <a href="#"><img src="facebook_icon_url" alt="Facebook"></a>
                <a href="#"><img src="twitter_icon_url" alt="Twitter"></a>
                <a href="#"><img src="instagram_icon_url" alt="Instagram"></a>
            </div>
        </footer>
    </div>
</body>
</html>