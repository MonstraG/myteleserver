<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <title>MyTeleОдин</title>
</head>
<body>
<div class="container-fluid" style="height: 100vh; overflow: hidden">
  <div th:insert="~{nav::navbar}"></div>
  <main class="row" style="height: 100vh; width: 100vw">
    <div th:insert="~{nav::aside}"></div>
    <div th:insert="~{content::ticketsTable}"></div>
  </main>
</div>
</body>
</html>