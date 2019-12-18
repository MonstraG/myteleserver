<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="navbar">
  <nav class="row justify-content-end" style="background-color: lightgray; height: 68px;">
    <div style="padding: 15px 40px 15px 0">
      <div style="display: flex; align-items: center; justify-content: center">
        <span>
           Вы зашли как <b style="padding-right: 20px" th:text=${userDetails.username}></b>
        </span>
        <a href="/logout">
          <button class="btn btn-secondary">Выйти</button>
        </a>
      </div>
    </div>
  </nav>
</div>

<div th:fragment="aside">
  <aside class=".col-3" style="background-color: lightblue; padding-right: 25px; height: 100vh;">
    <ul style="list-style: none; padding-top: 20px;">
      <li><a href="/">Главная</a></li>
      <li><a href="/tariffs/list">Тарифы</a></li>
      <li><a href="/services/list">Доп. услуги</a></li>
      <li><a href="/tickets">Заявки</a></li>
      <li><a href="/users" th:if=${userDetails.authorities.stream().findFirst().get().getAuthority().equals("ADMIN")}>Пользователи</a></li>
    </ul>
  </aside>
</div>

</body>
</html>