<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="viewTicket">
  <article class=".col-9" style="padding: 40px;">
    <h4 th:text="${ticket.topic}"></h4>
    <h5 th:if="${hasMod}">Назначенный сотрудник: <span th:text="${moderator.username}"></span></h5>

    <a th:href="@{'/tickets/close/' + ${ticket.id}}" th:if=${ticket.open}>
      <button class="btn btn-primary">Закрыть</button>
    </a>

    <a th:href="@{'/tickets/open/' + ${ticket.id}}" th:if=${!ticket.open}>
      <button class="btn btn-primary">Открыть</button>
    </a>

    <div th:each="row: ${messages}">
      <span></span>:
      <p>

      </p>
    </div>
  </article>
</div>
</body>
</html>