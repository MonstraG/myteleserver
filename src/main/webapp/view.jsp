<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="viewTicket">
  <article class=".col-9" style="padding: 40px;">
    <h4 th:text="${ticket.topic}"></h4>
    <h5 th:if="${hasMod}">Назначенный сотрудник: <span th:text="${moderator.username}"></span></h5>
    <h5 style="padding-top: 15px" th:if="${!hasMod}">Сотрудник еще не назначен</h5>

    <h5 style="padding-top: 15px">Сообщения:</h5>

    <th:block th:each="message: ${ticket.messageList}">
      <span th:text="${names.get(message.author)}"></span>:
      <p th:text="${message.text}"></p>
      <hr>
    </th:block>

    <div style="display: flex; flex-direction: row; justify-content: start;">
      <a th:href="@{'/tickets/' + ${ticket.id} + '/addMessage/'}">
        <button class="btn btn-primary">Ответить</button>
      </a>

      <a th:href="@{'/tickets/close/' + ${ticket.id}}" th:if=${ticket.open}>
        <button class="btn btn-secondary">Закрыть</button>
      </a>

      <a th:href="@{'/tickets/open/' + ${ticket.id}}" th:if=${!ticket.open}>
        <button class="btn btn-secondary">Открыть</button>
      </a>
    </div>
  </article>
</div>
</body>
</html>