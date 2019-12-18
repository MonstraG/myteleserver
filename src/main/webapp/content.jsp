<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="home">
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
</div>

<div th:fragment="tariffsTable">
  <article class=".col-9" style="padding: 40px;">
    <h4>Тарифы</h4>
    <h5 th:if=${!hasContent}>Тарифов нет</h5>
    <div th:if=${hasContent}>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">Название</th>
          <th scope="col">Ежемесячная цена</th>
          <th scope="col">Цена за подключение</th>
          <th scope="col">Минут в месяц</th>
          <th scope="col">СМС в месяц</th>
          <th scope="col">ГБ в месяц</th>
        </tr>
        </thead>
        <tbody>
        <tr class="even pointer" th:each="row: ${tariffs}" id="tablerow">
          </th>
          <td th:each="field: ${row}" th:text="${field.name}" />
          <td th:each="field: ${row}" th:text="${field.monthlyCharge}" />
          <td th:each="field: ${row}" th:text="${field.connectionCharge}" />
          <td th:each="field: ${row}" th:text="${field.minutes}" />
          <td th:each="field: ${row}" th:text="${field.sms}" />
          <td th:each="field: ${row}" th:text="${field.internet}" />
        </tr>
        </tbody>
      </table>
    </div>

    <a href="/tariffs/add" th:if=${!userDetails.authorities.stream().findFirst().get().getAuthority().equals("USER")}><button class="btn btn-primary">Добавить тариф</button></a>
  </article>
</div>

<div th:fragment="servicesTable">
  <article class=".col-9" style="padding: 40px;">
    <h4>Дополнительные услуги</h4>
    <h5 th:if=${!hasContent}>Доп. услуг нет</h5>
    <div th:if=${hasContent}>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">Название</th>
          <th scope="col">Ежемесячная цена</th>
          <th scope="col">Описание</th>
        </tr>
        </thead>
        <tbody>
        <tr class="even pointer" th:each="row: ${services}" id="tablerow">
          </th>
          <td th:each="field: ${row}" th:text="${field.name}" />
          <td th:each="field: ${row}" th:text="${field.price}" />
          <td th:each="field: ${row}" th:text="${field.description}" />
        </tr>
        </tbody>
      </table>
    </div>

    <a href="/services/add" th:if=${!userDetails.authorities.stream().findFirst().get().getAuthority().equals("USER")}><button class="btn btn-primary">Добавить услугу</button></a>
  </article>
</div>

</body>
</html>