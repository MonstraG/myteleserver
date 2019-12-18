<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="addTariff">
    <div style="padding: 20px 40px;">
        <form action="#" th:action="@{/tariffs/create}" th:object="${tariff}" method="post">
            <div class="form-group">
                <label for="name">Название</label>
                <input type="text" class="form-control" id="name" th:field="*{name}">
            </div>
            <div class="form-group">
                <label for="monthlyCharge">Ежемесячный платеж</label>
                <input type="number" class="form-control" id="monthlyCharge" th:field="*{monthlyCharge}">
            </div>
            <div class="form-group">
                <label for="connectionCharge">Плата за подключение</label>
                <input type="number" class="form-control" id="connectionCharge" th:field="*{connectionCharge}">
            </div>
            <div class="form-group">
                <label for="minutes">Минут разговора</label>
                <input type="number" class="form-control" id="minutes" th:field="*{minutes}">
            </div>
            <div class="form-group">
                <label for="sms">СМС</label>
                <input type="number" class="form-control" id="sms" th:field="*{sms}">
            </div>
            <div class="form-group">
                <label for="internet">ГБ в месяц</label>
                <input type="number" class="form-control" id="internet" th:field="*{internet}">
            </div>
            <button type="submit" class="btn btn-primary">Добавить</button>
        </form>
    </div>
</div>

<div th:fragment="addService">
    <div style="padding: 20px 40px;">
        <form action="#" th:action="@{/services/create}" th:object="${service}" method="post">
            <div class="form-group">
                <label for="name">Название</label>
                <input type="text" class="form-control" id="name" th:field="*{name}">
            </div>
            <div class="form-group">
                <label for="price">Ежемесячный платеж</label>
                <input type="number" class="form-control" id="price" th:field="*{price}">
            </div>
            <div class="form-group">
                <label for="description">Описание</label>
                <input type="number" class="form-control" id="description" th:field="*{description}">
            </div>
            <button type="submit" class="btn btn-primary">Добавить</button>
        </form>
    </div>
</div>

<div th:fragment="addTicket">
    <div style="padding: 20px 40px;">
        <form action="#" th:action="@{/tickets/create}" th:object="${message}" method="post">
            <div class="form-group">
                <label for="text">Текст заявки</label>
                <textarea class="form-control" id="text" th:field="*{text}"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Создать</button>
        </form>
    </div>
</div>

</body>
</html>