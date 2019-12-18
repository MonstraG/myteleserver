<jsp:useBean id="userDetails" scope="request" type="org.springframework.security.core.userdetails.UserDetails"/>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <title>MyTeleОдин</title>
</head>
<body>
<div class="container-fluid" style="height: 100vh; overflow: hidden">
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
  <main class="row" style="height: 100vh; width: 100vw">
    <aside class=".col-3" style="background-color: lightblue; padding-right: 25px;">
      <ul style="list-style: none; padding-top: 20px;">
        <li><a href="/">Главная</a></li>
        <li><a href="/tariffs">Тарифы</a></li>
        <li><a href="/services">Доп. услуги</a></li>
        <li><a href="/tickets">Заявки</a></li>
        <li><a href="/users" th:if=${userDetails.authorities.stream().findFirst().get().getAuthority().equals("ADMIN")}>Пользователи</a></li>
      </ul>
    </aside>
    <article class=".col-9" style="padding: 40px;">
      <div id="ads" th:if=${userDetails.authorities.stream().findFirst().get().getAuthority().equals("USER")}>
        A тут кароч всякая реклама ребят
      </div>
      <div th:if=${userDetails.authorities.stream().findFirst().get().getAuthority().equals("ADMIN")}>
        <h4>Статистика</h4>
        <ul style="list-style: none">
          <li>Пользователей: <span th:text=${stats.get("users")}></span></li>
          <li>Тарифов <span th:text=${stats.get("tariffs")}></span></li>
          <li>Услуг: <span th:text=${stats.get("additionalServices")}></span></li>
          <li>Заявок: <span th:text=${stats.get("tickets")}></span></li>
          <li>Сообщений: <span th:text=${stats.get("messages")}></span></li>
        </ul>
      </div>
    </article>
  </main>
</div>
</body>
</html>