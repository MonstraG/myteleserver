<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="viewTicket">
  <article class=".col-9" style="padding: 40px;">

    <a href="/ticket/add" th:if=${userDetails.authorities.stream().findFirst().get().getAuthority().equals("USER")}>
      <button class="btn btn-primary">Добавить заявку</button>
    </a>
  </article>
</div>
</body>
</html>