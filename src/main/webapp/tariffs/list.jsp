<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <title>MyTeleОдин</title>
</head>
<body>
<div class="container-fluid" style="min-height: 100vh; display: flex; flex-direction: column;">
  <div th:insert="~{nav::navbar}"></div>
  <main class="row" style="display: flex; flex-direction: row; align-items: stretch; flex-grow: 1">
    <div style="display: flex;" th:insert="~{nav::aside}"></div>
    <div th:insert="~{content::tariffsTable}"></div>
  </main>
</div>
</body>
</html>