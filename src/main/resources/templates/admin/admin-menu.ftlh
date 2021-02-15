<#import "../fragments/pagination.ftl" as pagination/>

<head>
    <#include "../fragments/bootstrap.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Administrator menu</title>
</head>

<body>

<#include "../fragments/nav-bar.ftl" />
<br>

<div class="container">
    <div class="row">
        <h2 class="text"><@spring.message "title.main.menu"/></h2>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <th><@spring.message "field.user.login"/></th>
                <th><@spring.message "field.user.name"/></th>
                <th class="info"><@spring.message "field.user.balance"/></th>
                <th><@spring.message "field.user.status"/></th>
            </tr>
            <#list page.content as user>
                <tr>
                    <td>
                        <form class="form-inline ml-2 mr-2 my-2 my-lg-0" action="/admin/user-details/${user.id?long?c}" method="get">
                            <button class="btn btn-link p-0 m-0 d-inline align-baseline">${user.id?long?c}</button>
                        </form>
                    </td>
                    <#if springMacroRequestContext.getLocale().toString()?contains("en")>
                        <td>${user.getNameEn()}</td>
                    <#else>
                        <td>${user.getNameUkr()}</td>
                    </#if>
                    <td>${user.getBalance()} <@spring.message "currency"/></td>
                    <#if user.getUserStatus() == "ACTIVE">
                        <td ><@spring.message "field.user.active"/></td>
                    <#else>
                        <td style="color: crimson"><@spring.message "field.user.blocked"/></td>
                    </#if>
                    <td>
                        <form class="form-inline ml-auto my-2 my-lg-0"
                              action="/admin/user-status/${user.id?long?c}" method="post">
                            <button class="btn btn-primary " type="submit"><@spring.message "button.change.status"/></button>
                        </form>
                    </td>
                </tr>

            </#list>
            </thead>
        </table>

        <div class="col-md-6 offset-md-4">
            </button>
            <a href="/registration" class="btn btn-link">
                <@spring.message "title.registration"/>
            </a>
        </div>
        <a class="text-center"><@pagination.pagination "menu" page/></a>
    </div>
</div>
<br>
<br>
<#include "../fragments/footer.ftlh"/>
</body>